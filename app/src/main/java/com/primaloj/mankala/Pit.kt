package com.primaloj.mankala

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout

class Pit(context: Context?, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs, 0) {
    init {
        LayoutInflater.from(context).inflate(R.layout.pit, this, true)
    }

    fun setBorderColor(color: Int) {
        findViewById<View>(R.id.pit_container).setBackgroundColor(color)
    }
}