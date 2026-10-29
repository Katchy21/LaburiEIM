package ro.pub.cs.systems.eim.lab05.startedserviceactivity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ro.pub.cs.systems.eim.lab05.startedserviceclient.view.StartedServiceBroadcastReceiver

class StartedServiceActivity : AppCompatActivity() {

    private lateinit var messageTextView: TextView
    private lateinit var startedServiceBroadcastReceiver: StartedServiceBroadcastReceiver
    private lateinit var startedServiceIntentFilter: IntentFilter
    private var serviceStarted = false
    private var receiverRegistered = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_started_service)

        messageTextView = findViewById(R.id.message_text_view)

        Log.d(ro.pub.cs.systems.eim.lab05.startedserviceactivity.Constants.TAG, "am intializat servicebroadcastreceiver")
        startedServiceBroadcastReceiver = StartedServiceBroadcastReceiver(messageTextView)
        startedServiceIntentFilter = IntentFilter().apply {
            addAction(Constants.ACTION_STRING)
            addAction(Constants.ACTION_INTEGER)
            addAction(Constants.ACTION_ARRAY_LIST)
        }

    }


    override fun onResume() {
        super.onResume()

        Log.d(Constants.TAG, "onResume() was invoked")

        // Inregistram receptorul
        if (!receiverRegistered) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter, Context.RECEIVER_EXPORTED)
            } else {
                @Suppress("DEPRECATION")
                registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter)
            }
            receiverRegistered = true
        }

        if (!serviceStarted) {
            val serviceIntent = Intent().apply {
                component = ComponentName(
                    "ro.pub.cs.systems.eim.lab05.startedservice",
                    "ro.pub.cs.systems.eim.lab05.startedservice.StartedService"
                )
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }

            serviceStarted = true
        }
    }


    override fun onPause() {
        // TODO: exercise 8c - unregister the broadcast receiver
        if (::startedServiceBroadcastReceiver.isInitialized) {
            unregisterReceiver(startedServiceBroadcastReceiver)
        }

        super.onPause()
    }

    override fun onDestroy() {
        // TODO: exercise 8d - stop the service
        val serviceIntent = Intent().apply {
            component = ComponentName(
                "ro.pub.cs.systems.eim.lab05.startedservice",
                "ro.pub.cs.systems.eim.lab05.startedservice.StartedService"
            )
        }
        stopService(serviceIntent)
        super.onDestroy()
    }

    // TODO: exercise 9 - implement the onNewIntent callback method
    // get the message from the extra field of the intent
    // and display it in the messageTextView

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // Assuming "extraMessage" is the key for the message in the intent extras
        val message = intent.getStringExtra("extraMessage")
        message?.let {
            messageTextView.text = it
        }
    }
}
