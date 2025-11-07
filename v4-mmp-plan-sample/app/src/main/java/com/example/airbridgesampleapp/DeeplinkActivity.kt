package com.example.airbridgesampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import co.ab180.airbridge.Airbridge

class DeeplinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deeplink)

        val backButton: Button = findViewById(R.id.backButton)
        val deviceIdTextView: TextView = findViewById(R.id.deviceIdTextView)

        // Fetch Airbridge Device ID
        Airbridge.fetchDeviceUUID(
            onSuccess = { uuid ->
                // On success, update the TextView on the UI thread
                runOnUiThread {
                    deviceIdTextView.text = uuid
                }
            },
            onFailure = {
                // 실패 시 에러 메시지 표시
                // On failure, display an error message
                runOnUiThread {
                    deviceIdTextView.text = "Failed to fetch ID"
                }
            }
        )
        // Set up the click listener for the button.
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        val originalUriTextView: TextView = findViewById(R.id.originalUriTextView)
        val handledUriTextView: TextView = findViewById(R.id.handledUriTextView)


        val rawUri = intent?.data
        originalUriTextView.text = rawUri.toString()
        //Log.d("DeeplinkTest", "intent.data: $rawUri")

        // handle airbridge deeplink
        val isAirbridgeDeeplink = Airbridge.handleDeeplink(intent){uri ->
            handledUriTextView.text = uri.toString()
            //Log.d("DeeplinkTest", "onSuccess uri: $uri")
        }
        if (isAirbridgeDeeplink) return
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

}
