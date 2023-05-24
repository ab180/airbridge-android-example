package co.ab180.exabr.presentation.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.ab180.airbridge.Airbridge
import co.ab180.airbridge.AirbridgeCallback
import co.ab180.airbridge.event.StandardEventCategory
import co.ab180.airbridge.event.model.Product
import co.ab180.airbridge.event.model.SemanticAttributes
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

            val lipstick = Product()
            lipstick.id = "lipstick_red"
            lipstick.name = "Lipstick - Ruby Red"
            lipstick.quantity = 1
            lipstick.currency = "USD"
            lipstick.price = 12.99
            lipstick.position = 0

            val foundation = Product()
            foundation.id = "foundation_light"
            foundation.name = "Foundation - Light Shade"
            foundation.quantity = 1
            foundation.currency = "USD"
            foundation.price = 24.99
            foundation.position = 1

            val semanticAttributes = SemanticAttributes()
            semanticAttributes.products = listOf(lipstick, foundation)
            semanticAttributes.currency = "USD"
            semanticAttributes.totalValue = 37.98
            semanticAttributes.inAppPurchased = true
            Airbridge.trackEvent(
                category = StandardEventCategory.ORDER_COMPLETED,
                action = null,
                label = null,
                value = null,
                customAttributes = null,
                semanticAttributes = semanticAttributes.toMap()
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
        Airbridge.getDeeplink(intent, object : AirbridgeCallback<Uri> {
            override fun onSuccess(uri: Uri) {
                // Process deeplink data
                MessageDialog.show(
                    supportFragmentManager,
                    titleColor = Color.BLUE,
                    title = "getDeeplink|onSuccess",
                    message = uri.toString()
                )
            }

            override fun onFailure(throwable: Throwable) {
                // Error
                MessageDialog.show(
                    supportFragmentManager,
                    titleColor = Color.RED,
                    title = "getDeeplink|onFailure",
                    message = throwable.message.toString()
                )
            }

            override fun onComplete() {
                // After process deeplink data
            }
        })
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