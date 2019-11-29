package com.primaloj.mankala

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class SelectNameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectname)
        findViewById<ImageButton>(R.id.button_close).setOnClickListener { close() }
        findViewById<Button>(R.id.button_start).setOnClickListener { startGame() }
    }


    fun close() {
        finish()
    }
    fun startGame() {
        val p1=findViewById<EditText>(R.id.p1).text
        val p2=findViewById<EditText>(R.id.p2).text
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)



    }
}



