package com.primaloj.mankala

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout

class DoublePit(context: Context?) : LinearLayout(context) {

    lateinit var onPitSelected: (pit: Pit) -> Unit

    init {
        LayoutInflater.from(context).inflate(R.layout.double_pit, this, true)
        findViewById<Pit>(R.id.p1_pit).setOnClickListener {
            onPitSelected.invoke(it as Pit)
        }
        findViewById<Pit>(R.id.p2_pit).setOnClickListener {
            onPitSelected.invoke(it as Pit)
        }
    }

    fun setBorderColors(p1Color: Int, p2Color: Int) {
        findViewById<Pit>(R.id.p1_pit).setBorderColor(p1Color)
        findViewById<Pit>(R.id.p2_pit).setBorderColor(p2Color)
    }

    fun addOneToPit(pitIndex: Int) {
        if (pitIndex == 1) {
            findViewById<Pit>(R.id.p1_pit).increment()
        } else {
            findViewById<Pit>(R.id.p2_pit).increment()
        }
    }

    fun reset(pitIndex: Int){
        if (pitIndex == 1) {
            findViewById<Pit>(R.id.p1_pit).reset()
        } else {
            findViewById<Pit>(R.id.p2_pit).reset()
        }
    }

    fun setInitialValues(value: Int) {
        findViewById<Pit>(R.id.p1_pit).setValue(value)
        findViewById<Pit>(R.id.p2_pit).setValue(value)
    }

    fun getValueForPlayer(player: Int): Int {
        if (player == 1) {
            return findViewById<Pit>(R.id.p1_pit).getValue()
        } else {
            return findViewById<Pit>(R.id.p2_pit).getValue()

        }
    }
}