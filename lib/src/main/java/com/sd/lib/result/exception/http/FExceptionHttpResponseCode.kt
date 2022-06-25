package com.sd.lib.result.exception.http

import com.sd.lib.result.R
import com.sd.lib.result.ext.LibContentProvider

/**
 * Http返回码异常
 */
class FExceptionHttpResponseCode @JvmOverloads constructor(
    val code: Int,
    message: String? = null,
) : FExceptionHttp(message = message) {

    override val formatCause: String
        get() {
            val context = LibContentProvider.application ?: return super.formatCause
            return buildString {
                append(context.getString(R.string.lib_result_http_desc_exception_response_code))
                append("(").append(code).append(")")
            }
        }
}