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

    override val formatCause: String
        get() {
            return buildString {
                append(fContext.getString(R.string.lib_result_exception_http_cause_response_code))
                append("(").append(code).append(")")
            }
        }
}