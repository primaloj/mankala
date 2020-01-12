package com.primaloj.mankala

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.function.DoubleSupplier


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
        findViewById<View>(R.id.p1_name).setBackgroundColor(0x80ffff00.toInt())
    }

    private fun togglePlayer() {
        currentPlayer = otherPlayer()
        if (currentPlayer == 1) {
            findViewById<View>(R.id.p1_name).setBackgroundColor(0x80ffff00.toInt())
            findViewById<View>(R.id.p2_name).setBackgroundColor(Color.TRANSPARENT)
        } else {
            findViewById<View>(R.id.p2_name).setBackgroundColor(0x80ffff00.toInt())
            findViewById<View>(R.id.p1_name).setBackgroundColor(Color.TRANSPARENT)
        }
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
                    if (it.pitPlayer == currentPlayer && it.getValue() > 0) {
                        // if already selected - move
                        if (it == selectedPit) {
                            boop(i - 1)
                            it.deselect()
                        } else { // select
                            selectedPit?.deselect()
                            selectedPit = it
                            it.select()
                        }
                    }
                }
                view.setInitialValues(2)
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
        val count = doublePit.getValueForPlayer(currentPlayer)
        doublePit.reset(currentPlayer)
        var passedThePointPit = false
        val pits = if (currentPlayer == 1) p1Pits else p2Pits

        var doubleTurn = false

        for (i in 1..count) {
            var pitIndex = if (currentPlayer == 1) index + i - 1 else 6 - index + i
            pitIndex %= pits.size
            val view = pits[pitIndex]

            if (passedThePointPit && pitIndex == 0) {
                passedThePointPit = false
            }

            if (view is DoublePit) {
                val doublePitIndex =
                    if (passedThePointPit) (otherPlayer()) else currentPlayer
                view.addOneToPit(doublePitIndex)

                if (count == 1 &&
                    view.getValueForPlayer(currentPlayer) == 1 &&
                    view.getValueForPlayer(otherPlayer()) > 0
                ) {
                    val total = view.getValueForPlayer(otherPlayer()) + 1
                    view.reset(1)
                    view.reset(2)
                    (pits[pits.size / 2] as Pit).add(total)
                }
            } else {
                passedThePointPit = !passedThePointPit
                (view as Pit).increment()

                if (i == count) {
                    doubleTurn = true
                }
            }
        }

        if (!checkGameOver() && !doubleTurn) {
            togglePlayer()
        }
    }

    private fun checkGameOver(): Boolean {
        val pits = if (currentPlayer == 1) p1Pits else p2Pits
        for (i in 0..pits.size / 2 - 1) {
            if ((pits[i] as DoublePit).getValueForPlayer(currentPlayer) > 0) {
                return false
            }
        }

        var total = 0
        for (i in pits.size / 2 + 1..pits.size - 1) {
            total += (pits[i] as DoublePit).getValueForPlayer(otherPlayer())
            (pits[i] as DoublePit).reset(otherPlayer())
        }
        (pits[pits.size / 2] as Pit).add(total)
        checkWinner()
        return true
    }

    private fun checkWinner() {
        if ((p1Pits[p1Pits.size / 2] as Pit).getValue() > ((p2Pits[p2Pits.size / 2] as Pit).getValue())) {
            findViewById<View>(R.id.p1_name).setBackgroundColor(0x80ff00ff.toInt())
        } else {
            findViewById<View>(R.id.p2_name).setBackgroundColor(0x80ff00ff.toInt())
        }
    }

    private fun otherPlayer() = if (currentPlayer == 1) 2 else 1

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