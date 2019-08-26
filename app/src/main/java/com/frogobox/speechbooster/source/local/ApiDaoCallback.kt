package com.frogobox.speechbooster.source.local

import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * SpeechBooster
 * Copyright (C) 26/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.speechbooster.source.local
 *
 */
abstract class ApiDaoCallback : CompletableObserver {

    abstract fun onFinish()
    abstract fun onFailed(errorMessage: String)
    abstract fun onAddSubscribe(disposable: Disposable)

    override fun onComplete() {
        onFinish()
    }

    override fun onError(e: Throwable) {
        onFailed(e.message ?: "Process is error")
    }

    override fun onSubscribe(d: Disposable) {
        onAddSubscribe(d)
    }
}