package org.x2framework.kotlin.app.controller.board

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.x2framework.kotlin.app.controller.Controllers
import org.x2framework.kotlin.app.model.board.Board
import org.x2framework.kotlin.app.service.board.BoardService
import org.x2framework.kotlin.model.ResponseData

@RestController
@RequestMapping("/board")
class BoardController(val boardService: BoardService): Controllers() {
	@GetMapping("/all")
	fun all(): List<Board> = boardService.all()
	
	@PostMapping()
	fun insert(board:Board): ResponseData {
		LOGGER.debug("insertBoard: [$board.toString()]")
		
		val cnt = boardService.insert(board)
		return ResponseData(true, "$cnt Board data has been inserted successfully")
	}
}