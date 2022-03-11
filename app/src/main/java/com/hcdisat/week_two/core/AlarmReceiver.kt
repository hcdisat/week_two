package com.hcdisat.week_two.core

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hcdisat.week_two.CalendarUtils
import com.hcdisat.week_two.repositories.EventsRepository
import com.hcdisat.week_two.utils.NotificationHelper
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }

        for (event in EventsRepository.all(context)) {
            if (CalendarUtils.isSameYear(calendar, event.eventCalendar) &&
                CalendarUtils.isSameMonth(calendar, event.eventCalendar) &&
                CalendarUtils.isSameDay(calendar, event.eventCalendar)
            ) {
                val notificationHelper = NotificationHelper(context)
                notificationHelper.setNotification(event)
            }
        }
    }

    companion object {

        private fun getIntent(context: Context): Intent {
            return Intent(context, AlarmReceiver::class.java)
        }

        fun getPendingIntent(context: Context): PendingIntent {
            return PendingIntent.getBroadcast(
                context,
                1,
                getIntent(context),
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        }
    }
}