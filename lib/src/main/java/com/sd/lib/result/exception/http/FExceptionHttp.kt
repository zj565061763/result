package com.sd.lib.result.exception.http

import android.content.Context
import com.sd.lib.result.R
import com.sd.lib.result.exception.FException
import java.net.SocketTimeoutException

/**
 * Http异常
 */
open class FExceptionHttp @JvmOverloads constructor(
    message: String? = "",
    cause: Throwable? = null,
) : FException(message, cause) {

    /** 错误描述 */
    open fun getDescFormat(context: Context): String {
        return when (cause) {
            is SocketTimeoutException -> context.getString(R.string.lib_result_http_desc_exception_timeout)
            else -> context.getString(R.string.lib_result_http_desc_exception_http)
        } + toStringAppend()
    }

    protected fun toStringAppend(): String {
        val content = toString()
        return if (content.isEmpty()) {
            content
        } else {
            "，${content}"
        }
    }

    companion object {
        @JvmStatic
        fun wrap(e: Throwable): FExceptionHttp {
            return if (e is FExceptionHttp) e else FExceptionHttp(null, e)
        }
    }
}