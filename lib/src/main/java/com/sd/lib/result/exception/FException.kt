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

    companion object {
        @JvmStatic
        fun wrap(
            message: String? = "",
            cause: Throwable? = null,
        ): FException {
            return if (cause is FException) cause else FException(message, cause)
        }
    }
}

fun Exception.isLoading(): Boolean {
    return this is FExceptionLoading
}

fun Exception.isCancellation(): Boolean {
    return this is FExceptionCancellation
}