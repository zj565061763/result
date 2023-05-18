package com.sd.demo.result

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sd.demo.result.ui.theme.AppTheme
import com.sd.lib.result.exception.FException
import com.sd.lib.result.exception.http.FExceptionHttp
import com.sd.lib.result.exception.http.FExceptionHttpParseResponse
import com.sd.lib.result.exception.http.FExceptionHttpResponseCode
import java.net.SocketTimeoutException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Content()
            }
        }
    }
}

@Composable
private fun Content() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Button(
            onClick = {
                testExceptionDesc()
            }
        ) {
            Text(text = "button")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    AppTheme {
        Content()
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
    Log.i("FResult-demo", block())
}