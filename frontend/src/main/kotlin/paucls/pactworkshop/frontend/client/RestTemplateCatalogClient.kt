package paucls.pactworkshop.frontend.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RestTemplateCatalogClient : CatalogClient {

    @Value("\${CATALOG_BASE_URL}")
    private lateinit var catalogBaseUrl: String

    private val restTemplate = RestTemplate()

    override fun getAllProducts(): List<Product> {
        return restTemplate.getForEntity("$catalogBaseUrl/products", Array<Product>::class.java).body!!.toList()
    }

    override fun getProduct(id: Int): Product {
        return restTemplate.getForEntity("$catalogBaseUrl/products/$id", Product::class.java).body!!
    }

    override fun favouriteProduct(id: Int) {
        restTemplate.postForLocation("$catalogBaseUrl/products/{id}/favourite", null, id)
    }
}
