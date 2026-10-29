package ro.pub.cs.systems.eim.lab05.startedservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class StartedService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(Constants.TAG, "onCreate() method was invoked")
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(Constants.TAG, "onBind() method was invoked")
        return null
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(Constants.TAG, "onUnbind() method was invoked")
        return true
    }

    override fun onRebind(intent: Intent) {
        Log.d(Constants.TAG, "onRebind() method was invoked")
    }

    override fun onDestroy() {
        Log.d(Constants.TAG, "onDestroy() method was invoked")
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(Constants.TAG, "onStartCommand() method was invoked")

        dummyNotification()

        val processingThread = ProcessingThread(this)
        processingThread.start()

        return START_REDELIVER_INTENT
    }

    private val CHANNEL_ID = "11"
    private val CHANNEL_NAME = "ForegroundServiceChannel"

    private lateinit var processingThread: ProcessingThread

    private fun dummyNotification() {
        val manager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

        val notification =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Notification.Builder(applicationContext, CHANNEL_ID)
                    .setContentTitle("Foreground Service")
                    .setContentText("Service is running...")
                    .setSmallIcon(android.R.drawable.ic_media_play)
                    .build()
            } else {
                Notification.Builder(applicationContext)
                    .setContentTitle("Foreground Service")
                    .setContentText("Service is running...")
                    .setSmallIcon(android.R.drawable.ic_media_play)
                    .build()
            }

        startForeground(1, notification)
    }


}
