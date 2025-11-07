package com.example.airbridgesampleapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import co.ab180.airbridge.Airbridge
import co.ab180.airbridge.common.AirbridgeCategory
import co.ab180.airbridge.common.AirbridgeAttribute

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the UI design file as the content view for this activity.
        setContentView(R.layout.activity_main)

        // Find the buttons from the XML layout by their IDs.
        val deviceIdTextView: TextView = findViewById(R.id.deviceIdTextView)
        val copyButton: Button = findViewById(R.id.copyButton)
        val sendOrderCompleteButton: Button = findViewById(R.id.sendOrderCompleteButton)
        val sendCustomEventButton: Button = findViewById(R.id.sendCustomEventButton)
        val goDeeplinkButton: Button = findViewById(R.id.goDeeplinkButton)
        val goWebviewButton: Button = findViewById(R.id.goWebviewButton)

        // --- Implement features ---
        // Fetch Airbridge Device ID
        Airbridge.fetchDeviceUUID(
            onSuccess = { uuid ->
                // On success, update the TextView on the UI thread
                runOnUiThread {
                    deviceIdTextView.text = uuid
                }
            },
            onFailure = {
                // On failure, display an error message
                runOnUiThread {
                    deviceIdTextView.text = "Failed to fetch ID"
                }
            }
        )

        // Implement copy button functionality
        copyButton.setOnClickListener {
            // Get the clipboard system service.
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Airbridge Device ID", deviceIdTextView.text)

            // Copy the text to the clipboard.
            clipboard.setPrimaryClip(clip)
            // Show a confirmation message to the user.
            Toast.makeText(this, "Device ID copied!", Toast.LENGTH_SHORT).show()
        }

        // Send Standard Event ('order_complete')
        // The semanticAttributes used in the events below are arbitrary example values for testing.
        sendOrderCompleteButton.setOnClickListener {
            Airbridge.trackEvent(
                AirbridgeCategory.ORDER_COMPLETED,
                mapOf(
                    AirbridgeAttribute.VALUE to 11000,
                    AirbridgeAttribute.TRANSACTION_ID to "8065ef16-162b-4a82-b683-e51aefdda7d5",
                    AirbridgeAttribute.CURRENCY to "KRW",
                    AirbridgeAttribute.IN_APP_PURCHASED to true,
                    AirbridgeAttribute.PRODUCTS to listOf(
                        mapOf(
                            AirbridgeAttribute.PRODUCT_ID to "0117b32a-5a6c-4d4c-b64c-7858e07dba78",
                            AirbridgeAttribute.PRODUCT_NAME to "PlasticHammer",
                            AirbridgeAttribute.PRODUCT_PRICE to 10000,
                            AirbridgeAttribute.PRODUCT_QUANTITY to 1,
                            AirbridgeAttribute.PRODUCT_CURRENCY to "KRW",
                        ),
                        mapOf(
                            AirbridgeAttribute.PRODUCT_ID to "d6ab2fbe-decc-4362-b719-d257a131e91e",
                            AirbridgeAttribute.PRODUCT_NAME to "PlasticFork",
                            AirbridgeAttribute.PRODUCT_PRICE to 1000,
                            AirbridgeAttribute.PRODUCT_QUANTITY to 1,
                            AirbridgeAttribute.PRODUCT_CURRENCY to "KRW",
                        ),
                    ),
                )
            )

            // Show a confirmation message to the user.
            Toast.makeText(this, "'order_complete' event sent!", Toast.LENGTH_SHORT).show()
        }

        // Send Custom Event ('your_custom_event')
        sendCustomEventButton.setOnClickListener {
            Airbridge.trackEvent(
                "your_custom_event",
                mapOf(
                    AirbridgeAttribute.VALUE to 10,
                ),
                mapOf(
                    "string" to "string",
                    "number" to 1000,
                    "boolean" to true,
                    "object" to mapOf("key" to "value"),
                    "array" to listOf("value1", "value2")
                )
            )
            // Show a confirmation message to the user.
            Toast.makeText(this, "'your_custom_event' event sent!", Toast.LENGTH_SHORT).show()
        }

        // Set up the click listener for the 'Go to Deeplink Page' button.
        goDeeplinkButton.setOnClickListener {
            // Intent: An object that holds the "intent" to open a new screen, 'DeeplinkActivity'.
            val intent = Intent(this, DeeplinkActivity::class.java)

            // startActivity: Sends the Intent to the system to start the new activity.
            startActivity(intent)
        }

        // Set up the click listener for the 'Go to WebView Page' button.
        goWebviewButton.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        // Deferred Deep Link Callback Setup
        val isFirstCalled = Airbridge.handleDeferredDeeplink { uri ->
            // when handleDeferredDeeplink is called firstly after install
            Log.d("DeferredDeeplinkTest", "uri: $uri")
            if (uri != null) {
                // show proper content using uri (YOUR_SCHEME://...)
            }
        }
    }
}
