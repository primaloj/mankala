package com.primaloj.mankala

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_home)
        findViewById<ImageButton>(R.id.button_settings).setOnTouchListener { v, event ->
            onSettingsButtonTouched(v, event)
        }
        findViewById<ImageButton>(R.id.button_play).setOnTouchListener { v, event ->
            onPlayButtonTouched(v, event)
        }
    }

    private fun openSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun openSelectName() {
        val intent = Intent(this, SelectNameActivity::class.java)
        startActivity(intent)
    }

    private fun onPlayButtonTouched(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                v.animate().scaleX(0.9F).scaleY(0.9F).setDuration(40).start()
                true
            }
            MotionEvent.ACTION_UP -> {
                v.animate().scaleX(1.0F).scaleY(1.0F).setDuration(40).start()
                openSelectName()
                true
            }
        }

        return true
    }

    private fun onSettingsButtonTouched(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                v.animate().scaleX(0.9F).scaleY(0.9F).setDuration(40).start()
                true
            }
            MotionEvent.ACTION_UP -> {
                v.animate().scaleX(1.0F).scaleY(1.0F).setDuration(40).start()
                openSettings()
                true
            }
        }
        return true

    }
}


