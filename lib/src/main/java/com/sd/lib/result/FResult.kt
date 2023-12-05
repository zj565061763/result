package com.sd.lib.result

import com.sd.lib.result.exception.FException
import com.sd.lib.result.exception.FExceptionCancellation
import com.sd.lib.result.exception.FExceptionLoading
import kotlin.coroutines.cancellation.CancellationException
import kotlin.reflect.KClass

fun <T> fResultFailure(throwable: Throwable?): Result<T> = Result.failure(FException.wrap(throwable))

fun <T> fResultFailure(message: String?): Result<T> = Result.failure(FException(message = message))

fun <T> fResultLoading(message: String? = null): Result<T> = Result.failure(FExceptionLoading(message = message))

fun <T> fResultCancellation(
    message: String? = null,
    cause: Throwable? = null,
): Result<T> = Result.failure(FExceptionCancellation(message = message, cause = cause))

fun <T> Result<T>.fIsLoading(): Boolean {
    return exceptionOrNull() is FExceptionLoading
}

fun <T> Result<T>.fIsCancellation(): Boolean {
    return exceptionOrNull() is FExceptionCancellation
}

inline fun <T> fCatching(
    escape: KClass<out Throwable>? = CancellationException::class,
    block: () -> T,
): Result<T> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        if (e::class == escape) throw e
        fResultFailure(e)
    }
}

fun <T> Result<T>.fResult(): Result<T> {
    if (this.isSuccess) return this
    val exception = checkNotNull(this.exceptionOrNull())
    return fResultFailure(exception)
}

fun <R, T> Result<T>.fFailure(): Result<R> {
    val exception = exceptionOrNull() ?: error("This method should be called when Result.isFailure")
    return Result.failure(exception)
}