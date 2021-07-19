package com.todo.check.activity.usecase3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.todo.check.AttachPhotoActivity
import com.todo.check.WebAppBaseActivity
import com.todo.check.WebAppInterface

class UseCase3Menu3Activity : AttachPhotoActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addJavascriptInterface(WebAppInterface(this))
        val id = intent.getStringExtra(EXTRA_KEY_ID)
        val name = intent.getStringExtra(EXTRA_KEY_NAME)
        val map = mapOf("id" to id, "name" to name)
        loadView("usecase3/menu3", map)
    }
}
