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

    fun syncProductAvailability(productId: Int, quantity: Int) {
        val product = productRepository.getProduct(productId)

        product.availability = if (quantity > 0) ProductAvailability.InStock else ProductAvailability.OutOfStock

        productRepository.save(product)
    }

    fun favouriteProduct(id: Int) {
        TODO("Not yet implemented")
    }
}
