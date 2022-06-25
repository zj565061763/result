package com.sd.lib.result.exception.http

import com.sd.lib.result.R
import com.sd.lib.result.exception.FException
import com.sd.lib.result.ext.LibContentProvider
import java.net.SocketTimeoutException

/**
 * Http异常
 */
open class FExceptionHttp @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null,
) : FException(message, cause) {

    override val formatCause: String
        get() {
            val superInfo = super.formatCause
            val context = LibContentProvider.application ?: return superInfo
            return when (cause) {
                is SocketTimeoutException -> context.getString(R.string.lib_result_http_desc_exception_timeout)
                else -> superInfo
            }
        }
}