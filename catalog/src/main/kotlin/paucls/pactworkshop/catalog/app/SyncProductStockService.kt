package paucls.pactworkshop.catalog.app

import org.springframework.stereotype.Service

@Service
class SyncProductStockService {
    fun syncProductStock(productId: Int, isInStock: Boolean) {
        // logic to update product stock in catalog context
        // ...
    }
}
