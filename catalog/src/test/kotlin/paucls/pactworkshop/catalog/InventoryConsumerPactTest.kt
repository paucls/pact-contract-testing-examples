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
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import paucls.pactworkshop.catalog.app.SyncProductStockService
import paucls.pactworkshop.catalog.messaging.ProductStockChangedDto
import paucls.pactworkshop.catalog.messaging.ProductStockChangedHandler

@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "inventory", providerType = ProviderType.ASYNCH)
class InventoryConsumerPactTest {

    private val syncProductStockServiceMock: SyncProductStockService = mock()
    private val handler = ProductStockChangedHandler(syncProductStockServiceMock)

    @Pact(consumer = "catalog")
    fun pact_product_stock_changed(builder: MessagePactBuilder): MessagePact {
        val body = PactDslJsonBody()
                .integerType("productId", 123)
                .booleanType("isInStock", true)

        val metadata = HashMap<String, String>()
        metadata["contentType"] = "application/json"

        return builder.given("a product goes out of stock or is back in stock")
                .expectsToReceive("product stock changed message")
                .withMetadata(metadata)
                .withContent(body)
                .toPact()
    }

    @PactTestFor(pactMethod = "pact_product_stock_changed")
    @Test
    fun `should handle product stock changed message`(messages: List<Message> ) {
        val productStockChangedDto: ProductStockChangedDto = jacksonObjectMapper().readValue(messages[0].contentsAsBytes())

        handler.handleMessage(productStockChangedDto)

        verify(syncProductStockServiceMock).syncProductStock(
                productId = 123,
                isInStock = true)
    }
}
