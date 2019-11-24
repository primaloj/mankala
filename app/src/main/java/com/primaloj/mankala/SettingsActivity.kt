package com.primaloj.mankala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView

class SettingsActivity : AppCompatActivity() {

    private lateinit var checkSound: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        findViewById<TextView>(R.id.check_sound_label).setOnClickListener { toggleSound() }

        checkSound = findViewById<CheckBox>(R.id.check_sound)
        checkSound.isChecked = GameSettings.isSoundEnabled(this)
        checkSound.setOnCheckedChangeListener { buttonView, isChecked ->
            GameSettings.setSoundEnabled(
                this,
                isChecked
            )
        }
    }

    fun toggleSound() {
        checkSound.isChecked = !checkSound.isChecked
    }
}
