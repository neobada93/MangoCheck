package com.todo.check.activity.usecase3

import android.os.Bundle
import com.todo.check.WebAppBaseActivity
import com.todo.check.WebAppInterface

class UseCase3HomeActivity : WebAppBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addJavascriptInterface(WebAppInterface(this))
        loadView("usecase3/home")
    }
}
