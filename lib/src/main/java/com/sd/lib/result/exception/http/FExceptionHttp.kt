package com.sd.lib.result.exception.http

import com.sd.lib.ctx.fContext
import com.sd.lib.result.exception.FException

/**
 * Http异常
 */
open class FExceptionHttp @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null,
) : FException(message, cause) {

    override val formatCause: String
        get() = getCauseInfo(cause).ifEmpty { super.formatCause }

    companion object {
        @JvmStatic
        fun wrap(throwable: Throwable?): FExceptionHttp {
            return if (throwable is FExceptionHttp) throwable else FExceptionHttp(cause = throwable)
        }

        @JvmStatic
        fun getCauseInfo(cause: Throwable?): String {
            if (cause == null) return ""
            val resName = "lib_result_exception_http_cause_${cause.javaClass.name}"
            return try {
                val context = fContext
                val resId = context.resources.getIdentifier(resName, "string", context.packageName)
                context.getString(resId)
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }
    }
}