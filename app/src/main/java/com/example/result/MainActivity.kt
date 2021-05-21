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
            Log.i(TAG, "runSuccess:${it}")
        }

        getResult(0).runFailure {
            Log.i(TAG, "runFailure:${it}")
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