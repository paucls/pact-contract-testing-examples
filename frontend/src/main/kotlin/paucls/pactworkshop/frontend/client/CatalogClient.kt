package paucls.pactworkshop.frontend.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class CatalogClient(@Value("\${CATALOG_BASE_URL}") private val catalogBaseUrl: String) {

    private val restTemplate = RestTemplate()

    fun getAllProducts(): List<Product> {
        return restTemplate.getForEntity("$catalogBaseUrl/products", Array<Product>::class.java).body!!.toList()
    }

    fun getProduct(id: Int): Product {
        return restTemplate.getForEntity("$catalogBaseUrl/products/$id", Product::class.java).body!!
    }

    fun favouriteProduct(id: Int) {
        restTemplate.postForLocation("$catalogBaseUrl/products/{id}/favourite", null, id)
    }
}