package org.x2framework.kotlin.model.greet

import org.x2framework.kotlin.model.KotlinModel
import java.util.Date

@KotlinModel
data class Greet (val name:String, val date:Date, val number:Int)