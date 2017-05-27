package com.felipeporge.cleanbootstrap.rxkotlin.presentation.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.felipeporge.cleanbootstrap.rxkotlin.presentation.presenters.MvpPresenter
import com.felipeporge.cleanbootstrap.rxkotlin.presentation.views.MvpView
import javax.inject.Inject

/**
 * This class represents a presenter fragment.
 * @author  Felipe Porge Xavier - <a href="http://www.felipeporge.com" target="_blank">www.felipeporge.com</a>
 * @date    14/05/2017
 */
abstract class PresenterFragment<PRESENTER: MvpPresenter<in MvpView>> : Fragment(), MvpView {

    @Inject
    lateinit var presenter: PRESENTER

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.view = this
        presenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        presenter.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}