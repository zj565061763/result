package com.example.result

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sd.lib.result.FResult
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
        assertEquals(true, result.isSuccess)
        assertEquals(false, result.isFailure)
        assertEquals("success", result.data)
        assertEquals(null, result.exception)
    }

    @Test
    fun testResultFailure() {
        val result = FResult.failure<String>("failure")
        assertEquals(true, result.isFailure)
        assertEquals(false, result.isSuccess)
        assertEquals(null, result.data)
        assertEquals("failure", result.exception!!.toString())

        val resultCopy = FResult.failure<String>(result)
        assertEquals(result.exception, resultCopy.exception)
        assertEquals(result, resultCopy)
    }

    @Test
    fun testResultEquals() {
        val resultSuccess = FResult.success("success")
        val resultFailure = FResult.failure<String>("failure")
        assertEquals(false, resultSuccess == resultFailure)
        assertEquals(true, resultSuccess == FResult.success("success"))
        assertEquals(false, resultFailure == FResult.failure<String>("failure"))
    }

    @Test
    fun testResultSet() {
        val set = mutableSetOf<FResult<*>>()

        val resultSuccess = FResult.success("success")
        val resultFailure = FResult.failure<String>("failure")

        set.clear()
        set.add(resultSuccess)
        set.add(resultFailure)
        assertEquals(2, set.size)
        assertEquals(true, set.contains(FResult.success("success")))

        val add = set.add(FResult.success("success"))
        assertEquals(false, add)
        assertEquals(2, set.size)

        val failure = FResult.failure<String>("failure")
        assertEquals(false, set.contains(failure))

        set.add(failure)
        assertEquals(true, set.contains(failure))
        assertEquals(3, set.size)
    }
}