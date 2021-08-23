package co.ab180.abshop.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.ab180.abshop.GlideApp
import co.ab180.abshop.data.Constants
import co.ab180.abshop.databinding.DetailsActivityBinding
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: DetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_ID, 0)
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
            onBackPressed()
        }
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