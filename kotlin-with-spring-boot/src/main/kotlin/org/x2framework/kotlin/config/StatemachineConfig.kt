package org.x2framework.kotlin.config

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.StateMachineBuilder
import org.springframework.statemachine.config.StateMachineBuilder.Builder

@Configuration
open class StatemachineConfig (val applicationContext: ApplicationContext){
	
	@Bean
	@Scope(scopeName="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
	open fun stateMachine(): StateMachine<String, String>{
		val builder: Builder<String, String> = StateMachineBuilder.builder()
		builder.configureConfiguration()
            .withConfiguration()
				.beanFactory(applicationContext.autowireCapableBeanFactory)
                .autoStartup(true)
		
        builder.configureStates()
		    .withStates()
                .initial("SI")
                .end("SF")
                .states(setOf("S1", "S2", "S3"))
		
		builder.configureTransitions()
		    .withExternal()
                .source("SI").target("S1").event("E1").action(onE1()).and()
            .withExternal()
                .source("S1").target("S2").event("E2").action(onE2()).and()
            .withExternal()
                .source("S2").target("SF").event("end");
                
		return builder.build()
	}
	
	@Bean
	open fun onE1(): Action<String, String> = Action<String, String> { println("onE1: $it.target.id") }
	
	@Bean
	open fun onE2(): Action<String, String> = Action<String, String> { println("onE2: $it.target.id") }
	
}