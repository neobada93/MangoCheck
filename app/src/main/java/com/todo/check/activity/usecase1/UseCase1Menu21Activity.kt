package com.todo.check.activity.usecase1

import android.os.Bundle
import com.todo.check.WebAppBaseActivity
import com.todo.check.WebAppInterface

class UseCase1Menu21Activity : WebAppBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addJavascriptInterface(WebAppInterface(this))
        val id = intent.getStringExtra(EXTRA_KEY_ID)
        val name = intent.getStringExtra(EXTRA_KEY_NAME)
        val startDate = intent.getStringExtra(EXTRA_KEY_START_DATE)
        val endDate = intent.getStringExtra(EXTRA_KEY_END_DATE)
        val map = mapOf("id" to id, "name" to name, "startDate" to startDate, "endDate" to endDate)
        loadView("usecase1/menu21", map)
    }
}
