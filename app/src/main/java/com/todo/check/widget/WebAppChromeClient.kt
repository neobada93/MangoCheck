package com.todo.check.widget

import android.content.Context
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import com.todo.check.Utils

open class WebAppChromeClient(val context: Context) : WebChromeClient() {

    override fun onJsAlert(
        view: WebView,
        url: String?,
        message: String?,
        result: JsResult
    ): Boolean {
        val dialog: AlertDialog =
            AlertDialog.Builder(view.context).setTitle(Utils.projectName(context))
                .setMessage(message)
                .setPositiveButton(
                    "OK"
                ) { _, _ ->
                    //do nothing
                }.create()
        dialog.show()
        result.confirm()
        return true
    }

    override fun onConsoleMessage(message: String, lineNumber: Int, sourceID: String) {
        Log.d("WebAppView", "$message -- From line $lineNumber of $sourceID")
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        consoleMessage?.apply {
            Log.d(
                "WebAppView",
                "${message()} -- From line ${lineNumber()} of ${sourceId()}"
            )
        }
        return true
    }

}