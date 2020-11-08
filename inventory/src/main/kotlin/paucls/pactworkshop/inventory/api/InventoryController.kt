package paucls.pactworkshop.inventory.api

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import paucls.pactworkshop.inventory.messaging.ProductStockChangedDto
import paucls.pactworkshop.inventory.messaging.ProductStockChangedPublisher

@RestController
class InventoryController(private val productStockChangedPublisher: ProductStockChangedPublisher) {

    @RequestMapping("/")
    fun index(): String = "Inventory service!"

    @RequestMapping(value = ["/productstocks/{id}"], method = [(RequestMethod.PUT)])
    fun updateProductStock(@PathVariable id: Int,
                           @RequestBody productStock: ProductStock): ProductStock {
        // call an app server ...
        productStockChangedPublisher.publish(ProductStockChangedDto(
                productId = id,
                isInStock = productStock.quantity > 0))
        return productStock
    }
}

data class ProductStock(
        val productId: Int,
        val quantity: Int
)
