package org.x2framework.kotlin.app.controller.board

import org.springframework.statemachine.StateMachine
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
class BoardController(
		val boardService: BoardService, val stateMachine:StateMachine<String, String>
): Controllers() {
	@GetMapping("/all")
	fun all(): List<Board> {
		println("before stateMachine:: [$stateMachine.state]")
		stateMachine.sendEvent("E1")
		println("intermediate1 stateMachine:: [$stateMachine.state]")
		stateMachine.sendEvent("E2")
		println("intermediate2 stateMachine:: [$stateMachine.state]")
		stateMachine.sendEvent("end")
		println("after stateMachine:: [$stateMachine.state]")
		
		return boardService.all()
	}
	
	@PostMapping()
	fun insert(board:Board): ResponseData {
		println("before stateMachine:: [$stateMachine.state]")
		stateMachine.sendEvent("E2")
		println("after stateMachine:: [$stateMachine.state]")
		
		LOGGER.debug("insertBoard: [$board.toString()]")
		
		val cnt = boardService.insert(board)
		return ResponseData(true, "$cnt Board data has been inserted successfully")
	}
}