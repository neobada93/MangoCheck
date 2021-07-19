package com.todo.check

import android.util.Log
import android.webkit.JavascriptInterface
import com.todo.check.App
import com.todo.check.WebAppBaseActivity

class WebAppInterface(private val activity: WebAppBaseActivity) {

    @JavascriptInterface
    fun setRemember(id: String) {
        App.prefs.remember = id
        Log.e("setRemember", id)
    }

    @JavascriptInterface
    fun getRemember(): String = App.prefs.remember

    @JavascriptInterface
    fun setId(id: String) {
        App.prefs.id = id
        Log.e("setId", id)
    }

    @JavascriptInterface
    fun getId(): String = App.prefs.id

    @JavascriptInterface
    fun setGrant(grant: String) {
        App.prefs.grant = grant
        Log.e("setGrant", grant)
    }

    @JavascriptInterface
    fun getGrant(): String = App.prefs.grant

    @JavascriptInterface
    fun setName(name: String) {
        App.prefs.name = name
        Log.e("setName", name)
    }

    @JavascriptInterface
    fun getName(): String = App.prefs.name

    @JavascriptInterface
    fun scrollBottom() {
        activity.scrollBottom()
    }

    @JavascriptInterface
    fun closeActivity() {
        activity.closeActivity()
    }

    @JavascriptInterface
    fun showLoading() {
        activity.showProgressBar()
    }

    @JavascriptInterface
    fun hideLoading() {
        activity.hideProgressBar()
    }

    // 로그인
    @JavascriptInterface
    fun openLoginActivity() {
        activity.startLoginActivity();
    }

    // 3자 물류 > 홈
    @JavascriptInterface
    fun openUseCase1HomeActivity() {
        activity.startUseCase1HomeActivity()
    }

    // 3자 물류 > 공지사항
    @JavascriptInterface
    fun openUseCase1Menu1Activity() {
        activity.startUseCase1Menu1Activity()
    }

    // 3자 물류 > 주문현황
    @JavascriptInterface
    fun openUseCase1Menu2Activity() {
        activity.startUseCase1Menu2Activity()
    }

    // 3자 물류 > 주문현황(상세)
    @JavascriptInterface
    fun openUseCase1Menu21Activity(arg1: String, arg2: String, arg3: String, arg4: String) {
        activity.startUseCase1Menu21Activity(arg1, arg2, arg3, arg4)
    }

    // 3자 물류 > 주문현황
    @JavascriptInterface
    fun openUseCase1Menu3Activity() {
        activity.startUseCase1Menu3Activity()
    }

    // 경영정보 > 홈
    @JavascriptInterface
    fun openUseCase2HomeActivity() {
        activity.startUseCase2HomeActivity()
    }

    // 경영정보 > 주문현황
    @JavascriptInterface
    fun openUseCase2Menu1Activity() {
        activity.startUseCase2Menu1Activity()
    }

    // 경영정보 > 판매현황
    @JavascriptInterface
    fun openUseCase2Menu2Activity() {
        activity.startUseCase2Menu2Activity()
    }

    // 경영정보 > 이익현황
    @JavascriptInterface
    fun openUseCase2Menu3Activity() {
        activity.startUseCase2Menu3Activity()
    }

    // 경영정보 > 미수금현황
    @JavascriptInterface
    fun openUseCase2Menu4Activity() {
        activity.startUseCase2Menu4Activity()
    }

    // 경영정보 > 자금현황
    @JavascriptInterface
    fun openUseCase2Menu5Activity() {
        activity.startUseCase2Menu5Activity()
    }

    // 경영정보 > 자금현황상세
    @JavascriptInterface
    fun openUseCase2Menu51Activity(arg1: String, arg2: String) {
        activity.startUseCase2Menu51Activity(arg1, arg2)
    }

    // 점검관리 > nfc reader
    @JavascriptInterface
    fun openUseCase3NfcReaderActivity() {
        activity.startUseCase3NfcReaderActivity()
    }

    // 점검관리 > 메인
    @JavascriptInterface
    fun openUseCase3HomeActivity() {
        activity.startUseCase3HomeActivity()
    }

    // 점검관리 > 평가이력
    @JavascriptInterface
    fun openUseCase3Menu1Activity() {
        activity.startUseCase3Menu1Activity()
    }

    // 점검관리 > 가맹점정보
    @JavascriptInterface
    fun openUseCase3Menu2Activity(arg1: String, arg2: String) {
        activity.startUseCase3Menu2Activity(arg1, arg2)
    }
    // 점검관리 > 평가
    @JavascriptInterface
    fun openUseCase3Menu3Activity(arg1: String, arg2: String) {
        activity.startUseCase3Menu3Activity(arg1, arg2)
    }
    // 점검관리 > 평가완료
    @JavascriptInterface
    fun openUseCase3Menu32Activity(arg1: String, arg2: String) {
        activity.startUseCase3Menu32Activity(arg1, arg2)
    }
    // 점검관리 > 세부기준
    @JavascriptInterface
    fun openUseCase3Menu33Activity(arg1: String, arg2: String, arg3: String, arg4: String, arg5: String) {
        activity.startUseCase3Menu33Activity(arg1, arg2, arg3, arg4, arg5)
    }
    // 점검관리 > 지도
    @JavascriptInterface
    fun openUseCase3Menu34Activity(arg1: String, arg2: String, arg3: String) {
        activity.startUseCase3Menu34Activity(arg1, arg2, arg3)
    }
}