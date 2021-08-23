package co.ab180.abshop.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import co.ab180.abshop.R
import co.ab180.abshop.data.Constants
import co.ab180.abshop.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private val adapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.submitList(Constants.products)
        binding.listView.layoutManager = GridLayoutManager(this, 2)
        binding.listView.adapter = adapter
    }
}