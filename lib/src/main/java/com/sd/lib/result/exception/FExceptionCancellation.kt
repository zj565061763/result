package com.sd.lib.result.exception

/**
 * 取消
 */
open class FExceptionCancellation @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null,
) : FException(message, cause)