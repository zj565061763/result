package com.sd.lib.result

import com.sd.lib.result.exception.FException

sealed class FResult<out R> {
    data class Success<out T> internal constructor(val data: T) : FResult<T>()

    data class Failure internal constructor(val exception: Exception) : FResult<Nothing>()

    companion object {
        @JvmStatic
        fun <T> success(data: T): Success<T> {
            return Success(data)
        }

        @JvmStatic
        fun failure(message: String? = ""): Failure {
            return Failure(FException(message = message))
        }

        @JvmStatic
        fun failure(exception: Exception?): Failure {
            return Failure(exception ?: FException(message = "unknown"))
        }
    }
}