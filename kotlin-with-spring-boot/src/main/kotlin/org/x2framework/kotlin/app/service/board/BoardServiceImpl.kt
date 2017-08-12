package org.x2framework.kotlin.app.service.board

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.x2framework.kotlin.app.mapper.board.BoardMapper
import org.x2framework.kotlin.app.model.board.Board

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
class BoardServiceImpl(val boardMapper: BoardMapper) : BoardService {
	override fun all(): List<Board> = boardMapper.getAll()
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=arrayOf(Exception::class), readOnly=false)
	override fun insert(board: Board): Int = boardMapper.insert(board)
}