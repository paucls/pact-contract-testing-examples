package paucls.pactworkshop.frontend.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "catalog", url = "\${CATALOG_BASE_URL}")
interface CatalogClient {

    @RequestMapping(method = [RequestMethod.GET], value = ["/products"])
    fun getAllProducts(): List<Product>

    @RequestMapping(method = [RequestMethod.GET], value = ["/products/{id}"])
    fun getProduct(@PathVariable("id") id: Int): Product

    @RequestMapping(method = [RequestMethod.POST], value = ["/products/{id}/favourite"])
    fun favouriteProduct(@PathVariable("id") id: Int)
}
