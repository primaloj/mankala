package com.primaloj.mankala

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class GameActivity : AppCompatActivity() {
    companion object {
        val p1_name = "p1_name"
        val p2_name = "p2_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        findViewById<TextView>(R.id.p1_name).text = intent.getStringExtra(p1_name)
        findViewById<TextView>(R.id.p2_name).text = intent.getStringExtra(p2_name)
        createPits()
    }

    fun createPits() {
        val container = findViewById<LinearLayout>(R.id.pits_container)
        for (i in 1..8) {
            var view: View
            if (i == 1 || i == 8) {
                view = View(this)
            } else {
                view = DoublePit(this)
            }
            val layoutParams = LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT,
                1F
            )
            container.addView(view, layoutParams)

        }
    }

}