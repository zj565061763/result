package com.sd.lib.result.exception

/**
 * 取消
 */
open class FExceptionCancellation : FException {
    @JvmOverloads
    constructor(message: String? = "", cause: Throwable? = null) : super(message, cause)
}