package co.ab180.abshop.data

import android.content.Context
import co.ab180.abshop.data.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Constants {

    lateinit var products: List<Product>

    fun init(context: Context) {
        val productsJsonString = context.assets
            .open("products.json")
            .bufferedReader()
            .use { it.readText() }
        products = Gson().fromJson(productsJsonString, object : TypeToken<List<Product>>(){}.type)
    }

    fun getProductById(id: Int): Product? {
        for (product in products) {
            if (product.id == id) {
                return product
            }
        }
        return null
    }
}