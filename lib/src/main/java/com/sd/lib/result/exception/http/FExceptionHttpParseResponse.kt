package com.sd.lib.result.exception.http

import com.sd.lib.context.FContext
import com.sd.lib.result.R

/**
 * Http返回数据解析异常
 */
class FExceptionHttpParseResponse @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null,
) : FExceptionHttp(message, cause) {

    override val formatCause: String
        get() {
            val context = FContext.get() ?: return super.formatCause
            return context.getString(R.string.lib_result_exception_http_cause_parse_response)
        }
}