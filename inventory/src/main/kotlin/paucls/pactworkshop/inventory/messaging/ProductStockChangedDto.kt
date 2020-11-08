package paucls.pactworkshop.inventory.messaging

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductStockChangedDto(
        val productId: Int,
        @get:JsonProperty("isInStock")
        val isInStock: Boolean
)
