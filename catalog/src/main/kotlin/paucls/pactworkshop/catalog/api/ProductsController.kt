package paucls.pactworkshop.catalog.api

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import paucls.pactworkshop.catalog.app.Product
import paucls.pactworkshop.catalog.app.ProductsService

@RestController
class ProductsController(private val productsService: ProductsService) {

    @RequestMapping(value = ["/products"])
    fun getAllProducts(): List<Product> {
        return productsService.getAllProducts()
    }

    @RequestMapping(value = ["/products/{id}"])
    fun getProduct(@PathVariable id: Int): Product {
        return productsService.getProduct(id)
    }
}
