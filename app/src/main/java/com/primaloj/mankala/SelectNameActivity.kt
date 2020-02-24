package com.primaloj.mankala

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class SelectNameActivity : TextWatcher, AppCompatActivity() {
    lateinit var p1: EditText
    lateinit var p2: EditText
    lateinit var button_start: ShrinkyButton

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        checkNames()
    }

    private fun checkNames() {
        button_start.isEnabled = p1.text.isNotBlank() && p2.text.isNotBlank()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectname)
        findViewById<ShrinkyButton>(R.id.button_close).onClick= { close() }
        findViewById<ShrinkyButton>(R.id.button_start).onClick= { startGame() }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        findViewById<ImageButton>(R.id.button_close).setOnClickListener { close() }
        p1 = findViewById(R.id.p1)
        p2 = findViewById(R.id.p2)
        button_start = findViewById(R.id.button_start)
        button_start.setOnClickListener { startGame() }
        p1.addTextChangedListener(this)
        p2.addTextChangedListener(this)

        checkNames()
    }


    fun close() {
        finish()
    }

    fun startGame() {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(GameActivity.p1_name, p1.text.toString())
        intent.putExtra(GameActivity.p2_name, p2.text.toString())
        startActivity(intent)


    }
}



