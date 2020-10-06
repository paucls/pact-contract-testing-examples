package paucls.pactworkshop.catalog.api

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductsController {

    private val products = listOf(
            Product(id = 0, name = "Gem Visa", type = "CREDIT_CARD"),
            Product(id = 1, name = "28 Degrees", type = "CREDIT_CARD"),
            Product(id = 2, name = "MyFlexiPay", type = "PERSONAL_LOAN"))

    @RequestMapping(value = ["/products"])
    fun getAllProducts(): List<Product> {
        return products
    }

    @RequestMapping(value = ["/products/{id}"])
    fun getProduct(@PathVariable id: Int): Product {
        return products[id]
    }
}
