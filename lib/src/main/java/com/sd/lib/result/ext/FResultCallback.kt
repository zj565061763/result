package com.sd.lib.result.ext

import com.sd.lib.result.FResult

fun interface FResultCallback<T> {
    fun onResult(result: FResult<T>)

    fun interface Cancelable {
        fun cancel()
    }
}