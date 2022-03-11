package com.hcdisat.week_two.repositories

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hcdisat.week_two.helpers.SharePreferenceDriver
import com.hcdisat.week_two.models.Event

object EventsRepository {

    private val gson by lazy { Gson() }

    private var events: MutableList<Event> = arrayListOf()

    fun all(context: Context): List<Event> {
        val sharePreferenceDriver = SharePreferenceDriver(context)
        val eventsString : String? = sharePreferenceDriver.read()

        eventsString?.let {
            events = gson.fromJson(it, object : TypeToken<List<Event?>?>() {}.type)
        }

        return events
    }

    fun add(context: Context, event: Event, index: Int = -1) {
        events.add(event)
        write(context, event)
    }

    fun remove(context: Context, event: Event) {
        events.remove(event)
        write(context, event)
    }

    private fun write(context: Context, event: Event) {
        val sharePreferenceDriver = SharePreferenceDriver(context)
        sharePreferenceDriver.write(events)
    }
}