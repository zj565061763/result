package com.sd.lib.result.exception

/**
 * 状态码异常
 */
open class FExceptionCode @JvmOverloads constructor(
    val code: Int,
    message: String? = null,
    cause: Throwable? = null,
) : FException(message, cause)