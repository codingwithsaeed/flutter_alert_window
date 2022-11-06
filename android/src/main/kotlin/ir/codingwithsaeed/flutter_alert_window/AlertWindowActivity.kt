package ir.codingwithsaeed.flutter_alert_window

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import ir.codingwithsaeed.alert_activity.CLOSE_ACTION

class AlertWindowActivity : AppCompatActivity() {
    companion object {
        public lateinit var activity: AlertWindowActivity
    }


    private var callbackHandle: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        activity = this
        if (intent.getBooleanExtra("EXIT", false)) {
            finish();
        }
        window.setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        val win: Window = window
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.primaryColor)
        }
        setTheme(R.style.NormalTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_window)
        initViews()
    }

    private fun initViews() {
        if (intent.hasExtra("type")) {
            val type = intent.getStringExtra("type")
            val layout = findViewById<ConstraintLayout>(R.id.backgroundLayout)
            if (type == "adhan") {
                layout.setBackgroundResource(R.drawable.adhan)
            } else {
                layout.setBackgroundResource(R.drawable.alert)
            }
        }

        if (intent.hasExtra("time")) {
            val time = findViewById<TextView>(R.id.txt_time)
            time.text = intent.getStringExtra("time")
        }
        if (intent.hasExtra("title")) {
            val title = findViewById<TextView>(R.id.txt_title)
            title.text = intent.getStringExtra("title")
        }
        if (intent.hasExtra("description")) {
            val desc = findViewById<TextView>(R.id.txt_desc)
            desc.text = intent.getStringExtra("description")
        }

        callbackHandle = intent.getLongExtra("onCloseHandle", 0L)

        findViewById<MaterialButton>(R.id.btn_close).setOnClickListener {
            sendNewBroadcast()
            finishAffinity()
        }
    }

    override fun onBackPressed() {
        sendNewBroadcast()
        finishAffinity()
        super.onBackPressed()
    }

    private fun sendNewBroadcast() {
        val intent = Intent(CLOSE_ACTION).also {
            it.putExtra("onCloseHandle", callbackHandle)
        }
        sendBroadcast(intent)
    }

}