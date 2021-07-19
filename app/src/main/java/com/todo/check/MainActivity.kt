package com.todo.check

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.todo.check.activity.common.LoginActivity
import com.todo.check.activity.usecase1.UseCase1HomeActivity
import com.todo.check.activity.usecase2.UseCase2HomeActivity
import com.todo.check.activity.usecase3.UseCase3HomeActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("GRANT","grant: ${grant}")
        when (grant) {
            "4" -> { // 점검관리
                Intent(this, UseCase3HomeActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            "6" -> { // 3자물류
                Intent(this, UseCase1HomeActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            "7" -> { //경영정보
                Intent(this, UseCase2HomeActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
            else -> {// 로그인
                Intent(this, LoginActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    var grant: String
        get() = App.prefs.grant
        set(value) {
            App.prefs.grant = value
        }
}
