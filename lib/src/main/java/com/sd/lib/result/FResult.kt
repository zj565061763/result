package com.sd.lib.result

import com.sd.lib.result.exception.FException

class FResult<T> internal constructor(val data: T?, val failure: Exception?) {
    val isSuccess: Boolean
    val isFailure: Boolean

    init {
        val success = data != null && failure == null
        val failure = data == null && failure != null

        assert(success || failure)
        if (success && failure) throw RuntimeException("success and failure")

        isSuccess = success
        isFailure = failure
    }

    override fun hashCode(): Int {
        return arrayOf(isSuccess, isFailure, data, failure).contentHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is FResult<*>) return false
        return isSuccess == other.isSuccess &&
                isFailure == other.isFailure &&
                data == other.data &&
                failure == other.failure
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
            return failure(result.failure!!)
        }

        @JvmStatic
        fun <T> failure(exception: Exception?): FResult<T> {
            return FResult(null, exception ?: FException(message = "unknown"))
        }
    }
}