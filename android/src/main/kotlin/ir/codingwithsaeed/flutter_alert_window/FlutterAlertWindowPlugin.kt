package ir.codingwithsaeed.flutter_alert_window

import android.content.Context
import android.content.IntentFilter

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.JSONMethodCodec
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import ir.codingwithsaeed.alert_activity.CLOSE_ACTION
import org.json.JSONObject

class FlutterAlertWindowPlugin : FlutterPlugin, MethodCallHandler {
    private lateinit var channel: MethodChannel
    private lateinit var eventChannel: EventChannel
    private lateinit var context: Context

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(
            flutterPluginBinding.binaryMessenger, "flutter_alert_window", JSONMethodCodec.INSTANCE
        )
        eventChannel = EventChannel(
            flutterPluginBinding.binaryMessenger,
            "flutter_alert_window_event",
            JSONMethodCodec.INSTANCE
        )
        context = flutterPluginBinding.applicationContext
        val closeBroadcast = CloseBroadcastReceiver.getInstance()
        val intentFilter = IntentFilter(CLOSE_ACTION)
        eventChannel.setStreamHandler(closeBroadcast)
        context.registerReceiver(closeBroadcast, intentFilter)
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "showAlert") {
            val args = call.arguments as JSONObject
            val time = args.getString("time")
            val title = args.getString("title")
            val desc = args.getString("description")
            val type = args.getString("alert_type")
            val onCloseHandle = args.getLong("on_close")
            Utils.openAlertWindow(context, time, title, desc, type, onCloseHandle)
            result.success(true)
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
        eventChannel.setStreamHandler(null)
    }
}
