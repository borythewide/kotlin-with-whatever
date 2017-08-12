package org.x2framework.kotlin.app.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.x2framework.kotlin.model.ResponseData

@ControllerAdvice
class RestResponseExceptionHandler: ResponseEntityExceptionHandler() {
	@ExceptionHandler(Exception::class)
    protected fun handleConflict(ex:RuntimeException, request:WebRequest): ResponseEntity<Any> {
		val responseData = ResponseData(false, "Error: $ex.getMessage()")
        return handleExceptionInternal(ex, responseData, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}