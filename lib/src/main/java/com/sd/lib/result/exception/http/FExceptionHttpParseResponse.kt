package com.sd.lib.result.exception.http

import com.sd.lib.ctx.fContext
import com.sd.lib.result.R

/**
 * Http返回数据解析异常
 */
class FExceptionHttpParseResponse @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null,
) : FExceptionHttp(message, cause) {

    override val formatMessage: String
        get() = buildString {
            val localMsg = fContext.getString(R.string.lib_result_exception_http_message_parse_response)
            val superMsg = super.formatMessage

            append(localMsg)
            if (localMsg.isNotEmpty() && superMsg.isNotEmpty()) {
                append(" ")
            }
            append(superMsg)
        }
}