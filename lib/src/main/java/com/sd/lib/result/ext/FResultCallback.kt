package com.sd.lib.result.ext

interface FResultCallback<T> {
    fun onResult(result: Result<T>)

    fun interface Cancelable {
        fun cancel()
    }
}