package shibin.kmp.product_catalog.data.repository

import shibin.kmp.product_catalog.data.mapper.toDomain
import shibin.kmp.product_catalog.data.remote.ProductApi
import shibin.kmp.product_catalog.domain.model.Product

class ProductRepositoryImpl(
private val api: ProductApi
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {

        return api.getProducts()
            .data
            .products
            .map { it.toDomain() }
    }

    override suspend fun getProductById(
        id: Int
    ): Product {

        return api
            .getProductDetail(id)
            .data
            .toDomain()
    }
}