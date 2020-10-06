package paucls.pactworkshop.frontend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class FrontendApplication

fun main(args: Array<String>) {
	runApplication<FrontendApplication>(*args)
}
