package com.primaloj.mankala

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class SettingsActivity : AppCompatActivity() {

    companion object {
        var marblesCount: Int = 4
    }

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
        findViewById<ImageButton>(R.id.button_close).setOnClickListener { close() }
        findViewById<EditText>(R.id.marble_count).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                marblesCount = s.toString().toInt()
            }

        })
    }

    fun close() {
        finish()
    }

    fun toggleSound() {
        checkSound.isChecked = !checkSound.isChecked
    }
}
