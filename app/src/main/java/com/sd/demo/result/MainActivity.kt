package com.sd.demo.result

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
    }
}

private fun testExceptionDesc() {
    logMsg { "1 ${FException()}" }
    logMsg { "2 ${FException(message = "error", cause = RuntimeException("runtime"))}" }

    logMsg { "--------------------" }

    logMsg { "1 ${FExceptionHttp()}" }
    logMsg { "2 ${FExceptionHttp(message = "error", cause = RuntimeException("runtime"))}" }
    logMsg { "3 ${FExceptionHttp(cause = SocketTimeoutException())}" }
    logMsg { "4 ${FExceptionHttpResponseCode(404)}" }
    logMsg { "5 ${FExceptionHttpParseResponse()}" }
}

inline fun logMsg(block: () -> String) {
    Log.i("result-demo", block())
}