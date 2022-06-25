package com.sd.lib.result.exception

open class FException @JvmOverloads protected constructor(
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
            return if (cause is FException) {
                cause
            } else {
                if (cause == null && message == null) {
                    FException(message = "unknown", cause = null)
                } else {
                    FException(message, cause)
                }
            }
        }
    }
}

fun Exception.isCancellation(): Boolean {
    return this is FExceptionCancellation
}

fun Exception.isStateLoading(): Boolean {
    return this is FExceptionStateLoading
}

fun Exception.isStateNone(): Boolean {
    return this is FExceptionStateNone
}