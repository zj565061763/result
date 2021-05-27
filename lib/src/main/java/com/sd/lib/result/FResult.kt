package com.sd.lib.result

import com.sd.lib.result.exception.FException

sealed class FResult<out R> {

    fun onSuccess(block: (R) -> Unit) {
        if (this is Success<R>) {
            block(data)
        }
    }

    fun onFailure(block: (Exception) -> Unit) {
        if (this is Failure) {
            block(exception)
        }
    }

    data class Success<out T> internal constructor(val data: T) : FResult<T>()

    data class Failure internal constructor(val exception: Exception) : FResult<Nothing>()

    companion object {
        @JvmStatic
        fun <T> success(data: T): Success<T> {
            return Success(data)
        }

        @JvmStatic
        fun failure(message: String?): Failure {
            return Failure(FException(message = message))
        }

        @JvmStatic
        fun failure(exception: Exception?): Failure {
            return Failure(exception ?: FException(message = "unknown"))
        }
    }
}

@Deprecated("")
inline fun <T> FResult<T>.onSuccess(block: (T) -> Unit) {
    if (this is FResult.Success<T>) {
        block(data)
    }
}

@Deprecated("")
inline fun FResult<*>.onFailure(block: (Exception) -> Unit) {
    if (this is FResult.Failure) {
        block(exception)
    }
}