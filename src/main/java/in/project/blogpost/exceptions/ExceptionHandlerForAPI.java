package in.project.blogpost.exceptions;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.project.blogpost.payloads.APIMessage;
import io.swagger.annotations.Api;

@RestControllerAdvice
public class ExceptionHandlerForAPI{
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<Map<String, String>> invalidArgumenthandler(MethodArgumentNotValidException ex){
		Map<String, String> response= new HashMap<String, String>();
		ex.getAllErrors().forEach((error)->{
			String fieldName= ((FieldError)error).getField();
			String message= error.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<APIMessage> resoureNotFoundHandler(ResourceNotFoundException res)
	{
			String message= res.getMessage();
			APIMessage apiMessage= new APIMessage(message, false);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiMessage);
	}
	
	@ExceptionHandler({BadCredentialsException.class})
	public ResponseEntity<APIMessage> BadCredentialsExceptionhandler(BadCredentialsException e)
	{
		APIMessage apiMessage= new APIMessage();
		apiMessage.setMessage(e.getMessage());
		apiMessage.setSuccess(false);
		return new ResponseEntity<APIMessage>(apiMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({UsernameNotFoundException.class})
	public ResponseEntity<APIMessage> userNameNotFoundExceptionhandler(UsernameNotFoundException ex)
	{
		APIMessage message= new APIMessage();
		message.setMessage(ex.getMessage());
		message.setSuccess(false);
		return new ResponseEntity<APIMessage>(message, HttpStatus.OK);
		
	}
	
	@ExceptionHandler({StringIndexOutOfBoundsException.class})
	public ResponseEntity<APIMessage> exception(){
		return new ResponseEntity<APIMessage>(new APIMessage("SomeThing went wrong!!!", false), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler({NullPointerException.class})
	public ResponseEntity<APIMessage> nullpointerhandler()
	{
		return new ResponseEntity<APIMessage>(new APIMessage("Given input is not correct!!!",false), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({FileNotFoundException.class})
	public ResponseEntity<APIMessage> filenotfoundhandler(){
		return new ResponseEntity<APIMessage>(new APIMessage("File not found!!", false), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
	public ResponseEntity<APIMessage> notaccptable(){
		return new ResponseEntity<APIMessage>(new APIMessage("Failed!",false),HttpStatus.BAD_REQUEST);
	}
	
	
	

}
