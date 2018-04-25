package com.felipeporge.cleanbootstrap.rxkotlin.domain.interactors

import com.felipeporge.cleanbootstrap.rxkotlin.domain.executors.PostExecution
import com.felipeporge.cleanbootstrap.rxkotlin.domain.executors.TaskExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


/**
 * This class represents an use case.
 * @author  Felipe Porge Xavier - <a href="http://www.felipeporge.com" target="_blank">www.felipeporge.com</a>
 * @date    27/05/2017
 */
abstract class ObservableUseCase<in PARAMS, RESULT>(
    private val taskExecutor: TaskExecutor,
    private val postExecutor: PostExecution
) {

    private val compDisposables = CompositeDisposable()

    /**
     * Builds an use case observer.
     * @param params    Use case parameters.
     * @return  Built observable for this use case.
     */
    abstract fun buildObservable(params: PARAMS): Observable<RESULT>

    /**
     * Executes the interactor.
     * @param params    Interactor params.
     * @param onSubscribe   Function to run on observer subscribed.
     * @param onNext    Function to run on next result received.
     * @param onError   Function to run on error.
     * @param onComplete    Function to run on complete.
     */
    fun execute(
        params: PARAMS,
        onSubscribe: (() -> Unit)? = null,
        onNext: ((result: RESULT) -> Unit)? = null,
        onError: ((exception: Throwable?) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {

        val disposable = buildObservable(params)
            .subscribeOn(Schedulers.from(taskExecutor))
            .observeOn(postExecutor.scheduler)
            .doOnSubscribe { onSubscribe?.invoke() }
            .subscribeWith(object : DisposableObserver<RESULT>() {
                override fun onError(e: Throwable) {
                    onError?.invoke(e)
                }

                override fun onNext(result: RESULT) {
                    onNext?.invoke(result)
                }

                override fun onComplete() {
                    onComplete?.invoke()
                }
            })

        compDisposables.add(disposable)
    }

    /**
     * Disposes this interactor.
     */
    fun dispose() {
        if (!compDisposables.isDisposed)
            compDisposables.dispose()
    }
}