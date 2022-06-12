package com.sd.lib.result.exception

open class FException @JvmOverloads constructor(
    message: String? = "",
    cause: Throwable? = null,
) : Exception(message, cause) {

    protected open val formatMessage: String
        get() {
            val superMessage = localizedMessage ?: ""
            val causeMessage = cause?.toString() ?: ""
            return superMessage + causeMessage
        }

    override fun toString(): String {
        return formatMessage
    }
}

fun Exception.isLoading(): Boolean {
    return this is FExceptionLoading
}

fun Exception.isCancellation(): Boolean {
    return this is FExceptionCancellation
}