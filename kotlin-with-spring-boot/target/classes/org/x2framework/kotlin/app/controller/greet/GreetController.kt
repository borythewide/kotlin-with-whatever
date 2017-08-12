package org.x2framework.kotlin.app.controller.greet

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.x2framework.kotlin.app.controller.Controllers
import org.x2framework.kotlin.app.service.greet.GreetService
import org.x2framework.kotlin.model.greet.Greet

@RestController
@RequestMapping("/greet")
class GreetController(val greetService: GreetService): Controllers() {
	@GetMapping("/{name}")
	fun greet(@PathVariable("name") name: String) 
		= Greet(name, greetService.getGreetDate(), greetService.getOne())
	
	@GetMapping("/")
	fun defaultGreet()
	    = Greet("borythewide", greetService.getGreetDate(), greetService.getOne())
}