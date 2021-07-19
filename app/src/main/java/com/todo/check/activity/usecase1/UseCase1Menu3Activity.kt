package com.todo.check.activity.usecase1

import android.os.Bundle
import com.todo.check.WebAppBaseActivity
import com.todo.check.WebAppInterface

class UseCase1Menu3Activity : WebAppBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addJavascriptInterface(WebAppInterface(this))
        loadView("usecase1/menu3")
    }
}
