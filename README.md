# Uncle Bob's Clean Architecture - Bootstrap for Android (Kotlin + Rx version)

This repository contains some standard boilerplate for an implementation of Uncle Bob's Clean Architecture.

# Steps to add android-clean-bootstrap-rxkotlin on your project dependencies:

1. Add jitpack repository url in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
  
2. Add the dependency on your modules, only updating the version according to available tags ( https://github.com/felipefpx/android-clean-bootstrap-rxkotlin/tags ) :
```
  dependencies {
     compile 'com.github.felipefpx.android-clean-bootstrap-rxkotlin:domain:0.0.1'
     compile 'com.github.felipefpx.android-clean-bootstrap-rxkotlin:presentation:0.0.1'
   }
```

3. Resync Gradle and Rebuild the project.
