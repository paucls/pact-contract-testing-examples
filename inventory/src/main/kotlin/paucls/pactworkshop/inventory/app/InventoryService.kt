package paucls.pactworkshop.inventory.app

import org.springframework.stereotype.Service
import paucls.pactworkshop.inventory.messaging.ProductInventoryChangedDto
import paucls.pactworkshop.inventory.messaging.ProductInventoryChangedPublisher

@Service
class InventoryService(private val productInventoryChangedPublisher: ProductInventoryChangedPublisher) {

    fun adjustProductLevel(c: AdjustProductLevelCommand) {
        // some interesting logic to calculate and update product inventory level ...
        // for demo purposes it just publishes the integration event
        productInventoryChangedPublisher.publish(ProductInventoryChangedDto(
                productId = c.productId,
                quantity = c.quantity))
    }
}

data class AdjustProductLevelCommand(
        val productId: Int,
        val locationId: String,
        val quantity: Int
)
