package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Intelligence Service Exception
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No se pudo determinar la posición o el mensaje.")
public class ISException extends Exception {

	private static final long serialVersionUID = 1L;

}
