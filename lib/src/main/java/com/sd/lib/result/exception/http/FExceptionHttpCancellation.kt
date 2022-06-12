package com.sd.lib.result.exception.http

import android.content.Context

/**
 * Http请求被取消
 */
class FExceptionHttpCancellation : FExceptionHttp() {
    override fun getDescFormat(context: Context): String {
        return ""
    }
}