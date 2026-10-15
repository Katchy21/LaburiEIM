package ro.pub.cs.systems.eim.lab03.phonedialer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PhoneDialerActivity : AppCompatActivity() {
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_phone_dialer)
        editText = findViewById(R.id.PhoneEditText)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.Button0).setOnClickListener(Listener())
        findViewById<Button>(R.id.Button1).setOnClickListener(Listener())
        findViewById<Button>(R.id.Button2).setOnClickListener(Listener())
        findViewById<Button>(R.id.Button3).setOnClickListener(Listener())
        findViewById<Button>(R.id.Button4).setOnClickListener(Listener())
        findViewById<Button>(R.id.Button5).setOnClickListener(Listener())
        findViewById<Button>(R.id.Button6).setOnClickListener(Listener())
        findViewById<Button>(R.id.Button7).setOnClickListener(Listener())
        findViewById<Button>(R.id.Button8).setOnClickListener(Listener())
        findViewById<Button>(R.id.Button9).setOnClickListener(Listener())
        findViewById<Button>(R.id.Button10).setOnClickListener(Listener())
        findViewById<Button>(R.id.Button11).setOnClickListener(Listener())
        findViewById<ImageButton>(R.id.ImageButtonBack).setOnClickListener(Listener())
        findViewById<ImageButton>(R.id.ImageButtonCall).setOnClickListener(Listener())
        findViewById<ImageButton>(R.id.ImageButtonClose).setOnClickListener(Listener())


    }
    inner class Listener : View.OnClickListener {

        override fun onClick(view: View) {

            when (view.id) {
                R.id.Button0 -> editText.append("0")
                R.id.Button1 -> editText.append("1")
                R.id.Button2 -> editText.append("2")
                R.id.Button3 -> editText.append("3")
                R.id.Button4 -> editText.append("4")
                R.id.Button5 -> editText.append("5")
                R.id.Button6 -> editText.append("6")
                R.id.Button7 -> editText.append("7")
                R.id.Button8 -> editText.append("8")
                R.id.Button9 -> editText.append("9")
                R.id.Button10 -> editText.append("#")
                R.id.Button11 -> editText.append("*")
                R.id.ImageButtonBack -> {
                    val text = editText.text
                    if (text.isNotEmpty()) {
                        text.delete(text.length - 1, text.length)
                    }
                }
                R.id.ImageButtonClose -> {
                    finish()
                }
                R.id.ImageButtonCall -> {
                if (ContextCompat.checkSelfPermission(
                        this@PhoneDialerActivity,
                        Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this@PhoneDialerActivity,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        1
                    )
                } else {
                    val intent = Intent(Intent.ACTION_CALL)
                    intent.setData(Uri.parse("tel:" + editText!!.text.toString()))
                    startActivity(intent)
                }
            }
            }

        }
    }
}