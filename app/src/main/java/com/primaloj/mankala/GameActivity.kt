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
        const val p2_color = 0xffff0000.toInt()
    }

    private var selectedPit: Pit? = null
    private val allPits = mutableListOf<View>()
    private val p1Pits = mutableListOf<View>()
    private val p2Pits = mutableListOf<View>()
    private var currentPlayer: Int = 1

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
                view.setValue(0)
                if (i == 1) {
                    view.setBorderColor(p2_color)
                    view.rotation = 180F
                } else {
                    view.setBorderColor(p1_color)
                }
            } else {
                view = DoublePit(this)
                view.setBorderColors(p1_color, p2_color)
                view.onPitSelected = {
                    // if already selected - move
                    if (it == selectedPit) {
                        boop(i - 1)
                    } else { // select
                        selectedPit?.deselect()
                        selectedPit = it
                        it.select()
                    }
                }
                view.setInitialValues(4)
            }

            val layoutParams = LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.MATCH_PARENT,
                1F
            )
            container.addView(view, layoutParams)
            allPits.add(view)
        }

        assignPitsToPlayers()
    }

    private fun boop(index: Int) {
        val doublePit = p1Pits[index - 1] as DoublePit
        if (currentPlayer == 1) {
            val count = doublePit.getValueForPlayer(currentPlayer)
            doublePit.reset(currentPlayer)
            var passedThePointPit = false
            for (i in 1..count) {
                val view = p1Pits[index + i - 1]
                if (view is DoublePit) {
                    if (passedThePointPit) {
                        view.addOneToPit(2)
                    } else {
                        view.addOneToPit(1)
                    }
                } else {
                    passedThePointPit = true
                    (view as Pit).increment()
                }
            }
        }
    }

    private fun assignPitsToPlayers() {
        // assign pits to players
        // player1:
        p1Pits.add(allPits[1])
        p1Pits.add(allPits[2])
        p1Pits.add(allPits[3])
        p1Pits.add(allPits[4])
        p1Pits.add(allPits[5])
        p1Pits.add(allPits[6])
        p1Pits.add(allPits[7])
        p1Pits.add(allPits[6])
        p1Pits.add(allPits[5])
        p1Pits.add(allPits[4])
        p1Pits.add(allPits[3])
        p1Pits.add(allPits[2])
        p1Pits.add(allPits[1])

        // player2:
        p2Pits.add(allPits[6])
        p2Pits.add(allPits[5])
        p2Pits.add(allPits[4])
        p2Pits.add(allPits[3])
        p2Pits.add(allPits[2])
        p2Pits.add(allPits[1])
        p2Pits.add(allPits[0])
        p2Pits.add(allPits[1])
        p2Pits.add(allPits[2])
        p2Pits.add(allPits[3])
        p2Pits.add(allPits[4])
        p2Pits.add(allPits[5])
        p2Pits.add(allPits[6])
    }

}