package paucls.pactworkshop.frontend.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class CatalogClient(restTemplateBuilder: RestTemplateBuilder,
                    @Value("\${CATALOG_BASE_URL}") catalogBaseUrl: String) {

    private var restTemplate: RestTemplate = restTemplateBuilder.rootUri(catalogBaseUrl).build()

    fun getAllProducts(): List<Product> {
        return restTemplate.getForEntity("/products", Array<Product>::class.java).body!!.toList()
    }

    fun getProduct(id: Int): Product {
        return restTemplate.getForEntity("/products/$id", Product::class.java).body!!
    }

    fun favouriteProduct(id: Int) {
        restTemplate.postForLocation("/products/{id}/favourite", null, id)
    }
}
