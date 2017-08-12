package org.x2framework.kotlin.app.service.greet

import java.util.Date

interface GreetService {
	fun getGreetDate(): Date
	fun getOne(): Int
}