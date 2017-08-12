package org.x2framework.kotlin.app.service.greet

import org.springframework.stereotype.Service
import org.x2framework.kotlin.app.mapper.sample.SampleMapper
import java.util.Date

@Service
open class GreetServiceImpl(
    val sampleMapper:SampleMapper
): GreetService{
	
	override fun getGreetDate(): Date = Date()
	override fun getOne():Int = sampleMapper.getOne()
}