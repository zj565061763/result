package com.sd.lib.result

import com.sd.lib.result.exception.FException
import com.sd.lib.result.exception.FExceptionStateLoading
import com.sd.lib.result.exception.isStateLoading
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

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

    fun isStateLoading(): Boolean {
        return this is Failure && this.exception.isStateLoading()
    }

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
        fun failure(cause: Throwable?): Failure {
            val exception = if (cause is FException) cause else FException(cause = cause)
            return Failure(exception)
        }

        @JvmStatic
        fun stateLoading(msg: String? = ""): Failure {
            return Failure(FExceptionStateLoading(msg))
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

fun <T> Result<T>.toFResult(): FResult<T> {
    return if (isSuccess) {
        FResult.success(getOrNull()!!)
    } else {
        FResult.failure(exceptionOrNull()!!)
    }
}