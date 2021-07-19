package com.todo.check.activity.usecase3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.todo.check.WebAppBaseActivity
import com.todo.check.WebAppInterface

class UseCase3Menu33Activity : WebAppBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        addJavascriptInterface(WebAppInterface(this))
        val id = intent.getStringExtra(EXTRA_KEY_ID)
        val scateNm = intent.getStringExtra(EXTRA_KEY_EXTRA_1)
        val bcateCd = intent.getStringExtra(EXTRA_KEY_EXTRA_2)
        val mcateCd = intent.getStringExtra(EXTRA_KEY_EXTRA_3)
        val scateCd = intent.getStringExtra(EXTRA_KEY_EXTRA_4)
        val map = mapOf("id" to id, "scateNm" to scateNm, "bcateCd" to bcateCd, "mcateCd" to mcateCd, "scateCd" to scateCd)
        loadView("usecase3/menu33", map)
    }
}
