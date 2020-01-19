package com.primaloj.mankala

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout

class DoublePit(context: Context?) : LinearLayout(context) {

    lateinit var onPitSelected: (pit: Pit) -> Unit
    lateinit var onLongClick: (pit: Pit) -> Unit
    private var p1pit: Pit
    private var p2pit: Pit


    init {
        LayoutInflater.from(context).inflate(R.layout.double_pit, this, true)
        p1pit = findViewById(R.id.p1_pit)
        p2pit = findViewById(R.id.p2_pit)

        p1pit.setOnClickListener {
            onPitSelected.invoke(it as Pit)
        }

        p2pit.setOnClickListener {
            onPitSelected.invoke(it as Pit)
        }
        p1pit.setOnLongClickListener {
            onLongClick.invoke(it as Pit)
            true
        }
        p2pit.setOnLongClickListener {
            onLongClick.invoke(it as Pit)
            true
        }
    }

    @Suppress("unused")
    fun setBorderColors(p1Color: Int, p2Color: Int) {
        p2pit.setBorderColor(p2Color)
        p1pit.setBorderColor(p1Color)
        p1pit.setPlayer(1)
        p2pit.setPlayer(2)
    }

    fun addOneToPit(index: Int) {
        getPitForPlayer(index).increment()
    }

    fun reset(index: Int) {
        getPitForPlayer(index).reset()
    }
    fun setValue(index: Int,value: Int) {
        getPitForPlayer(index).setValue(value)
    }

    @Suppress("unused")
    fun setInitialValues(value: Int) {
        p1pit.setValue(value)
        p2pit.setValue(value)
    }

    fun getValueForPlayer(index: Int): Int {
        return getPitForPlayer(index).getValue()
    }

    private fun getPitForPlayer(index: Int): Pit {
        return if (index == 1) {
            p1pit
        } else {
            p2pit
        }
    }

    override fun toString(): String {
        return "p1: ${p1pit} | p2: ${p2pit}"
    }
}