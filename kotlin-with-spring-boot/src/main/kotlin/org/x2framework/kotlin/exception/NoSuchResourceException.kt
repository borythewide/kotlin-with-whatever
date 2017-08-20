package org.x2framework.kotlin.exception

class NoSuchResourceException(
		message: String = "",
		cause: Throwable? = null
): RuntimeException(message, cause) {
	
}