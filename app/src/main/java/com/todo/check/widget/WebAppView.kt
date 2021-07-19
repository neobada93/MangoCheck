package com.todo.check.widget

import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.webkit.*
import com.todo.check.Utils


class WebAppView : WebView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        settings.apply {
            javaScriptEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING

            domStorageEnabled = true
            allowContentAccess = true
            allowUniversalAccessFromFileURLs = true
            builtInZoomControls = false
            domStorageEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            setSupportMultipleWindows(true)
            mediaPlaybackRequiresUserGesture = false
        }

        setInitialScale(1)
        setOnLongClickListener { true }
        isLongClickable = false
        isHapticFeedbackEnabled = false

        webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                view?.visibility = View.INVISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Handler().postDelayed({
                    view?.visibility = View.VISIBLE
                }, 500)
            }
        }
        webChromeClient = object : WebChromeClient() {

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

            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                //return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
                return true;
            }
        }
    }
}