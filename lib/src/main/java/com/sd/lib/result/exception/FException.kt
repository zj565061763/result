package com.sd.lib.result.exception

open class FException : Exception {
    @JvmOverloads
    constructor(message: String? = "", cause: Throwable? = null) : super(message, cause)

    override fun toString(): String {
        val superMessage = localizedMessage ?: ""
        val causeMessage = cause?.toString() ?: ""
        return superMessage + causeMessage
    }
}