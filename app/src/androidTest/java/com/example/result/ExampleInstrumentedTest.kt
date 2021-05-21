package com.example.result

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sd.lib.result.FResult
import com.sd.lib.result.runFailure
import com.sd.lib.result.runSuccess
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
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.result", appContext.packageName)
    }

    @Test
    fun testResultSuccess() {
        val result = FResult.success("success")
        assertEquals("success", result.data)
    }

    @Test
    fun testResultFailure() {
        val result = FResult.failure("failure")
        assertEquals("failure", result.exception.toString())
    }

    @Test
    fun testResult() {
        val success = getResult(100)
        assertEquals(true, success is FResult.Success<Int>)
        assertEquals(100, (success as FResult.Success<Int>).data)
        assertEquals(100, success.runSuccess { assertEquals(100, it) })

        val failure = getResult(0)
        assertEquals(true, failure is FResult.Failure)
        assertEquals("error", (failure as FResult.Failure).exception.toString())
        assertEquals("error", failure.runFailure { assertEquals("error", it.toString()) }.toString())
    }

    @Test
    fun testResultEquals() {
        val success = FResult.success("success")
        val failure = FResult.failure("failure")
        assertEquals(true, success == FResult.success("success"))
        assertEquals(false, failure == FResult.failure("failure"))
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