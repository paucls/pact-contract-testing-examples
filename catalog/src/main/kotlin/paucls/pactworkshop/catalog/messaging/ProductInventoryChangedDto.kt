package paucls.pactworkshop.catalog.messaging

data class ProductInventoryChangedDto(
        val productId: Int,
        val quantity: Int
)
