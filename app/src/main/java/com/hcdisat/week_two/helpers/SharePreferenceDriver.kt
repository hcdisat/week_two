package com.hcdisat.week_two.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.hcdisat.week_two.models.Event

private const val EVENTS_FILE = "EVENTS_FILE"
private const val EVENTS_KEY = "EVENTS_KEY"

class SharePreferenceDriver(
    context: Context,
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(EVENTS_FILE, Context.MODE_PRIVATE),
    @SuppressLint("CommitPrefEdits")
    private val editor : SharedPreferences.Editor = sharedPreferences.edit()
) {
    private val gson by lazy { Gson() }

    fun read(): String? = sharedPreferences.getString(EVENTS_KEY, null)

    fun write(events: List<Event>) =
        editor.putString(EVENTS_KEY, gson.toJson(events)).apply()
}