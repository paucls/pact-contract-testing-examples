package paucls.pactworkshop.frontend

import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslJsonArray
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.annotations.Pact
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import paucls.pactworkshop.frontend.client.CatalogClient
import paucls.pactworkshop.frontend.client.ProductAvailability.InStock
import paucls.pactworkshop.frontend.client.ProductAvailability.OutOfStock


@SpringBootTest
@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "catalog", port = "9081")
class CatalogClientConsumerPactTest {

    private val headers = mapOf("Content-Type" to "application/json")

    @Autowired
    private lateinit var catalogClient: CatalogClient

    @Pact(consumer = "frontend")
    fun pact_get_all_products_when_products_exist(builder: PactDslWithProvider): RequestResponsePact {
        val responseBody = PactDslJsonArray
                .arrayMinLike(1)
                .integerType("id", 123)
                .stringType("type", "STATIONERY")
                .stringType("name", "Permanent Marker")
                .stringValue("availability", "InStock")
                .close()

        return builder
                .given("products exist")
                .uponReceiving("a request to get all products")
                .method("GET")
                .matchPath("/products")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(responseBody)
                .toPact()
    }

    @PactTestFor(pactMethod = "pact_get_all_products_when_products_exist")
    @Test
    fun get_all_products_when_products_exist(mockServer: MockServer) {
        val products = catalogClient.getAllProducts()

        assertThat(products).hasSize(1)
        assertThat(products[0].id).isEqualTo(123)
        assertThat(products[0].name).isEqualTo("Permanent Marker")
        assertThat(products[0].type).isEqualTo("STATIONERY")
        assertThat(products[0].availability).isEqualTo(InStock)
    }

    @Pact(consumer = "frontend")
    fun pact_get_one_product_when_product_exists(builder: PactDslWithProvider): RequestResponsePact {
        val responseBody = PactDslJsonBody()
                .integerType("id", 10)
                .stringType("type", "STATIONERY")
                .stringType("name", "Colored Pencils Set")
                .stringValue("availability", "OutOfStock")
                .booleanType("isFavouriteProduct", true)
                .close()

        return builder
                .given("product with id 10 exists")
                .uponReceiving("a request to get one product")
                .method("GET")
                .matchPath("/products/10")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(responseBody)
                .toPact()
    }

    @PactTestFor(pactMethod = "pact_get_one_product_when_product_exists")
    @Test
    fun get_one_product_when_product_exists(mockServer: MockServer) {
        val product = catalogClient.getProduct(10)

        assertThat(product.id).isEqualTo(10)
        assertThat(product.type).isEqualTo("STATIONERY")
        assertThat(product.name).isEqualTo("Colored Pencils Set")
        assertThat(product.availability).isEqualTo(OutOfStock)
        assertThat(product.isFavouriteProduct).isTrue()
    }

    @Pact(consumer = "frontend")
    fun pact_favourite_a_product(builder: PactDslWithProvider): RequestResponsePact {
        return builder
                .given("product with id 10 exists")
                .uponReceiving("a request to favourite a product")
                .method("POST")
                .matchPath("/products/10/favourite")
                .willRespondWith()
                .status(204)
                .toPact()
    }

    @PactTestFor(pactMethod = "pact_favourite_a_product")
    @Test
    fun favourite_a_product(mockServer: MockServer) {
        catalogClient.favouriteProduct(10)
    }
}
