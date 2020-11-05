package paucls.pactworkshop.catalog.app

import org.springframework.stereotype.Service

@Service
class SyncProductStockService(
        private val productRepository: ProductRepository
) {
    fun syncProductStock(productId: Int, isInStock: Boolean) {
        val product = productRepository.getProduct(productId)

        product.availability = if (isInStock) ProductAvailability.InStock else ProductAvailability.OutOfStock

        productRepository.save(product)
    }
}
