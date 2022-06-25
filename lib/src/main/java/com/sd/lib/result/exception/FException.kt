package com.sd.lib.result.exception

open class FException @JvmOverloads constructor(
    message: String? = "",
    cause: Throwable? = null,
) : Exception(message, cause) {

    protected open val formatMessage: String
        get() {
            return buildString {
                val message = localizedMessage ?: ""
                val causeInfo = cause?.toString() ?: ""

                append(message)
                if (message.isNotEmpty() && causeInfo.isNotEmpty()) {
                    append(" ")
                }
                append(causeInfo)
            }
        }

    override fun toString(): String {
        return formatMessage
    }
}

fun Exception.isCancellation(): Boolean {
    return this is FExceptionCancellation
}

fun Exception.isStateLoading(): Boolean {
    return this is FExceptionStateLoading
}