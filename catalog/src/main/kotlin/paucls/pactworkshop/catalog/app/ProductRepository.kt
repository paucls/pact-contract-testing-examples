package paucls.pactworkshop.catalog.app

interface ProductRepository {
    fun getAllProducts(): List<Product>
    fun getProduct(id: Int): Product
}
