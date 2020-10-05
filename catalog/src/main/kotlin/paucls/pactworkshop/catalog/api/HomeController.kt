package paucls.pactworkshop.catalog.api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @RequestMapping("/")
    fun index(): String {
        return "Catalog service!"
    }

}
