package com.sd.lib.result

import com.sd.lib.result.exception.FException

class FResult<T> internal constructor(val data: T?, val exception: Exception?) {
    val isSuccess: Boolean
    val isFailure: Boolean

    init {
        val success = data != null && exception == null
        val failure = data == null && exception != null

        assert(success || failure)
        if (success && failure) throw RuntimeException("success and failure")

        isSuccess = success
        isFailure = failure
    }

    override fun hashCode(): Int {
        return arrayOf(isSuccess, isFailure, data, exception).contentHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is FResult<*>) return false
        return isSuccess == other.isSuccess &&
                isFailure == other.isFailure &&
                data == other.data &&
                exception == other.exception
    }

    companion object {
        @JvmStatic
        fun <T> success(data: T): FResult<T> {
            return FResult(data, null)
        }

        @JvmStatic
        fun <T> failure(message: String? = ""): FResult<T> {
            return failure(FException(message = message))
        }

        @JvmStatic
        fun <T> failure(result: FResult<*>): FResult<T> {
            assert(result.isFailure)
            return failure(result.exception!!)
        }

        @JvmStatic
        fun <T> failure(exception: Exception?): FResult<T> {
            return FResult(null, exception ?: FException(message = "unknown"))
        }
    }
}