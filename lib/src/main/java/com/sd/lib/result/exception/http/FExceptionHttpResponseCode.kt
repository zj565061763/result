package com.sd.lib.result.exception.http

import android.content.Context
import com.sd.lib.result.R

/**
 * Http返回码异常
 */
class FExceptionHttpResponseCode @JvmOverloads constructor(
    code: Int,
    message: String? = "",
) : FExceptionHttp(message = message) {
    val code = code

    override fun getDescFormat(context: Context): String {
        return context.getString(R.string.lib_result_http_desc_exception_response_code) + toStringAppend()
    }

    override fun toString(): String {
        return "${super.toString()} code:${code}"
    }
}