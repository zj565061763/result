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
            val context = LibContentProvider.application ?: return super.formatMessage
            return buildString {
                val causeMessage = when (cause) {
                    is SocketTimeoutException -> context.getString(R.string.lib_result_http_desc_exception_timeout)
                    else -> context.getString(R.string.lib_result_http_desc_exception_http)
                }
                append(causeMessage)

                val message = localizedMessage ?: ""
                if (message.isNotEmpty()) {
                    append(" (").append(message).append(")")
                }
            }
        }

    companion object {
        @JvmStatic
        fun wrap(
            message: String? = "",
            cause: Throwable? = null,
        ): FExceptionHttp {
            return if (cause is FExceptionHttp) cause else FExceptionHttp(message, cause)
        }
    }
}