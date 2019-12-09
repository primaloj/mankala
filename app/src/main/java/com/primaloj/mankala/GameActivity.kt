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

        val p1_color = 0x00ff00
        val p2_color = 0xff0000
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
                view = Pit(this)
                if (i == 1) {
                    (view as Pit).setBorderColor(p1_color)
                } else {
                    (view as Pit).setBorderColor(p2_color)
                }
            } else {
                view = DoublePit(this)
                (view as DoublePit).setBorderColors(p1_color, p2_color)
            }
            val layoutParams = LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT,
                1F
            )
            container.addView(view, layoutParams)

        }
    }

}