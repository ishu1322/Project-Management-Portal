package com.cts.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class MaximumResourceLimitReachedException extends Exception{
	public MaximumResourceLimitReachedException(String message) {
		super(message);
	}
}
