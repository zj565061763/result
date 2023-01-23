package com.sd.lib.result

import com.sd.lib.result.exception.FException
import com.sd.lib.result.exception.FExceptionLoading
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.coroutines.cancellation.CancellationException
import kotlin.reflect.KClass

sealed class FResult<out R> {

    inline fun onSuccess(block: (R) -> Unit) {
        if (this is Success<R>) {
            block(data)
        }
    }

    inline fun onFailure(block: (Exception) -> Unit) {
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
        fun failure(message: String? = null): Failure {
            return Failure(FException(message = message))
        }

        @JvmStatic
        fun failure(cause: Throwable?): Failure {
            return Failure(FException.wrap(cause))
        }

        @JvmStatic
        fun loading(msg: String? = null): Failure {
            return Failure(FExceptionLoading(msg))
        }
    }
}

@OptIn(ExperimentalContracts::class)
fun <T> FResult<T>.isSuccess(): Boolean {
    contract {
        returns(true) implies (this@isSuccess is FResult.Success<T>)
        returns(false) implies (this@isSuccess is FResult.Failure)
    }
    return this is FResult.Success<T>
}

@OptIn(ExperimentalContracts::class)
fun <T> FResult<T>.isFailure(): Boolean {
    contract {
        returns(true) implies (this@isFailure is FResult.Failure)
        returns(false) implies (this@isFailure is FResult.Success<T>)
    }
    return this is FResult.Failure
}

fun <T> FResult<T>.isLoading(): Boolean {
    return this is FResult.Failure && this.exception is FExceptionLoading
}

inline fun <R> fCatching(
    ignore: KClass<out Throwable>? = CancellationException::class,
    block: () -> R,
): FResult<R> {
    return try {
        FResult.success(block())
    } catch (e: Throwable) {
        if (e::class == ignore) throw e
        FResult.failure(e)
    }
}

fun <T> Result<T>.toFResult(): FResult<T> {
    return if (isSuccess) {
        FResult.success(getOrNull()!!)
    } else {
        FResult.failure(exceptionOrNull()!!)
    }
}

fun <T> FResult<T>.toResult(): Result<T> {
    return if (isSuccess()) {
        Result.success(data)
    } else {
        Result.failure(exception)
    }
}