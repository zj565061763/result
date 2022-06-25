package com.sd.demo.result

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sd.lib.result.FResult
import com.sd.lib.result.exception.FException
import com.sd.lib.result.isFailure
import com.sd.lib.result.isSuccess
import com.sd.lib.result.toFResult

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testExceptionDesc()

        getResult(100).onSuccess {
            Log.i(TAG, "100 onSuccess:${it}")
        }
        getResult(100).onFailure {
            Log.i(TAG, "100 onFailure:${it}")
        }

        getResult(0).onSuccess {
            Log.i(TAG, "0 onSuccess:${it}")
        }
        getResult(0).onFailure {
            Log.i(TAG, "0 onFailure:${it}")
        }

        val loadingResult = FResult.loading("loading")
        Log.i(TAG, "isLoading:${loadingResult.isLoading()}")

        val result100 = getResult(100)
        if (result100.isSuccess()) {
            Log.i(TAG, "isSuccess:${result100.data}")
        } else {
            Log.i(TAG, "isFailure:${result100.exception}")
        }

        val result0 = getResult(0)
        if (result0.isFailure()) {
            Log.i(TAG, "isFailure:${result0.exception}")
        } else {
            Log.i(TAG, "isSuccess:${result0.data}")
        }

        runCatching {
            "success"
        }.toFResult().onSuccess {
            Log.i(TAG, "toFResult onSuccess:${it}")
        }

        runCatching {
            throw RuntimeException("error")
        }.toFResult().onFailure {
            Log.i(TAG, "toFResult onFailure:${it}")
        }
    }

    private fun testExceptionDesc() {
        Log.i(TAG, "1 " + FException().toString())
        Log.i(TAG, "2 " + FException(message = "hello", cause = RuntimeException("runtime")).toString())
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