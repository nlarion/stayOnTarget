package com.stayontarget

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.UUID

class NotificationHelper(private val context: Context) {
    private val channelId = "TASK_TRACKING_CHANNEL"
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                context.getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = context.getString(R.string.notification_channel_description)
                setShowBadge(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showTaskNotification(taskDescription: String) {
        val taskId = UUID.randomUUID().toString()
        
        val completeIntent = Intent(context, TaskCompleteReceiver::class.java).apply {
            putExtra("TASK_ID", taskId)
            putExtra("TASK_DESCRIPTION", taskDescription)
        }
        
        val completePendingIntent = PendingIntent.getBroadcast(
            context,
            taskId.hashCode(),
            completeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_target_notification)
            .setContentTitle(context.getString(R.string.task_notification_title))
            .setContentText(taskDescription)
            .setStyle(NotificationCompat.BigTextStyle().bigText(taskDescription))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOngoing(true)
            .setAutoCancel(false)
            .addAction(
                R.drawable.ic_target_notification,
                context.getString(R.string.task_complete),
                completePendingIntent
            )
            .build()

        try {
            NotificationManagerCompat.from(context).notify(taskId.hashCode(), notification)
        } catch (e: SecurityException) {
            android.util.Log.e("NotificationHelper", "Missing notification permission", e)
        }
    }

    fun dismissNotification(taskId: String) {
        NotificationManagerCompat.from(context).cancel(taskId.hashCode())
    }
}