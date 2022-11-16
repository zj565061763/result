package com.sd.demo.result

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sd.lib.result.FResult
import com.sd.lib.result.exception.*
import com.sd.lib.result.isFailure
import com.sd.lib.result.isSuccess
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
    }

    @Test
    fun testIsFailure() {
        assertEquals(true, getResult(0).isFailure())
        assertEquals(false, getResult(100).isFailure())
    }

    @Test
    fun testIsLoading() {
        val result = FResult.loading("loading")
        assertEquals(true, result.isFailure())
        assertEquals(false, result.isSuccess())
        assertEquals("loading", result.exception.toString())
    }

    @Test
    fun testFException() {
        assertEquals("", FException().toString())
        assertEquals("hello", FException(message = "hello").toString())
        assertEquals("hello java.lang.RuntimeException", FException(message = "hello", cause = RuntimeException()).toString())
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