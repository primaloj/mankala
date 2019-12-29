package com.primaloj.mankala

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class Pit(context: Context?, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs, 0) {

    private var playerColor: Int = 0
    private var pitValue: Int = 0
    var pitPlayer: Int = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.pit, this, true)
    }


    fun setBorderColor(color: Int) {
        playerColor = color
        findViewById<View>(R.id.pit_container).setBackgroundColor(color)
    }

    fun select() {
        findViewById<View>(R.id.pit_container).setBackgroundColor(0xff00ff00.toInt())
    }

    fun deselect() {
        findViewById<View>(R.id.pit_container).setBackgroundColor(playerColor)
    }

    fun reset() {
        setValue(0)
    }

    fun increment() {
        setValue(pitValue + 1)
    }

    fun setValue(value: Int) {
        pitValue = value
        findViewById<TextView>(R.id.number).text = pitValue.toString()
    }

    fun getValue() = pitValue
    fun setPlayer(playerIndex: Int) {
        pitPlayer = playerIndex
    }

    fun add(addValue: Int) {
        setValue(pitValue + addValue)
    }
}

