package paucls.pactworkshop.inventory.messaging

data class ProductInventoryChangedDto(
        val productId: Int,
        val quantity: Int
)
