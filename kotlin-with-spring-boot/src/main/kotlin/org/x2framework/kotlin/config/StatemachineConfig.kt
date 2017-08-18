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
                .initial("INITIAL_STATE")
				.fork("FORKED_STATE")
				.join("JOINED_STATE")
				.state("AFTER_JOIN", afterJoined())
				.end("ALL_END")
				.and()
				    .withStates()
				    .parent("FORKED_STATE")
				    .initial("FORKED_ONE_INITIAL")
				    .state("FORKED_ONE", onForkedOne())
				    .end("FORKED_ONE_END")
				.and()
				    .withStates()
				    .parent("FORKED_STATE")
				    .initial("FORKED_TWO_INITIAL")
				    .state("FORKED_TWO", onForkedTwo())
				    .end("FORKED_TWO_END")

		builder.configureTransitions()
		    .withExternal()
				.source("INITIAL_STATE")
				.target("FORKED_STATE").event("FORKED_EVENT")
			.and()
		    .withFork()
		        .source("FORKED_STATE")
		        .target("FORKED_ONE")
		        .target("FORKED_TWO")
		    .and()
		    .withJoin()
		        .source("FORKED_ONE")
		        .source("FORKED_TWO")
		        .target("JOINED_STATE")
		    .and()
		    .withExternal()
		        .source("JOINED_STATE")
		        .target("AFTER_JOIN")
                
		return builder.build()
	}
	
	@Bean
	open fun onForkedOne(): Action<String, String> = Action<String, String> { println("!!!onForkedOne: ${it.message.headers}") }
	
	@Bean
	open fun onForkedTwo(): Action<String, String> = Action<String, String> { println("@@@onForkedTwo: ${it.target.id}") }
	
	@Bean
	open fun afterJoined(): Action<String, String> = Action<String, String> { println("###onJoined: ${it.target.id}") }
	
}