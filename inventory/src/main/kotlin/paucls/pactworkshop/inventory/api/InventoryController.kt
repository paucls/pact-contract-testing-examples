package paucls.pactworkshop.inventory.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InventoryController() {

    @RequestMapping("/")
    fun index(): String = "Inventory service!"
}
