package paucls.pactworkshop.catalog.app

data class Product(
        val id: Int,
        val name: String,
        val type: String,
        var availability: ProductAvailability)

enum class ProductAvailability {
    InStock,
    OutOfStock
}
