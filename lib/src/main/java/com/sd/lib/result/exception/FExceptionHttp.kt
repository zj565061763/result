package com.sd.lib.result.exception

/**
 * Http异常
 */
open class FExceptionHttp : FException {
    @JvmOverloads
    constructor(message: String? = "", cause: Throwable? = null) : super(message, cause)
}