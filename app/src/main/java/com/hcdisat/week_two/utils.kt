package com.hcdisat.week_two

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.hcdisat.week_two.models.Event
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.temporal.Temporal
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Constants
 */
const val ARG_EVENT = "ARG_EVENT"

fun snackBar(view: View, message: String) =
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()

fun calculateDaysToEvent(event: Event): Long {
    val c = Calendar.getInstance()
    c.set(event.year, event.month - 1, event.day)

    val eventDate = c.timeInMillis
    val eInstant = Instant.ofEpochMilli(eventDate)
    val nowInstant = Instant.ofEpochMilli(Date.from(Instant.now()).time)
    val diff = Duration.between(nowInstant, eInstant).toDays()

    return if (diff < 0) 0 else diff
}
