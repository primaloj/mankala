package com.primaloj.mankala

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout

class DoublePit(context: Context?) : LinearLayout(context) {
    init {
        LayoutInflater.from(context).inflate(R.layout.double_pit, this, true)
    }
}