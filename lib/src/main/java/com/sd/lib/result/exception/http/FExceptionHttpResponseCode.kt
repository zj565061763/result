package com.sd.lib.result.exception.http

import com.sd.lib.result.R
import com.sd.lib.result.ext.LibContentProvider

/**
 * Http返回码异常
 */
class FExceptionHttpResponseCode @JvmOverloads constructor(
    code: Int,
    message: String? = "",
) : FExceptionHttp(message = message) {
    val code = code

    override val formatMessage: String
        get() {
            val context = LibContentProvider.application ?: return super.formatMessage
            return buildString {
                val message = localizedMessage ?: context.getString(R.string.lib_result_http_desc_exception_http)
                val causeInfo = context.getString(R.string.lib_result_http_desc_exception_response_code) + "($code)"

                append(message)
                if (message.isNotEmpty() && causeInfo.isNotEmpty()) {
                    append(" ")
                }
                append(causeInfo)
            }
        }
}