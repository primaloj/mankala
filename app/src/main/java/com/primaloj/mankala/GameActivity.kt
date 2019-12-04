package com.primaloj.mankala

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    companion object{
        val p1_name = "p1_name"
        val p2_name = "p2_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        findViewById<TextView>(R.id.p1_name).text = intent.getStringExtra(p1_name)
        findViewById<TextView>(R.id.p2_name).text = intent.getStringExtra(p2_name)
    }

}