package com.todo.check

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar
import com.todo.check.activity.NaverMap.NaverMapActivity
import com.todo.check.activity.common.LoginActivity
import com.todo.check.activity.usecase1.*
import com.todo.check.activity.usecase2.*
import com.todo.check.activity.usecase3.*
import com.todo.check.widget.WebAppView
import java.net.URLEncoder


abstract class WebAppBaseActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_KEY_ID = "EXTRA_KEY_ID"
        const val EXTRA_KEY_NAME = "EXTRA_KEY_NAME"
        const val EXTRA_KEY_DATE = "EXTRA_KEY_DATE"
        const val EXTRA_KEY_START_DATE = "EXTRA_KEY_START_DATE"
        const val EXTRA_KEY_END_DATE = "EXTRA_KEY_END_DATE"
        const val EXTRA_KEY_EXTRA_1 = "EXTRA_KEY_EXTRA_1"
        const val EXTRA_KEY_EXTRA_2 = "EXTRA_KEY_EXTRA_2"
        const val EXTRA_KEY_EXTRA_3 = "EXTRA_KEY_EXTRA_3"
        const val EXTRA_KEY_EXTRA_4 = "EXTRA_KEY_EXTRA_4"
    }

    private var toast: Toast? = null;
    protected lateinit var webAppView: WebAppView
    protected lateinit var progressBar: ContentLoadingProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_app_base)
        progressBar = findViewById(R.id.progressBar)
        webAppView = findViewById(R.id.webAppView)
    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        webAppView.onResume()
        // ...
    }

    @SuppressLint("NewApi")
    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun showToast(message: String, duration: Int) {
        toast?.let { it.cancel() }
        toast = Toast.makeText(this, message, duration)
        toast?.show()
    }

    private fun String.utf8(): String = URLEncoder.encode(this, "UTF-8")

    protected fun loadUrl(url: String) {
        webAppView.loadUrl(url)
    }

    protected fun loadView(version: String, name: String) {
        loadView(version, name, mapOf())
    }

    protected fun loadView(name: String) {
        loadView("v1", name, mapOf())
    }

    protected fun loadView(name: String, params: Map<String, String>) {
        loadView("v1", name, params)
    }

    protected fun loadView(version: String, name: String, params: Map<String, String>) {
        var url = "file:///android_asset/${version}/pages/${name}.html"
        if (params.isNotEmpty()) {
            url += "?" + params.map { (k, v) -> "${k.utf8()}=${v.utf8()}" }.joinToString("&")
        }
        webAppView.loadUrl(url)
    }

    @SuppressLint("JavascriptInterface")
    protected fun addJavascriptInterface(obj: Any) {
        webAppView.addJavascriptInterface(obj, "WebView")
    }

    var remember: String
        get() = App.prefs.remember
        set(value) {
            App.prefs.remember = value}

    var id: String
        get() = App.prefs.id
        set(value) {
            App.prefs.id = value}

    var grant: String
        get() = App.prefs.grant
        set(value) {
            App.prefs.grant = value}

    fun closeActivity() {
        finish()
    }

    fun showProgressBar() {
        runOnUiThread {
            progressBar.show()
        }
    }

    fun hideProgressBar() {
        runOnUiThread {
            progressBar.hide()
        }
    }

    fun scrollBottom() {
        runOnUiThread {
            webAppView.pageDown(true)
        }
    }

    // 로그인
    fun startLoginActivity() {
        Intent(this, LoginActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }

    // 망고3자물류 > 메인
    fun startUseCase1HomeActivity() {
        Intent(this, UseCase1HomeActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }

    // 망고3자물류 > 공지사항
    fun startUseCase1Menu1Activity() {
        Intent(this, UseCase1Menu1Activity::class.java).also {
            startActivity(it)
        }
    }

    // 망고3자물류 > 주문현황
    fun startUseCase1Menu2Activity() {
        Intent(this, UseCase1Menu2Activity::class.java).also {
            startActivity(it)
        }
    }

    // 망고3자물류 > 주문현황(상세)
    fun startUseCase1Menu21Activity(arg1: String, arg2: String, arg3: String, arg4: String) {
        Intent(this, UseCase1Menu21Activity::class.java).also {
            it.putExtra(
                EXTRA_KEY_ID
                , arg1
            )
            it.putExtra(
                EXTRA_KEY_NAME
                , arg2
            )
            it.putExtra(
                EXTRA_KEY_START_DATE
                , arg3
            )
            it.putExtra(
                EXTRA_KEY_END_DATE
                , arg4
            )
            startActivity(it)
        }
    }

    // 망고3자물류 > 단가정보
    fun startUseCase1Menu3Activity() {
        Intent(this, UseCase1Menu3Activity::class.java).also {
            startActivity(it)
        }
    }

    // 망고 경영정보 > 메인
    fun startUseCase2HomeActivity() {
        Intent(this, UseCase2HomeActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }

    // 망고 경영정보 > 주문현황
    fun startUseCase2Menu1Activity() {
        Intent(this, UseCase2Menu1Activity::class.java).also {
            startActivity(it)
        }
    }

    // 망고 경영정보 > 판매현황
    fun startUseCase2Menu2Activity() {
        Intent(this, UseCase2Menu2Activity::class.java).also {
            startActivity(it)
        }
    }

    // 망고 경영정보 > 이익현황
    fun startUseCase2Menu3Activity() {
        Intent(this, UseCase2Menu3Activity::class.java).also {
            startActivity(it)
        }
    }

    // 망고 경영정보 > 미수금현황
    fun startUseCase2Menu4Activity() {
        Intent(this, UseCase2Menu4Activity::class.java).also {
            startActivity(it)
        }
    }

    // 망고 경영정보 > 자금현항
    fun startUseCase2Menu5Activity() {
        Intent(this, UseCase2Menu5Activity::class.java).also {
            startActivity(it)
        }
    }

    // 망고 경영정보 > 자금현항상세
    fun startUseCase2Menu51Activity(arg1: String, arg2: String) {
        Intent(this, UseCase2Menu51Activity::class.java).also {
            it.putExtra(
                EXTRA_KEY_EXTRA_1
                , arg1
            )
            it.putExtra(
                EXTRA_KEY_EXTRA_2
                , arg2
            )
            startActivity(it)
        }
    }

    // 점검관리 > nfc
    fun startUseCase3NfcReaderActivity() {
        Intent(this, UseCase3NfcReaderActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }

    // 점검관리 > 메인
    fun startUseCase3HomeActivity() {
        Intent(this, UseCase3HomeActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }

    // 점검관리 > 평가이력
    fun startUseCase3Menu1Activity() {
        Intent(this, UseCase3Menu1Activity::class.java).also {
            startActivity(it)
        }
    }

    // 점검관리 > 가맹점정보
    fun startUseCase3Menu2Activity(arg1: String, arg2: String) {
        Intent(this, UseCase3Menu2Activity::class.java).also {
            it.putExtra(
                EXTRA_KEY_ID
                , arg1
            )
            it.putExtra(
                EXTRA_KEY_NAME
                , arg2
            )
            startActivity(it)
        }
    }

    // 점검관리 > 평가1
    fun startUseCase3Menu3Activity(arg1: String, arg2: String) {
        Intent(this, UseCase3Menu3Activity::class.java).also {
            it.putExtra(
                EXTRA_KEY_ID
                , arg1
            )
            it.putExtra(
                EXTRA_KEY_NAME
                , arg2
            )
            startActivity(it)
        }
    }

    // 점검관리 > 평가 완료
    fun startUseCase3Menu32Activity(arg1: String, arg2: String) {
        Intent(this, UseCase3Menu32Activity::class.java).also {
            it.putExtra(
                EXTRA_KEY_ID
                , arg1
            )
            it.putExtra(
                EXTRA_KEY_NAME
                , arg2
            )
            startActivity(it)
        }
    }

    // 점검관리 > 세부기준
    fun startUseCase3Menu33Activity(arg1: String, arg2: String, arg3: String, arg4: String, arg5: String) {
        Intent(this, UseCase3Menu33Activity::class.java).also {
            it.putExtra(
                EXTRA_KEY_ID
                , arg1
            )
            it.putExtra(
                EXTRA_KEY_EXTRA_1
                , arg2
            )
            it.putExtra(
                EXTRA_KEY_EXTRA_2
                , arg3
            )
            it.putExtra(
                EXTRA_KEY_EXTRA_3
                , arg4
            )
            it.putExtra(
                EXTRA_KEY_EXTRA_4
                , arg5
            )
            startActivity(it)
        }
    }

    // 점검관리 > 지도
    fun startUseCase3Menu34Activity(arg1: String, arg2: String, arg3: String) {
        Intent(this, UseCase3Menu34Activity::class.java).also {
            it.putExtra(
                EXTRA_KEY_ID
                , arg1
            )
            it.putExtra(
                EXTRA_KEY_NAME
                , arg2
            )
            it.putExtra(
                EXTRA_KEY_EXTRA_1
                , arg3
            )
            startActivity(it)
        }
    }

}
