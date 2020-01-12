package com.primaloj.mankala

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageButton


class ShrinkyButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageButton(context, attrs, defStyleAttr) {

    lateinit var onClick: () -> Unit

    private lateinit var rect: Rect
    private var touchInside: Boolean = false

    init {
        setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleX(0.9F).scaleY(0.9F).setDuration(40).start()
                    rect =
                        Rect(v.left, v.top, v.right, v.bottom)
                    touchInside = true
                }
                MotionEvent.ACTION_UP -> {
                    v.animate().scaleX(1.0F).scaleY(1.0F).setDuration(40).start()
                    if (touchInside) {
                        onClick.invoke()
                        touchInside = false
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    if (!rect.contains(
                            v.left + event.x.toInt(),
                            v.top + event.y.toInt()
                        )
                    ) {
                        // User moved outside bounds
                        touchInside = false
                        v.animate().scaleX(1.0F).scaleY(1.0F).setDuration(40).start()
                    }
                }
            }

            return@setOnTouchListener true
        }

    }
}