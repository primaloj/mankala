package com.primaloj.mankala

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
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
                    scaleButton(v, 0.9F)
                    rect =
                        Rect(v.left, v.top, v.right, v.bottom)
                    touchInside = true
                }
                MotionEvent.ACTION_UP -> {
                    scaleButton(v, 1.0F)
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
                        scaleButton(v, 1.0F)
                    }
                }
            }

            return@setOnTouchListener true
        }
    }

    private fun scaleButton(v: View, value: Float) {
        v.animate().scaleX(value).scaleY(value).setDuration(40).start()
    }
}