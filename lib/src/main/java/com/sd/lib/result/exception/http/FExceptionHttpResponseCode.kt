package com.sd.lib.result.exception.http

import com.sd.lib.context.FContext
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
            val context = FContext.get() ?: return super.formatCause
            return buildString {
                append(context.getString(R.string.lib_result_exception_http_cause_response_code))
                append("(").append(code).append(")")
            }
        }
}