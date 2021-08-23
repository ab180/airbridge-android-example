package co.ab180.abshop.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.ab180.abshop.GlideApp
import co.ab180.abshop.data.model.Product
import co.ab180.abshop.databinding.ProductItemBinding
import co.ab180.abshop.ui.details.DetailsActivity
import com.bumptech.glide.Glide

object ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}

class ProductViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) {
        GlideApp.with(itemView)
            .load(product.image)
            .fitCenter()
            .into(binding.imageView)
        binding.titleLabel.text = product.title
        binding.priceLabel.text = String.format("$${product.price}")
        binding.root.setOnClickListener {
            val context = itemView.context
            val intent = DetailsActivity.newIntent(context, product.id)
            context.startActivity(intent)
        }
    }
}

class ProductAdapter : ListAdapter<Product, ProductViewHolder>(ProductDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}