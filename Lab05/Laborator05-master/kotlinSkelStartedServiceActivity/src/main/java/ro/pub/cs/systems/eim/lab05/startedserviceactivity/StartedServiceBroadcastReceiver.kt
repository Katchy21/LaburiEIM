
package ro.pub.cs.systems.eim.lab05.startedserviceclient.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView
import ro.pub.cs.systems.eim.lab05.startedserviceactivity.Constants

class StartedServiceBroadcastReceiver(private val messageTextView: TextView?) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        var data: String? = null

        if (Constants.ACTION_STRING == action) {
            data = intent.getStringExtra(Constants.DATA)
        } else if (Constants.ACTION_INTEGER == action) {
            data = intent.getIntExtra(Constants.DATA,1).toString()
        } else if (Constants.ACTION_ARRAY_LIST == action) {
            data = intent.getStringArrayListExtra(Constants.DATA).toString()
        } else {
            data = null;
        }

        Log.d(ro.pub.cs.systems.eim.lab05.startedserviceactivity.Constants.TAG, "am ajuns aici")

        messageTextView?.let {
            val currentText = it.text.toString()
            Log.d(ro.pub.cs.systems.eim.lab05.startedserviceactivity.Constants.TAG, "am ajuns aici si current text + $currentText")

            if (data!=null) {
                it.text = "$currentText\n$data"
                Log.d(ro.pub.cs.systems.eim.lab05.startedserviceactivity.Constants.TAG, "am ajuns aici si current text + $currentText\n$data")

            }
        }
    }
}
