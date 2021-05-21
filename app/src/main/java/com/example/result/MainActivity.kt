package com.example.result

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sd.lib.result.FResult
import com.sd.lib.result.runFailure
import com.sd.lib.result.runSuccess

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getResult(100).runSuccess {
            Log.i(TAG, "100 runSuccess:${it}")
        }
        getResult(100).runFailure {
            Log.i(TAG, "100 runFailure:${it}")
        }

        getResult(0).runSuccess {
            Log.i(TAG, "0 runSuccess:${it}")
        }
        getResult(0).runFailure {
            Log.i(TAG, "0 runFailure:${it}")
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