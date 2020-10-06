package paucls.pactworkshop.frontend.api

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@Controller
class ProductsController {

    private val products = listOf(
            Product(id = 0, name = "Gem Visa", type = "CREDIT_CARD"),
            Product(id = 1, name = "28 Degrees", type = "CREDIT_CARD"),
            Product(id = 2, name = "MyFlexiPay", type = "PERSONAL_LOAN")
    )

    @GetMapping("/")
    fun greeting(model: Model): String {
        model.addAttribute("products", products)
        return "products"
    }

    @GetMapping("/products/{id}")
    fun productDetails(@PathVariable("id") id: Int, model: Model): String {
        model.addAttribute("product", products[id])
        return "product-details"
    }
}

data class Product(val id: Int, val name: String, val type: String)
