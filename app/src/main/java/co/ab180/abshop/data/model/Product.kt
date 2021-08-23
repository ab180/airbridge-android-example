package co.ab180.abshop.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("description")
    val desc: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("image")
    val image: String
)
