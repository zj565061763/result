package com.sd.lib.result.exception

/**
 * 正在执行中
 */
open class FExceptionRunning : FException {
    @JvmOverloads
    constructor(message: String? = "", cause: Throwable? = null) : super(message, cause)
}