package com.sd.demo.result

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sd.lib.result.*
import com.sd.lib.result.exception.FException
import com.sd.lib.result.exception.http.FExceptionHttp
import com.sd.lib.result.exception.http.FExceptionHttpParseResponse
import com.sd.lib.result.exception.http.FExceptionHttpResponseCode
import java.net.SocketTimeoutException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testExceptionDesc()

        getResult(100).onSuccess {
            logMsg { "100 onSuccess:${it}" }
        }
        getResult(100).onFailure {
            logMsg { "100 onFailure:${it}" }
        }

        getResult(0).onSuccess {
            logMsg { "0 onSuccess:${it}" }
        }
        getResult(0).onFailure {
            logMsg { "0 onFailure:${it}" }
        }

        val loadingResult = FResult.loading("loading")
        logMsg { "isLoading:${loadingResult.isLoading()}" }

        val result100 = getResult(100)
        if (result100.isSuccess()) {
            logMsg { "isSuccess:${result100.data}" }
        } else {
            logMsg { "isFailure:${result100.exception}" }
        }

        val result0 = getResult(0)
        if (result0.isFailure()) {
            logMsg { "isFailure:${result0.exception}" }
        } else {
            logMsg { "isSuccess:${result0.data}" }
        }

        runCatching {
            "success"
        }.toFResult().onSuccess {
            logMsg { "toFResult onSuccess:${it}" }
        }

        runCatching {
            throw RuntimeException("error")
        }.toFResult().onFailure {
            logMsg { "toFResult onFailure:${it}" }
        }
    }

    private fun testExceptionDesc() {
        logMsg { "1 " + FException().toString() }
        logMsg { "2 " + FException(message = "error", cause = RuntimeException("runtime")).toString() }

        logMsg { "--------------------" }

        logMsg { "1 " + FExceptionHttp().toString() }
        logMsg { "2 " + FExceptionHttp(message = "error", cause = RuntimeException("runtime")).toString() }
        logMsg { "3 " + FExceptionHttp(cause = SocketTimeoutException()).toString() }
        logMsg { "4 " + FExceptionHttpResponseCode(404).toString() }
        logMsg { "5 " + FExceptionHttpParseResponse().toString() }
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

fun logMsg(block: () -> String) {
    Log.i("FResult-demo", block())
}