package co.ab180.abshop.ui.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.ab180.abshop.GlideApp
import co.ab180.abshop.data.Constants
import co.ab180.abshop.databinding.DetailsActivityBinding
import co.ab180.airbridge.Airbridge
import co.ab180.airbridge.event.StandardEventCategory
import co.ab180.airbridge.event.model.Product
import co.ab180.airbridge.event.model.SemanticAttributes
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: DetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!intent.dataString.isNullOrEmpty()) {
            val uri = Uri.parse(intent.dataString)
            val queryId = uri.getQueryParameter(EXTRA_ID)
            if (queryId.isNullOrEmpty()) {
                Toast.makeText(this, "Cannot find id query parameter", Toast.LENGTH_SHORT).show()
                finish()
                return
            }

            initProductUI(Integer.parseInt(queryId))
        } else {
            val id = intent.getIntExtra(EXTRA_ID, 0)
            initProductUI(id)
        }
    }

    fun initProductUI(id: Int) {
        val product = Constants.getProductById(id)
        GlideApp.with(binding.imageView)
            .load(product?.image)
            .fitCenter()
            .into(binding.imageView)
        binding.titleLabel.text = product?.title
        binding.descLabel.text = product?.desc
        binding.priceLabel.text = String.format("$${product?.price}")
        binding.buyButton.setOnClickListener {
            Toast.makeText(this, "Order Completed!", Toast.LENGTH_SHORT).show()
            // Track order completed event
            val semanticAttributes = SemanticAttributes(
                products = listOf(
                    Product(
                        id = product?.id.toString(),
                        name = product?.title,
                        price = product?.price?.toFloat()
                    )
                )
            )
            Airbridge.trackEvent(
                category = StandardEventCategory.ORDER_COMPLETED,
                label = product?.title,
                semanticAttributes = semanticAttributes.toMap()
            )
            onBackPressed()
        }

        // Track product details view event
        val semanticAttributes = SemanticAttributes(
            products = listOf(
                Product(
                    id = product?.id.toString(),
                    name = product?.title,
                    price = product?.price?.toFloat()
                )
            )
        )
        Airbridge.trackEvent(
            category = StandardEventCategory.PRODUCT_DETAILS_VIEW,
            label = product?.title,
            semanticAttributes = semanticAttributes.toMap()
        )
    }

    companion object {
        private const val EXTRA_ID = "id"

        fun newIntent(context: Context, id: Int): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            return intent
        }
    }
}