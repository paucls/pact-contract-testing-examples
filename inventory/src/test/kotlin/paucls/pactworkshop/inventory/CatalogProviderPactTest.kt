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
import paucls.pactworkshop.inventory.messaging.ProductInventoryChangedDto


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

    @State("a product inventory level has changed")
    fun `a product inventory level has changed`() {
    }

    @PactVerifyProvider("product inventory changed message")
    fun verifyEntitiesCreatedMessage(): String {
        val message = ProductInventoryChangedDto(
                productId = 10,
                quantity = 1000)
        return jacksonObjectMapper().writeValueAsString(message)
    }
}
