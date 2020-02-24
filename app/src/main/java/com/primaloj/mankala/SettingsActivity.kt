package com.primaloj.mankala

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var checkSound: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        findViewById<TextView>(R.id.check_sound_label).setOnClickListener { toggleSound() }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )



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
                if(s.toString().isEmpty()) return
                GameSettings.setMarbleCount(this@SettingsActivity, s.toString().toInt())
            }

        })
        findViewById<EditText>(R.id.marble_count).text =SpannableStringBuilder( GameSettings.getMarbleCount(this).toString())
    }

    fun close() {
        finish()
    }

    fun toggleSound() {
        checkSound.isChecked = !checkSound.isChecked
    }
}
