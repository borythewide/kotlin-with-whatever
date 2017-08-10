package org.x2framework.kotlin.app.controller.greet

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.x2framework.kotlin.app.service.greet.GreetService
import org.x2framework.kotlin.model.greet.GreetModel
import java.util.Date

@RestController
@RequestMapping("/greet")
class GreetController (val greetService:GreetService){
    @GetMapping("/{name}")
	fun greet(@PathVariable("name") name:String) = GreetModel(name, greetService.getGreetDate())
}