package paucls.pactworkshop.inventory

import au.com.dius.pact.provider.PactVerifyProvider
import au.com.dius.pact.provider.junit5.AmpqTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactFolder
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import paucls.pactworkshop.inventory.messaging.ProductStockChangedDto


@Provider("inventory")
@PactFolder("pacts")
class CatalogProviderPactTest {

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }

    @BeforeEach
    fun before(context: PactVerificationContext) {
        val testTarget = AmpqTestTarget()
        context.target = testTarget
    }

    @State("a product goes out of stock or is back in stock")
    fun `a product goes out of stock or is back in stock`() {
    }

    @PactVerifyProvider("product stock changed message")
    fun verifyEntitiesCreatedMessage(): String {
        val message = ProductStockChangedDto(
                productId = 10,
                isInStock = true)
        return jacksonObjectMapper().writeValueAsString(message)
    }
}
