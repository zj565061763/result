package com.sd.lib.result.exception.http

import com.sd.lib.ctx.fContext
import com.sd.lib.result.R

/**
 * Http返回码异常
 */
class FExceptionHttpResponseCode @JvmOverloads constructor(
    val code: Int,
    message: String? = null,
) : FExceptionHttp(message = message) {

    override val formatMessage: String
        get() = buildString {
            val localMsg = fContext.getString(R.string.lib_result_exception_http_message_response_code)
            val superMsg = super.formatMessage

            if (localMsg.isNotEmpty()) {
                append(localMsg)
                append("(").append(code).append(")")
            }
            if (localMsg.isNotEmpty() && superMsg.isNotEmpty()) {
                append(" ")
            }
            append(superMsg)
        }
}