package com.sd.lib.result.exception.http

import com.sd.lib.result.R
import com.sd.lib.result.ext.LibContentProvider

/**
 * Http返回数据解析异常
 */
class FExceptionHttpParseResponse @JvmOverloads constructor(
    message: String? = "",
    cause: Throwable? = null,
) : FExceptionHttp(message, cause) {

    override val formatMessage: String
        get() {
            val context = LibContentProvider.application ?: return super.formatMessage
            return buildString {
                val message = localizedMessage ?: context.getString(R.string.lib_result_http_desc_exception_http)
                val causeInfo = context.getString(R.string.lib_result_http_desc_exception_parse_response)

                append(message)
                if (message.isNotEmpty() && causeInfo.isNotEmpty()) {
                    append(" ")
                }
                append(causeInfo)
            }
        }
}