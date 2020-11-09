package paucls.pactworkshop.inventory.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import paucls.pactworkshop.inventory.app.AdjustProductLevelCommand
import paucls.pactworkshop.inventory.app.InventoryService

@RestController
class InventoryController(private val inventoryService: InventoryService) {

    @RequestMapping("/")
    fun index(): String = "Inventory service!"

    @RequestMapping(value = ["/product_levels/adjust"], method = [(RequestMethod.POST)])
    fun adjustProductLevel(@RequestBody c: AdjustProductLevelCommand): ResponseEntity<Unit> {
        inventoryService.adjustProductLevel(c)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}

