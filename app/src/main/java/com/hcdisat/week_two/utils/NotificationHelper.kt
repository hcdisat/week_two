package com.hcdisat.week_two.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.hcdisat.week_two.R
import com.hcdisat.week_two.models.Event

private const val CHANNEL_ID = "channel_id"
private const val NOTIFICATION_ID = 1

class NotificationHelper(val context: Context) {
    fun setNotification(event: Event) {
        var notificationChannel: NotificationChannel? = null
        notificationChannel = NotificationChannel(
            CHANNEL_ID,
            context.getString(R.string.event_notification_name),
            NotificationManager.IMPORTANCE_HIGH
        )

        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_calendar)
            .setContentTitle(context.getString(R.string.event_notification_title, event.title))
            .setContentText(context.getString(R.string.event_notification_body, event.title, event.category))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}