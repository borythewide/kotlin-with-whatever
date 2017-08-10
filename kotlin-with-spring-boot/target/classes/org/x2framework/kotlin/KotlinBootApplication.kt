package org.x2framework.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = arrayOf("org.x2framework.kotlin.config", "org.x2framework.kotlin.app"))
open class KotlinBootApplication

fun main(args: Array<String>) {
	SpringApplication.run(KotlinBootApplication::class.java, *args)
}