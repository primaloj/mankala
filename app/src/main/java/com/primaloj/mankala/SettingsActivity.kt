package com.primaloj.mankala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        findViewById<TextView>(R.id.check_sound_label).setOnClickListener { toggleSound() }
    }

    fun toggleSound() {
        val checkSound = findViewById<CheckBox>(R.id.check_sound)
        checkSound.isChecked = !checkSound.isChecked
    }
}
