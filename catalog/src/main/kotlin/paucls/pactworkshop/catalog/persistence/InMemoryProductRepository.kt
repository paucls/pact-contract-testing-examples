package paucls.pactworkshop.catalog.persistence

import org.springframework.stereotype.Repository
import paucls.pactworkshop.catalog.app.Product
import paucls.pactworkshop.catalog.app.ProductAvailability
import paucls.pactworkshop.catalog.app.ProductRepository

/**
 * Fake in memory repository implementation for demo purposes
 */
@Repository
class InMemoryProductRepository : ProductRepository {

    private val products = listOf(
            Product(id = 0, name = "Gem Visa", type = "CREDIT_CARD", availability = ProductAvailability.InStock),
            Product(id = 1, name = "28 Degrees", type = "CREDIT_CARD", availability = ProductAvailability.InStock),
            Product(id = 2, name = "MyFlexiPay", type = "PERSONAL_LOAN", availability = ProductAvailability.OutOfStock))

    override fun getAllProducts(): List<Product> {
        return products
    }

    override fun getProduct(id: Int): Product {
        return products[id]
    }

    override fun save(product: Product) {
    }
}
