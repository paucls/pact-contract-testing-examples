package paucls.pactworkshop.catalog.messaging

const val EXCHANGE = "demo-exchange"

object RoutingKeys {
    const val PRODUCT_STOCK_CHANGED = "inventory.product-stock-changed"
}

object Queues {
    const val PRODUCT_STOCK_CHANGED = "catalog.product-stock-changed"
}
