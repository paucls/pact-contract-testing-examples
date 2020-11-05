package paucls.pactworkshop.catalog.app

import org.springframework.stereotype.Service

@Service
class ProductService(
        private val productRepository: ProductRepository
) {
    fun getAllProducts(): List<Product> {
        return productRepository.getAllProducts()
    }

    fun getProduct(id: Int): Product {
        return productRepository.getProduct(id)
    }

    fun syncProductStock(productId: Int, isInStock: Boolean) {
        val product = productRepository.getProduct(productId)

        product.availability = if (isInStock) ProductAvailability.InStock else ProductAvailability.OutOfStock

        productRepository.save(product)
    }

    fun favouriteProduct(id: Int) {
        TODO("Not yet implemented")
    }
}
