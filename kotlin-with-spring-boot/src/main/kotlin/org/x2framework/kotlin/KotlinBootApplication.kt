package org.x2framework.kotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration
import org.springframework.context.annotation.Import
import org.x2framework.kotlin.config.PersistenceConfig
import org.x2framework.kotlin.config.SwaggerConfig

@SpringBootApplication(scanBasePackages = arrayOf("org.x2framework.kotlin.app"))
@EnableAutoConfiguration(exclude=arrayOf(DataSourceAutoConfiguration::class, TransactionAutoConfiguration::class))
@Import(PersistenceConfig::class, SwaggerConfig::class)
open class KotlinBootApplication

fun main(args: Array<String>) {
	SpringApplication.run(KotlinBootApplication::class.java, *args)
}