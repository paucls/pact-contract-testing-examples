package paucls.pactworkshop.frontend.client

data class Product(
        val id: Int,
        val name: String,
        val type: String,
        val availability: ProductAvailability,
        val isFavouriteProduct: Boolean = false)

enum class ProductAvailability {
    InStock,
    OutOfStock
}
