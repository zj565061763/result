package com.sd.demo.result

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sd.lib.result.*
import com.sd.lib.result.exception.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun testResultSuccess() {
        assertEquals("success", FResult.success("success").data)

        val result = getResult(100)
        assertEquals(false, result is FResult.Failure)
        assertEquals(true, result is FResult.Success<Int>)
        assertEquals(100, (result as FResult.Success<Int>).data)

        var count = 1
        result.onSuccess {
            assertEquals(100, it)
            count += it
        }
        assertEquals(101, count)
    }

    @Test
    fun testResultFailure() {
        assertEquals("failure", FResult.failure("failure").exception.toString())

        val result = getResult(0)
        assertEquals(false, result is FResult.Success<Int>)
        assertEquals(true, result is FResult.Failure)
        assertEquals("error", (result as FResult.Failure).exception.toString())

        var content = "error"
        result.onFailure {
            assertEquals("error", it.toString())
            content += content
        }
        assertEquals("errorerror", content)
    }

    @Test
    fun testResultEquals() {
        assertEquals(true, FResult.success("success") == FResult.success("success"))
        assertEquals(false, FResult.failure("failure") == FResult.failure("failure"))
    }

    @Test
    fun testIsSuccess() {
        assertEquals(true, getResult(100).isSuccess())
        assertEquals(false, getResult(0).isSuccess())

        getResult(100).let {
            if (it.isSuccess()) {
                assertEquals(100, it.data)
            }
        }
    }

    @Test
    fun testIsFailure() {
        assertEquals(true, getResult(0).isFailure())
        assertEquals(false, getResult(100).isFailure())

        getResult(0).let {
            if (it.isFailure()) {
                assertEquals("error", it.exception.toString())
            }
        }
    }

    @Test
    fun testIsLoading() {
        val result = FResult.loading("loading")
        assertEquals(true, result.isLoading())
        assertEquals(true, result.isFailure())
        assertEquals(false, result.isSuccess())
        assertEquals("loading", (result as FResult.Failure).exception.toString())

        assertEquals(false, FResult.success("").isLoading())
        assertEquals(false, FResult.failure("").isLoading())
        assertEquals(true, FResult.failure(FExceptionLoading()).isLoading())
    }

    @Test
    fun testFCatchingSuccess() {
        testCatchingResultSuccess(
            fCatching { "success" }
        )
    }

    @Test
    fun testFCatchingFailure() {
        testCatchingResultFailure(
            fCatching { throw RuntimeException() }
        )
    }

    @Test
    fun testFException() {
        assertEquals("", FException().toString())
        assertEquals("hello", FException(message = "hello").toString())
        assertEquals("hello java.lang.RuntimeException", FException(message = "hello", cause = RuntimeException()).toString())
    }


    private fun testCatchingResultSuccess(result: FResult<String>) {
        assertEquals(true, result.isSuccess())
        assertEquals(false, result.isFailure())
        assertEquals("success", (result as FResult.Success<String>).data)
    }

    private fun testCatchingResultFailure(result: FResult<String>) {
        assertEquals(true, result.isFailure())
        assertEquals(false, result.isSuccess())
        assertEquals("java.lang.RuntimeException", (result as FResult.Failure).exception.toString())
    }

    companion object {
        private fun getResult(value: Int): FResult<Int> {
            return if (value == 100) {
                FResult.success(value)
            } else {
                FResult.failure("error")
            }
        }
    }
}