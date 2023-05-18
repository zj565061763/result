package com.sd.lib.result

import com.sd.lib.result.exception.FException
import com.sd.lib.result.exception.FExceptionLoading

fun <T> fResultSuccess(value: T): Result<T> = Result.success(value)

fun <T> fResultFailure(exception: Throwable?): Result<T> = Result.failure(FException.wrap(exception))

fun <T> fResultFailure(message: String? = null): Result<T> = Result.failure(FException(message = message))

fun <T> fResultLoading(message: String? = null): Result<T> = Result.failure(FExceptionLoading(message = message))