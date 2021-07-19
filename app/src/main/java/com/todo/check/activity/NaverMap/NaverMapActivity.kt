package com.todo.check.activity.NaverMap

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.todo.check.R
import com.todo.check.WebAppBaseActivity

class NaverMapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_naver_map)
        val name = intent.getStringExtra(WebAppBaseActivity.EXTRA_KEY_NAME)

        val actionBar: ActionBar? = supportActionBar //제목줄 객체 얻어오기
        actionBar?.title = "${name}"
        actionBar?.setDisplayHomeAsUpEnabled(true) //업버튼 <- 만들기

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
