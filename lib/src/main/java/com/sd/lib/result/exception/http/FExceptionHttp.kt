package com.sd.lib.result.exception.http

import com.sd.lib.result.R
import com.sd.lib.result.exception.FException
import com.sd.lib.result.ext.LibContentProvider
import java.net.SocketTimeoutException

/**
 * Http异常
 */
open class FExceptionHttp @JvmOverloads constructor(
    message: String? = "",
    cause: Throwable? = null,
) : FException(message, cause) {

    override val formatMessage: String
        get() {
            val superInfo = super.formatMessage
            val context = LibContentProvider.application ?: return superInfo
            return superInfo.ifEmpty {
                context.getString(R.string.lib_result_http_desc_exception_http)
            }
        }

    override val formatCause: String
        get() {
            val context = LibContentProvider.application ?: return super.formatCause
            return when (cause) {
                is SocketTimeoutException -> context.getString(R.string.lib_result_http_desc_exception_timeout)
                else -> super.formatCause
            }
        }
}