package com.raiseTech.lastSubject.exeption;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.Map;

@RestControllerAdvice
public class SQLExceptionHandler {
	@ExceptionHandler(value=SQLException.class)
	public ResponseEntity<Map<String, String>> SQLException(
		SQLException e, HttpServletRequest request
	) {
		Map<String, String> body = Map.of("timestamp", ZonedDateTime.now().toString(), "status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "message", e.getMessage(), "path", request.getRequestURI());
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
