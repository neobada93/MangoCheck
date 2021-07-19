package com.todo.check.activity.usecase3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import com.todo.check.WebAppBaseActivity
import com.todo.check.WebAppInterface

class UseCase3Menu34Activity : WebAppBaseActivity() {

    // 지도
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addJavascriptInterface(WebAppInterface(this))
        val id = intent.getStringExtra(EXTRA_KEY_ID)
        val name = intent.getStringExtra(EXTRA_KEY_NAME)
        val addr = intent.getStringExtra(EXTRA_KEY_EXTRA_1)
        val map = mapOf("id" to id, "name" to name, "addr" to addr)
        //loadView("usecase3/menu34", map)
        val actionBar: ActionBar? = supportActionBar //제목줄 객체 얻어오기
        actionBar?.title = "${name}"
        actionBar?.setDisplayHomeAsUpEnabled(true) //업버튼 <- 만들기
        loadUrl("http://mangomap.brighten.co.kr/ex.asp?addr=${id}")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
