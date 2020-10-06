package paucls.pactworkshop.frontend.api

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.util.*


@Controller
class ProductsController {

    @GetMapping("/")
    fun greeting(model: Model): String {
        val products = listOf(
                Product(name = "Gem Visa", type = "CREDIT_CARD"),
                Product(name = "28 Degrees", type = "CREDIT_CARD"),
                Product(name = "MyFlexiPay", type = "PERSONAL_LOAN")
        )
        model.addAttribute("products", products)

        return "products"
    }

}

data class Product(val id: UUID = UUID.randomUUID(), val name: String, val type: String)
