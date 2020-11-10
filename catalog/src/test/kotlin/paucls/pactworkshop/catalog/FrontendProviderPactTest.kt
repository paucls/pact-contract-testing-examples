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
import paucls.pactworkshop.catalog.api.ProductController
import paucls.pactworkshop.catalog.app.Product
import paucls.pactworkshop.catalog.app.ProductAvailability.InStock
import paucls.pactworkshop.catalog.app.ProductAvailability.OutOfStock
import paucls.pactworkshop.catalog.app.ProductService


@Provider("catalog")
@PactFolder("pacts")
class FrontendProviderPactTest {

    private val productServiceMock: ProductService = mock()

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }

    @BeforeEach
    fun before(context: PactVerificationContext) {
        val testTarget = MockMvcTestTarget()
        testTarget.setControllers(ProductController(productServiceMock))
        context.target = testTarget
    }

    @State("products exist")
    fun products_exists() {
        whenever(productServiceMock.getAllProducts()).thenReturn(
                listOf(Product(id = 123, name = "Permanent Marker", type = "STATIONERY", availability = InStock)))
    }

    @State("product with id 10 exists")
    fun product_with_id_10_exists() {
        whenever(productServiceMock.getProduct(10)).thenReturn(
                Product(id = 10, name = "Colored Pencils Set", type = "STATIONERY", availability = OutOfStock)
        )
    }
}
