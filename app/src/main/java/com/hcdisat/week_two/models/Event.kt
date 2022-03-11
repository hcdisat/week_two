package com.hcdisat.week_two.models

import android.os.Parcelable
import com.hcdisat.week_two.calculateDaysToEvent
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

private const val DATE_FORMAT = "MM/dd/yyyy"

@Parcelize
data class Event(
    var title: String = "",
    var category: String = "",
    var year: Int = 1985,
    var month: Int = 9,
    var day: Int = 21
) : Parcelable {

    @IgnoredOnParcel
    val dateString get()  = formatDate()

    @IgnoredOnParcel
    val daysLeft get() = calculateDaysToEvent(this)

    @IgnoredOnParcel
    val eventCalendar: Calendar
        get() = Calendar.getInstance().apply {
        set(Calendar.MONTH, month - 1)
        set(Calendar.YEAR, year)
        set(Calendar.DAY_OF_MONTH, day)
    }

    private fun formatDate(): String {
        val date = LocalDateTime.of(year, month, day, 0, 0)
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
    }
}