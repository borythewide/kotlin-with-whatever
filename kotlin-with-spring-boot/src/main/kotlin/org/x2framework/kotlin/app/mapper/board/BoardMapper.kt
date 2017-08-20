package org.x2framework.kotlin.app.mapper.board

import org.apache.ibatis.annotations.Mapper
import org.x2framework.kotlin.app.model.board.Board

@Mapper
interface BoardMapper {
	fun getOne():Int
	
	fun getAll():List<Board>
	
	fun insert(board:Board): Int
	
	fun addReadCount(board:Board)
	
	fun get(board:Board): Board?
}