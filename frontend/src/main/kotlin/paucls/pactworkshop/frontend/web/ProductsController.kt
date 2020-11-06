package paucls.pactworkshop.frontend.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import paucls.pactworkshop.frontend.client.CatalogClient


@Controller
class ProductsController(private val catalogClient: CatalogClient) {

    @GetMapping("/")
    fun products(model: Model): String {
        val products = catalogClient.getAllProducts()
        model.addAttribute("products", products)
        return "products"
    }

    @GetMapping("/products/{id}")
    fun productDetails(@PathVariable("id") id: Int, model: Model): String {
        val product = catalogClient.getProduct(id)
        model.addAttribute("product", product)
        return "product-details"
    }
}
