package paucls.pactworkshop.catalog

import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactFolder
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import paucls.pactworkshop.catalog.api.ProductsController
import paucls.pactworkshop.catalog.app.Product
import paucls.pactworkshop.catalog.app.ProductsService


@Provider("catalog")
@PactFolder("pacts")
class FrontendPactProviderTest {

    private val productsServiceMock: ProductsService = mock()

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }

    @BeforeEach
    fun before(context: PactVerificationContext) {
        val testTarget = MockMvcTestTarget()
        testTarget.setControllers(ProductsController(productsServiceMock))
        context.target = testTarget
    }

    @State("products exist")
    fun products_exists() {
        whenever(productsServiceMock.getAllProducts()).thenReturn(
                listOf(Product(id = 123, name = "Mastercard", type = "CREDIT_CARD")))
    }

}
