package com.strikers.elitematrimony.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.strikers.elitematrimony.dto.ExceptionResponseDto;
import com.strikers.elitematrimony.util.ApiConstant;

@ControllerAdvice
public class ApiExceptionHandler {

	Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

	/**
	 * handleAgeNotMatchedException()
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(AgeNotMatchedException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionResponseDto handleAgeNotMatchedException(AgeNotMatchedException ex) {
		String msg = ex.getMessage();
		return new ExceptionResponseDto(ApiConstant.VALIDATION_FAILED, msg);
	}

	/**
	 * handleMobileNumberExistException()
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MobileNumberExistException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionResponseDto handleMobileNumberExistException(MobileNumberExistException ex) {
		String msg = ex.getMessage();
		return new ExceptionResponseDto(ApiConstant.VALIDATION_FAILED, msg);
	}

}
