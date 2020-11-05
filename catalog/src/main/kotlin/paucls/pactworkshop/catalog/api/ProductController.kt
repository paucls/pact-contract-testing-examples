package paucls.pactworkshop.catalog.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import paucls.pactworkshop.catalog.app.Product
import paucls.pactworkshop.catalog.app.ProductService

@RestController
class ProductController(private val productService: ProductService) {

    @RequestMapping("/")
    fun index(): String = "Catalog service!"

    @RequestMapping(value = ["/products"])
    fun getAllProducts(): List<Product> {
        return productService.getAllProducts()
    }

    @RequestMapping(value = ["/products/{id}"])
    fun getProduct(@PathVariable id: Int): Product {
        return productService.getProduct(id)
    }

    @RequestMapping(value = ["/products/{id}/favourite"], method = [RequestMethod.POST])
    fun favouriteProduct(@PathVariable("id") id: Int): ResponseEntity<Unit> {
        productService.favouriteProduct(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
