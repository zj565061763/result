package com.sd.lib.result.exception

open class FException @JvmOverloads constructor(
    message: String? = "",
    cause: Throwable? = null,
) : Exception(message, cause) {

    open val desc: String
        get() = buildString {
            val message = formatMessage
            append(message)

            val causeInfo = formatCauseInfo
            if (message.isNotEmpty() && causeInfo.isNotEmpty()) {
                append(" ")
            }
            append(causeInfo)
        }

    protected open val formatMessage: String
        get() = localizedMessage ?: ""

    protected open val formatCauseInfo: String
        get() = cause?.toString() ?: ""

    override fun toString(): String {
        return desc
    }
}

fun Exception.isCancellation(): Boolean {
    return this is FExceptionCancellation
}

fun Exception.isLoading(): Boolean {
    return this is FExceptionLoading
}