package shibin.kmp.product_catalog.data.repository

import shibin.kmp.product_catalog.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProductById(id: Int): Product
}
