package com.todo.check.activity.usecase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.todo.check.WebAppBaseActivity
import com.todo.check.WebAppInterface

class UseCase2Menu51Activity : WebAppBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addJavascriptInterface(WebAppInterface(this))
        val bankCd = intent.getStringExtra(EXTRA_KEY_EXTRA_1)
        val udt = intent.getStringExtra(EXTRA_KEY_EXTRA_2)
        val map = mapOf("bankCd" to bankCd, "udt" to udt)
        loadView("usecase2/menu51", map)
    }
}
