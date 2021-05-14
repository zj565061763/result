package com.sd.lib.result.ext

import com.sd.lib.result.FResult

interface FResultCallback<T> {
    fun onResult(result: FResult<T>)
}