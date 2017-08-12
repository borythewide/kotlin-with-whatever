package org.x2framework.kotlin.app.service.board

import org.springframework.stereotype.Service
import org.x2framework.kotlin.app.mapper.board.BoardMapper
import org.x2framework.kotlin.app.model.board.Board

@Service
open class BoardServiceImpl(val boardMapper: BoardMapper) : BoardService {

	override fun all(): List<Board> = boardMapper.getAll()
}