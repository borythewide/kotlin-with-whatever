package org.x2framework.kotlin.app.model.board

import org.apache.ibatis.type.Alias
import java.io.Serializable
import java.sql.Date
import org.x2framework.kotlin.model.KotlinModel

@KotlinModel
@Alias("board")
class Board : Serializable {
	var uid: Int = -1
	var title: String = ""
	var contents: String = ""
	var createdBy: String = ""
	var createdDate: Date = Date(System.currentTimeMillis())
	var updatedBy: String = ""
	var updatedDate: Date = Date(System.currentTimeMillis())
	
	override fun toString(): String =
"""Board [
    uid=$uid, title=$title, contents=$contents, 
    createdBy=$createdBy, createdDate=$createdDate,
    updatedBy=$updatedBy, updatedDate=$updatedDate
]
"""
}
