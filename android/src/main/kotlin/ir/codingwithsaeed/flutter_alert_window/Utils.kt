package ir.codingwithsaeed.flutter_alert_window

import android.content.Context
import android.content.Intent
import android.os.PowerManager

class Utils {
    companion object {
        private fun wakeUpScreen(context: Context) {
            val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            val serviceFlag =
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK.or(PowerManager.ACQUIRE_CAUSES_WAKEUP)
                    .or(PowerManager.ON_AFTER_RELEASE)

            val newWakeLock =
                powerManager.newWakeLock(serviceFlag, "ForegroundServiceUtils:WakeLock")
            newWakeLock.acquire(1000)
            newWakeLock.release()
        }

        fun openAlertWindow(
            context: Context, time: String, title: String, desc: String, onCloseHandle: Long,
        ) {
            wakeUpScreen(context)
            val intent = Intent(context, AlertWindowActivity::class.java)
            intent.putExtra("time", time)
            intent.putExtra("title", title)
            intent.putExtra("description", desc)
            intent.putExtra("onCloseHandle", onCloseHandle)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }
}
