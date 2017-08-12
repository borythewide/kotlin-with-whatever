package org.x2framework.kotlin.model

import java.io.Serializable

data class ResponseData(
		var succeeded:Boolean = true,
		var message:String = "Succeeded",
		var data:Serializable? = null
)