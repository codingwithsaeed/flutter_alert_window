package ir.codingwithsaeed.flutter_alert_window

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.flutter.plugin.common.EventChannel

class CloseBroadcastReceiver : BroadcastReceiver(), EventChannel.StreamHandler {
    companion object {
        private var instance: CloseBroadcastReceiver? = null
        fun getInstance(): CloseBroadcastReceiver {
            if (instance == null) {
                instance = CloseBroadcastReceiver()
            }
            return instance!!
        }
    }

    private var events: EventChannel.EventSink? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null && intent == null) return
        val handle = intent?.getLongExtra("onCloseHandle", 0L)
        if (handle == 0L) return
        events?.success(handle)
    }

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        this.events = events
    }

    override fun onCancel(arguments: Any?) {
        this.events = null
    }
}