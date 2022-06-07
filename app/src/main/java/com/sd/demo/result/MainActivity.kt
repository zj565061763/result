package com.sd.demo.result

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sd.lib.result.FResult
import com.sd.lib.result.isFailure
import com.sd.lib.result.isSuccess

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        val loadingResult = FResult.loading("")
        Log.i(TAG, "isLoading:${loadingResult.isLoading()}")

        val successResult = getResult(100)
        if (successResult.isSuccess()) {
            Log.i(TAG, "isSuccess:${successResult.data}")
        }

        val failureResult = getResult(0)
        if (failureResult.isFailure()) {
            Log.i(TAG, "isFailure:${failureResult.exception}")
        }
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