package com.contas.contas.config.exceptions.handler;

import com.contas.contas.config.exceptions.BusinessException;
import com.contas.contas.config.exceptions.NotFoundException;
import com.contas.contas.config.response.Result;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerConfig {
	
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Result<List<String>>> businessExceptionHandler(BusinessException ex) {
		return ResponseEntity.badRequest().body(Result.badRequest(List.of(ex.getMessage())));
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Result<Object>> notFoundExceptionHandler(NotFoundException ex) {
		Result<Object> result = Result.notFound(List.of(ex.getMessage()));
		return ResponseEntity.status(result.getCode()).body(result);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Result<List<String>>> iIllegalArgumentException(IllegalArgumentException ex){
		return ResponseEntity.badRequest().body(Result.badRequest(List.of(ex.getMessage())));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Result<String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		List<ObjectError> allErrors = ex.getAllErrors();
		List<String> errors = allErrors.stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).sorted().toList();
		return ResponseEntity.badRequest().body(Result.badRequest(errors));
	}

}
