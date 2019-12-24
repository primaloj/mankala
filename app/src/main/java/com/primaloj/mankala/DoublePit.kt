package com.primaloj.mankala

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout

class DoublePit(context: Context?) : LinearLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.double_pit, this, true)
        findViewById<Pit>(R.id.p1_pit).setOnClickListener {
            (it as Pit).select()
        }
        findViewById<Pit>(R.id.p2_pit).setOnClickListener {
            (it as Pit).select()
        }
    }

    fun setBorderColors(p1Color: Int, p2Color: Int) {
        findViewById<Pit>(R.id.p1_pit).setBorderColor(p1Color)
        findViewById<Pit>(R.id.p2_pit).setBorderColor(p2Color)
    }
}