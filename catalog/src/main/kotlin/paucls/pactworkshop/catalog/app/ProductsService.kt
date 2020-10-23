package paucls.pactworkshop.catalog.app

import org.springframework.stereotype.Service

@Service
class ProductsService {
    private val products = listOf(
            Product(id = 0, name = "Gem Visa", type = "CREDIT_CARD"),
            Product(id = 1, name = "28 Degrees", type = "CREDIT_CARD"),
            Product(id = 2, name = "MyFlexiPay", type = "PERSONAL_LOAN"))

    fun getAllProducts(): List<Product> {
        return products
    }

    fun getProduct(id: Int): Product {
        return products[id]
    }

    fun favouriteProduct(id: Int) {
        TODO("Not yet implemented")
    }
}
