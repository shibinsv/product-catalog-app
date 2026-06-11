package shibin.kmp.product_catalog.data.remote

data class ProductModel(
    val id: String,
    val title: String,
    val brand: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val category: String,
    val thumbnail: String,
    val description: String,
    val images: List<String>
)