package paucls.pactworkshop.catalog.messaging

data class ProductStockChangedDto(
        val productId: Int,
        val isInStock: Boolean
)