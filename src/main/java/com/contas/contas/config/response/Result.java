package com.contas.contas.config.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class Result<T> {

	private int code;
	private String message;
	private T body;
	private List<String> errors;
	private Boolean hasNext;
	private Long total;

	public Result(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public Result(int code, T result) {
		this.code = code;
		this.body = result;
	}

	public Result(int code, List<String> errors) {		
		this.code = code;
		this.errors = errors;
	}

	public Result(int code, T result, Boolean hasNext, Long total) {
		this.code = code;
		this.body = result;
		this.hasNext = hasNext;
		this.total = total;
	}
	public Result() {	
	}

	public static <T> Result<T> ok(T result) {
		return new Result<>(HttpStatus.OK.value(), result);
	}

	public static <T> Result<T> ok(T result, Boolean hasNext, Long total) {
		return new Result<>(HttpStatus.OK.value(), result, hasNext, total);
	}
	public static <T> Result<T> create(T result) {
		return new Result<>(HttpStatus.CREATED.value(), result);
	}

	public static <T> Result<T> badRequest(List<String> errors) {
		return new Result<>(HttpStatus.BAD_REQUEST.value(), errors);
	}

	public static <T> Result<T> buildResultError(int code, String message) {
		return new Result<>(code,message);
	}

	public static <T> Result<T> notFound(List<String> errors) {	
		return new Result<>(HttpStatus.NOT_FOUND.value(), errors);
	}

	@JsonIgnore
	public HttpHeaders getHeadersNosniff(){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("X-Content-Type-Options","nosniff");
		return httpHeaders;
	}

}
