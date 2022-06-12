package com.sd.lib.result.exception.http

import android.content.Context
import com.sd.lib.result.R

/**
 * Http返回数据解析异常
 */
class FExceptionHttpParseResponse @JvmOverloads constructor(
    message: String? = "",
    cause: Throwable? = null,
) : FExceptionHttp(message, cause) {
    override fun getDescFormat(context: Context): String {
        return context.getString(R.string.lib_result_http_desc_exception_parse_response) + toStringAppend()
    }
}