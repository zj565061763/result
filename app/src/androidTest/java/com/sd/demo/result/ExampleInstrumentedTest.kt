package com.sd.demo.result

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sd.lib.result.FResult
import com.sd.lib.result.exception.FExceptionLoading
import com.sd.lib.result.exception.isLoading
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
        val success = FResult.success("success")
        val failure = FResult.failure("failure")
        assertEquals(true, success == FResult.success("success"))
        assertEquals(false, failure == FResult.failure("failure"))
    }

    @Test
    fun testLoading() {
        val exception = FExceptionLoading()
        assertEquals(true, exception.isLoading())

        val loadingResult = FResult.loading("")
        assertEquals(true, loadingResult.isLoading())
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