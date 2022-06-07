package com.sd.lib.result

import com.sd.lib.result.exception.FException
import com.sd.lib.result.exception.FExceptionLoading
import com.sd.lib.result.exception.isLoading
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

    fun isLoading(): Boolean {
        return this is Failure && this.exception.isLoading()
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

        @JvmStatic
        fun loading(msg: String? = ""): Failure {
            return Failure(FExceptionLoading(msg))
        }
    }
}

@OptIn(ExperimentalContracts::class)
fun FResult<*>.isSuccess(): Boolean {
    contract {
        returns(true) implies (this@isSuccess is FResult.Success)
    }
    return this is FResult.Success
}

@OptIn(ExperimentalContracts::class)
fun FResult<*>.isFailure(): Boolean {
    contract {
        returns(true) implies (this@isFailure is FResult.Failure)
    }
    return this is FResult.Failure
}