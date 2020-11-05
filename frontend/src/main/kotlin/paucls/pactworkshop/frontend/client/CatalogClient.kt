package paucls.pactworkshop.frontend.client

import org.springframework.web.bind.annotation.PathVariable

interface CatalogClient {

    fun getAllProducts(): List<Product>

    fun getProduct(@PathVariable("id") id: Int): Product

    fun favouriteProduct(@PathVariable("id") id: Int)
}
