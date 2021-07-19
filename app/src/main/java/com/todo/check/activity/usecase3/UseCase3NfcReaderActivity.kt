package com.todo.check.activity.usecase3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.todo.check.R

class UseCase3NfcReaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_case3_nfc_reader)
    }

    fun skipClick(view: View) {
        Intent(this, UseCase3HomeActivity::class.java).also {
            startActivity(it)
        }
    }
}
