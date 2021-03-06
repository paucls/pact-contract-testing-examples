package paucls.pactworkshop.frontend

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import paucls.pactworkshop.frontend.client.CatalogClient
import paucls.pactworkshop.frontend.client.Product
import paucls.pactworkshop.frontend.client.ProductAvailability

@RestClientTest(CatalogClient::class)
class CatalogClientTest {

    @Autowired
    private lateinit var catalogClient: CatalogClient

    @Autowired
    private lateinit var mockRestServiceServer: MockRestServiceServer

    @BeforeEach
    fun setUp() {
        mockRestServiceServer.reset()
    }

    @AfterEach
    fun tearDown() {
        mockRestServiceServer.verify()
    }

    @Disabled
    @Test
    fun get_all_products_when_products_exist() {
        val json = jacksonObjectMapper().writeValueAsString(listOf(Product(
                id = 123,
                type = "STATIONERY",
                name = "Permanent Marker",
                availability = ProductAvailability.InStock)))
        mockRestServiceServer
                .expect(requestTo("/products"))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON))

        val products = catalogClient.getAllProducts()

        assertThat(products).hasSize(1)
        assertThat(products[0].id).isEqualTo(123)
        assertThat(products[0].name).isEqualTo("Permanent Marker")
        assertThat(products[0].type).isEqualTo("STATIONERY")
        assertThat(products[0].availability).isEqualTo(ProductAvailability.InStock)
    }

    @Disabled
    @Test
    fun get_one_product_when_product_exists() {
        val json = jacksonObjectMapper().writeValueAsString(Product(
                id = 10,
                type = "STATIONERY",
                name = "Colored Pencils Set",
                availability = ProductAvailability.OutOfStock))
        mockRestServiceServer
                .expect(requestTo("/products/10"))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON))

        val product = catalogClient.getProduct(10)

        assertThat(product.id).isEqualTo(10)
        assertThat(product.type).isEqualTo("STATIONERY")
        assertThat(product.name).isEqualTo("Colored Pencils Set")
        assertThat(product.availability).isEqualTo(ProductAvailability.OutOfStock)
    }

    @Disabled
    @Test
    fun favourite_a_product() {
        mockRestServiceServer
                .expect(requestTo("/products/10/favourite"))
                .andRespond(withNoContent())

        catalogClient.favouriteProduct(10)
    }
}
