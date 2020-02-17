package com.primaloj.mankala

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_home)
        findViewById<ShrinkyButton>(R.id.button_settings).onClick = { openSettings() }
        findViewById<ShrinkyButton>(R.id.button_play).onClick = { openSelectName() }
    }

    private fun openSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun openSelectName() {
        val intent = Intent(this, SelectNameActivity::class.java)
        startActivity(intent)
    }

}


