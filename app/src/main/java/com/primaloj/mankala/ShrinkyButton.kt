package com.primaloj.mankala

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageButton

class ShrinkyButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageButton(context, attrs, defStyleAttr) {

    lateinit var onClick: () -> Unit

    init {
        setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleX(0.9F).scaleY(0.9F).setDuration(40).start()
                }
                MotionEvent.ACTION_UP -> {
                    v.animate().scaleX(1.0F).scaleY(1.0F).setDuration(40).start()
                    onClick.invoke()
                }
            }

            return@setOnTouchListener true
        }

    }
}