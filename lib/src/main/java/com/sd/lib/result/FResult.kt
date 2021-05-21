package com.sd.lib.result

import com.sd.lib.result.exception.FException

@Deprecated("")
sealed class FResult<out R> {

    @Deprecated("")
    data class Success<out T> internal constructor(val data: T) : FResult<T>()

    @Deprecated("")
    data class Failure internal constructor(val exception: Exception) : FResult<Nothing>()

    companion object {
        @Deprecated("")
        @JvmStatic
        fun <T> success(data: T): Success<T> {
            return Success(data)
        }

        @Deprecated("")
        @JvmStatic
        fun failure(message: String?): Failure {
            return Failure(FException(message = message))
        }

        @Deprecated("")
        @JvmStatic
        fun failure(exception: Exception?): Failure {
            return Failure(exception ?: FException(message = "unknown"))
        }
    }
}

@Deprecated("")
inline fun <T> FResult<T>.onSuccess(block: (T) -> Unit): FResult<T> {
    if (this is FResult.Success<T>) {
        block(data)
    }
    return this
}

@Deprecated("")
inline fun FResult<*>.onFailure(block: (Exception) -> Unit): FResult<*> {
    if (this is FResult.Failure) {
        block(exception)
    }
    return this
}