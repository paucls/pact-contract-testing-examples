package paucls.pactworkshop.frontend

import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslJsonArray
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


@SpringBootTest
@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "catalog", port = "8081")
class CatalogClientConsumerPactTest {

    @Autowired
    private lateinit var catalogClient: CatalogClient

    @Pact(consumer = "frontend", provider = "catalog")
    fun pact_get_all_products_when_products_exist(builder: PactDslWithProvider): RequestResponsePact {
        val headers = mapOf("Content-Type" to "application/json")

        val responseBody = PactDslJsonArray
                .arrayMinLike(1)
                .integerType("id", 123)
                .stringType("name", "Mastercard")
                .stringType("type", "CREDIT_CARD")
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
        assertThat(products[0].name).isEqualTo("Mastercard")
        assertThat(products[0].type).isEqualTo("CREDIT_CARD")
    }
}
