package paucls.pactworkshop.frontend.client

data class Product(
        val id: Int,
        val name: String,
        val type: String,
        val availability: ProductAvailability)

enum class ProductAvailability {
    InStock,
    OutOfStock
}
