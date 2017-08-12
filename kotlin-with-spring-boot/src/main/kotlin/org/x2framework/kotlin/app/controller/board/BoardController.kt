package org.x2framework.kotlin.app.controller.board

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.x2framework.kotlin.app.model.board.Board
import org.x2framework.kotlin.app.service.board.BoardService

@RestController
@RequestMapping("/board")
class BoardController(val boardService: BoardService) {
	@GetMapping("/all")
	fun all(): List<Board> = boardService.all()
}