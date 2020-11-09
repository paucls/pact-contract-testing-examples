package paucls.pactworkshop.inventory.messaging

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.stereotype.Service
import paucls.pactworkshop.inventory.messaging.RoutingKeys.PRODUCT_INVENTORY_CHANGED

/**
 * Publish Integration Event, sending it as message to RabbitMQ, for other micro-services to consume
 */
@Service
class ProductInventoryChangedPublisher(
        private val rabbit: RabbitTemplate,
        private val messageConverter: MessageConverter
) {
    private val logger: Logger = LogManager.getLogger(ProductInventoryChangedPublisher::class.java)

    fun publish(event: ProductInventoryChangedDto) {
        logger.info("Publishing product inventory changed event $event")

        rabbit.convertAndSend(
                EXCHANGE,
                PRODUCT_INVENTORY_CHANGED,
                messageConverter.toMessage(event, MessageProperties())
        )
    }
}
