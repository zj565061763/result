package com.sd.demo.result

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sd.lib.result.FResult

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

        val loading = FResult.loading("")
        Log.i(TAG, "isLoading:${loading.isLoading()}")
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