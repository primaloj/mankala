package com.primaloj.mankala

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class GameActivity : AppCompatActivity() {
    companion object {
        const val p1_name = "p1_name"
        const val p2_name = "p2_name"

        const val p1_color = 0xff0000ff.toInt()
        const val p2_color = 0xff9900cc.toInt()
    }

    private var selectedPit: Pit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        findViewById<TextView>(R.id.p1_name).text = intent.getStringExtra(p1_name)
        findViewById<TextView>(R.id.p2_name).text = intent.getStringExtra(p2_name)
        createPits()
    }

    private fun createPits() {
        val container = findViewById<LinearLayout>(R.id.pits_container)
        for (i in 1..8) {
            var view: View
            if (i == 1 || i == 8) {
                view = Pit(this)
                if (i == 1) {
                    view.setBorderColor(p1_color)
                    view.rotation = 180F
                } else {
                    view.setBorderColor(p2_color)
                }
            } else {
                view = DoublePit(this)
                view.setBorderColors(p1_color, p2_color)
                view.onPitSelected = {
                    selectedPit?.deselect()
                    selectedPit = it
                    it.select()
                }
            }

            val layoutParams = LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT,
                1F
            )
            container.addView(view, layoutParams)

        }
    }

}