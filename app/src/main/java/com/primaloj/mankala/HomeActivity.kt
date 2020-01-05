package com.primaloj.mankala

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        findViewById<ImageButton>(R.id.button_settings).setOnClickListener { openSettings() }
        findViewById<ImageButton>(R.id.button_play).setOnClickListener { openSelectName() }
    }

    fun openSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun openSelectName() {
        val intent = Intent(this, SelectNameActivity::class.java)
        startActivity(intent)
    }
}



