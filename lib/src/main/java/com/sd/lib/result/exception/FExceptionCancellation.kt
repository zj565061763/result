package com.sd.lib.result.exception

/**
 * 取消
 */
open class FExceptionCancellation @JvmOverloads constructor(
    message: String? = "",
    cause: Throwable? = null,
) : FException(message, cause)