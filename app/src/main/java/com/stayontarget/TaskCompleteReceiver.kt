package com.stayontarget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class TaskCompleteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val taskId = intent.getStringExtra("TASK_ID")
        val taskDescription = intent.getStringExtra("TASK_DESCRIPTION")
        
        if (taskId != null && taskDescription != null) {
            val notificationHelper = NotificationHelper(context)
            notificationHelper.dismissNotification(taskId)
            
            Toast.makeText(
                context, 
                "Task completed: $taskDescription", 
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}