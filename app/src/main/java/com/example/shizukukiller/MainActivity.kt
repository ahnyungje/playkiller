package com.example.shizukukiller

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import rikka.shizuku.Shizuku
import java.io.DataOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val button = Button(this).apply {
            text = "구글 플레이 서비스 종료"
            setOnClickListener {
                if (Shizuku.isApiSupported() && Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
                    try {
                        val process = Shizuku.newProcess(arrayOf("sh"), null, null)
                        val output = DataOutputStream(process.outputStream)
                        output.writeBytes("am force-stop com.google.android.gms\n")
                        output.flush()
                        output.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        setContentView(button)
    }
}
