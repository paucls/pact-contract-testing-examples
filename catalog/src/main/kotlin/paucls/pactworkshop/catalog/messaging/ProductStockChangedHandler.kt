package paucls.pactworkshop.catalog.messaging

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.amqp.core.ExchangeTypes
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import paucls.pactworkshop.catalog.app.ProductService


@Component
class ProductStockChangedHandler(private val productService: ProductService) {

    private val logger: Logger = LogManager.getLogger(ProductStockChangedHandler::class.java)

    @RabbitListener(
            bindings = [(QueueBinding(
                    value = Queue(value = Queues.PRODUCT_INVENTORY_CHANGED, durable = "true"),
                    exchange = Exchange(EXCHANGE, type = ExchangeTypes.TOPIC, durable = "true", autoDelete = "false"),
                    key = [RoutingKeys.PRODUCT_INVENTORY_CHANGED]))]
    )
    fun handleMessage(message: ProductInventoryChangedDto) {
        logger.info("Received product stock changed $message")

        productService.syncProductAvailability(message.productId, message.quantity)
    }
}
