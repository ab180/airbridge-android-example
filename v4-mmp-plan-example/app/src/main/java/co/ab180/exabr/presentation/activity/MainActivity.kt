package co.ab180.exabr.presentation.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.ab180.airbridge.Airbridge
import co.ab180.airbridge.common.AirbridgeAttribute
import co.ab180.airbridge.common.AirbridgeCategory
import co.ab180.exabr.R
import co.ab180.exabr.databinding.ActivityMainBinding
import co.ab180.exabr.presentation.dialog.MessageDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButton()
    }

    private fun initButton() {

        // Track Event
        binding.btnTrackEvent.setOnClickListener {

            Airbridge.trackEvent(
                category = AirbridgeCategory.ORDER_COMPLETED,
                semanticAttributes = mapOf(
                    AirbridgeAttribute.VALUE to 37.98f,
                    AirbridgeAttribute.CURRENCY to "USD",
                    AirbridgeAttribute.IN_APP_PURCHASED to true,
                    AirbridgeAttribute.PRODUCTS to listOf(
                        mapOf(
                            AirbridgeAttribute.PRODUCT_ID to "lipstick_red",
                            AirbridgeAttribute.PRODUCT_NAME to "Lipstick - Ruby Red",
                            AirbridgeAttribute.PRODUCT_QUANTITY to 1,
                            AirbridgeAttribute.PRODUCT_CURRENCY to "USD",
                            AirbridgeAttribute.PRODUCT_PRICE to 12.99,
                            AirbridgeAttribute.PRODUCT_POSITION to 0
                        ),
                        mapOf(
                            AirbridgeAttribute.PRODUCT_ID to "foundation_light",
                            AirbridgeAttribute.PRODUCT_NAME to "Foundation - Light Shade",
                            AirbridgeAttribute.PRODUCT_QUANTITY to 1,
                            AirbridgeAttribute.PRODUCT_CURRENCY to "USD",
                            AirbridgeAttribute.PRODUCT_PRICE to 24.99,
                            AirbridgeAttribute.PRODUCT_POSITION to 1
                        ),
                    ),
                )
            )

            showToast(getString(R.string.button_track_event))
        }

        // Track Event From Web
        binding.btnHybridTrackEvent.setOnClickListener {
            val intent = WebviewActivity.newIntent(this)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        // Deferred Deep Link Callback Setup
        val isFirstCalled = Airbridge.handleDeferredDeeplink { uri ->
            // when handleDeferredDeeplink is called firstly after install
            if (uri != null) {
                MessageDialog.show(
                    supportFragmentManager,
                    titleColor = Color.BLUE,
                    title = "getDeferredDeeplink|onSuccess",
                    message = uri.toString()
                )
            }
        }

        // handle airbridge deeplink
        val isAirbridgeDeeplink = Airbridge.handleDeeplink(
            intent = intent,
            // Process deeplink data
            onSuccess = { uri ->
                MessageDialog.show(
                    supportFragmentManager,
                    titleColor = Color.BLUE,
                    title = "getDeeplink|onSuccess",
                    message = uri.toString()
                )
            },
            // Error
            onFailure = { throwable ->
                MessageDialog.show(
                    supportFragmentManager,
                    titleColor = Color.RED,
                    title = "getDeeplink|onFailure",
                    message = throwable.message.toString()
                )
            }
        )
        if (isAirbridgeDeeplink) return

        // when app is opened with other deeplink
        // use existing logic as it is
    }

    // The code below is required for proper deeplink processing
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}