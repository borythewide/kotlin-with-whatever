package org.x2framework.kotlin.app.controller.board

import org.springframework.messaging.support.MessageBuilder
import org.springframework.statemachine.StateMachine
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.x2framework.kotlin.app.controller.Controllers
import org.x2framework.kotlin.app.model.board.Board
import org.x2framework.kotlin.app.service.board.BoardService
import org.x2framework.kotlin.model.ResponseData

@RestController
@RequestMapping("/board")
class BoardController(
		val boardService: BoardService, val stateMachine:StateMachine<String, String>
): Controllers() {
	@GetMapping("/all")
	fun all(): List<Board> {
		println("before stateMachine:: [$stateMachine.state]")
		stateMachine.sendEvent(
				MessageBuilder.withPayload("FORKED_EVENT").setHeaderIfAbsent("eventObj", "12345").build()
		)
		println("after stateMachine:: [$stateMachine.state]")
		
		return boardService.all()
	}
	
	@PostMapping()
	fun insert(board:Board): ResponseData {
		LOGGER.debug("insertBoard: [$board.toString()]")
		
		val cnt = boardService.insert(board)
		return ResponseData(true, "$cnt Board data has been inserted successfully")
	}
	
	@GetMapping("/{uid}")
	fun read(@PathVariable("uid") uid:Int): Board{
		LOGGER.debug("read Board: $uid")
		
		val board = boardService.read(Board(uid = uid))
		return board
	}
}