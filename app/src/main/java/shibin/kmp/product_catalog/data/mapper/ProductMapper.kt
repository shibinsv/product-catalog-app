package shibin.kmp.product_catalog.data.mapper

import shibin.kmp.product_catalog.data.remote.ProductModel
import shibin.kmp.product_catalog.domain.model.Product

fun ProductModel.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        brand = brand,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        category = category,
        thumbnail = thumbnail,
        description = description,
        images = images
    )
}