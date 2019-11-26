package com.primaloj.mankala

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class SelectNameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectname)
        findViewById<ImageButton>(R.id.button_close).setOnClickListener { close() }
    }


    fun close() {
        finish()
    }
}



