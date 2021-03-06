package com.techvisio.einstitution.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techvisio.einstitution.beans.Response;
import com.techvisio.einstitution.util.CustomLogger;
import com.techvisio.einstitution.util.EISystemException;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerController {

	private MessageSource errorSource;

	private static CustomLogger logger = CustomLogger
			.getLogger(ExceptionHandlerController.class);

	@Autowired
	public ExceptionHandlerController(MessageSource errorSource) {
		this.errorSource = errorSource;
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Response> handleException(Exception exp) {
		// System.out.println(((UnexpectedRollbackException)e).getRootCause());
		Response response = new Response();

		String currentTime = new SimpleDateFormat("yyyyMMddHHmmssSSS")
				.format(new Date());
		String message = currentTime;

		logger.error(message + "::" + exp.getMessage(), exp);

		response.setError("We're sorry, but we are unable to perform your request at the moment. Error Code : "
				+ message);
		return new ResponseEntity<Response>(response,
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(EISystemException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Response> handleException(EISystemException exp) {
		// System.out.println(((UnexpectedRollbackException)e).getRootCause());
		Response response = new Response();

		String currentTime = new SimpleDateFormat("yyyyMMddHHmmssSSS")
				.format(new Date());
		String message = "We're sorry, but we are unable to perform your request at the moment. Error Code : "
				+ currentTime;
		message = message + "." + exp.getMessage();
		logger.error(message, exp);

		response.setError(message);
		return new ResponseEntity<Response>(response,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
