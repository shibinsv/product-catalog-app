package shibin.kmp.product_catalog.data.remote

import com.google.gson.annotations.SerializedName

data class ProductResponse(val data: ProductData)

data class ProductData(
    @SerializedName("data") val products: List<ProductModel>
)

data class ProductDetailResponse(
    val statusCode: Int, val data: ProductModel, val message: String, val success: Boolean
)