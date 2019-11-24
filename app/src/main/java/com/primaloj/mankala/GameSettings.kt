package com.primaloj.mankala

import android.content.Context

object GameSettings {
    private val keySoundEnabled = "sound_enabled"

    private fun getSharedPrefs(context: Context) =
        context.getSharedPreferences("mankala", Context.MODE_PRIVATE)

    fun setSoundEnabled(context: Context, enabled: Boolean) {
        val editor = getSharedPrefs(context).edit()
        editor.putBoolean(keySoundEnabled, enabled)
        editor.apply()
    }

    fun isSoundEnabled(context: Context): Boolean {
        return getSharedPrefs(context).getBoolean(keySoundEnabled, true)
    }
}