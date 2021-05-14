package com.sd.lib.result.exception

/**
 * 状态码异常
 */
open class FExceptionCode : FException {
    val code: Int

    @JvmOverloads
    constructor(code: Int, message: String? = "", cause: Throwable? = null) : super(message, cause) {
        this.code = code
    }

    override fun toString(): String {
        return "${super.toString()} code:${code}"
    }
}