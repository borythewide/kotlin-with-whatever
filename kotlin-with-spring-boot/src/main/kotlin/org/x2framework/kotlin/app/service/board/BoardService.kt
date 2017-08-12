package org.x2framework.kotlin.app.service.board

import org.x2framework.kotlin.app.model.board.Board

interface BoardService {
	fun all(): List<Board>
	
	fun insert(board:Board): Int
}