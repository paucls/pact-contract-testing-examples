package paucls.pactworkshop.catalog.messaging

const val EXCHANGE = "demo-exchange"

object RoutingKeys {
    const val PRODUCT_INVENTORY_CHANGED = "inventory.product-inventory-changed"
}

object Queues {
    const val PRODUCT_INVENTORY_CHANGED = "catalog.product-inventory-changed"
}
