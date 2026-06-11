package shibin.kmp.product_catalog.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shibin.kmp.product_catalog.data.remote.ProductApi
import shibin.kmp.product_catalog.data.repository.ProductRepository
import shibin.kmp.product_catalog.data.repository.ProductRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }
}
