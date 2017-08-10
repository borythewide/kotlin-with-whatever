package org.x2framework.kotlin.app.service.greet

import org.springframework.stereotype.Service
import java.util.Date

@Service
open class GreetServiceImpl: GreetService{
	override fun getGreetDate(): Date = Date()
}