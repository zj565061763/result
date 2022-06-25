package com.sd.lib.result.exception.http

import android.content.Context
import com.sd.lib.result.exception.FException
import com.sd.lib.result.ext.LibContentProvider

/**
 * Http异常
 */
open class FExceptionHttp @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null,
) : FException(message, cause) {

    override val formatCause: String
        get() {
            return getCauseInfo(cause, LibContentProvider.application).ifEmpty {
                super.formatCause
            }
        }

    companion object {
        @JvmStatic
        fun wrap(cause: Throwable?): FExceptionHttp {
            return if (cause is FExceptionHttp) cause else FExceptionHttp(cause = cause)
        }

        @JvmStatic
        fun getCauseInfo(cause: Throwable?, context: Context?): String {
            if (cause == null || context == null) return ""
            val resName = "lib_result_exception_http_cause_${cause.javaClass.name}"
            return try {
                val resId = context.resources.getIdentifier(resName, "string", context.packageName)
                context.getString(resId)
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }
    }
}