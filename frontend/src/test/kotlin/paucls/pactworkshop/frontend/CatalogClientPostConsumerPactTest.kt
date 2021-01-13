package paucls.pactworkshop.frontend

import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.annotations.Pact
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import paucls.pactworkshop.frontend.client.CatalogClient

/**
 * The tests in this class were meant to be in CatalogClientConsumerPactTest. Keeping them separated
 * as a workaround for the issue "I/O error on POST request ... org.apache.http.NoHttpResponseException"
 * https://github.com/pact-foundation/pact-jvm/issues/969
 */
@SpringBootTest
@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "catalog", port = "9081")
class CatalogClientPostConsumerPactTest {

    @Autowired
    private lateinit var catalogClient: CatalogClient

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
