package com.sd.lib.result.exception

/**
 * Http异常
 */
open class FExceptionHttp @JvmOverloads constructor(
    message: String? = "",
    cause: Throwable? = null,
) : FException(message, cause)