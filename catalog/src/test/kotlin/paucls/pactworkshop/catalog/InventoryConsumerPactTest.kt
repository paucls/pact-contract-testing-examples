package paucls.pactworkshop.catalog

import au.com.dius.pact.consumer.MessagePactBuilder
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.consumer.junit5.ProviderType
import au.com.dius.pact.core.model.annotations.Pact
import au.com.dius.pact.core.model.messaging.Message
import au.com.dius.pact.core.model.messaging.MessagePact
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import paucls.pactworkshop.catalog.app.ProductService
import paucls.pactworkshop.catalog.messaging.ProductInventoryChangedDto
import paucls.pactworkshop.catalog.messaging.ProductStockChangedHandler

@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "inventory", providerType = ProviderType.ASYNCH)
class InventoryConsumerPactTest {

    private val productServiceMock: ProductService = mock()
    private val handler = ProductStockChangedHandler(productServiceMock)

    @Pact(consumer = "catalog")
    fun pact_product_inventory_changed(builder: MessagePactBuilder): MessagePact {
        val body = PactDslJsonBody()
                .integerType("productId", 123)
                .integerType("quantity", 100)

        val metadata = HashMap<String, String>()
        metadata["contentType"] = "application/json"

        return builder.given("a product inventory level has changed")
                .expectsToReceive("product inventory changed message")
                .withMetadata(metadata)
                .withContent(body)
                .toPact()
    }

    @PactTestFor(pactMethod = "pact_product_inventory_changed")
    @Test
    fun `should handle product inventory changed message`(messages: List<Message> ) {
        val productInventoryChangedDto: ProductInventoryChangedDto = jacksonObjectMapper().readValue(messages[0].contentsAsBytes())

        handler.handleMessage(productInventoryChangedDto)

        verify(productServiceMock).syncProductAvailability(
                productId = 123,
                quantity = 100)
    }
}
