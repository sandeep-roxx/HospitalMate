package com.verma.sandeep.hospital.mate.global.exception.handler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.verma.sandeep.hospital.mate.dto.ErrorMessage;
import com.verma.sandeep.hospital.mate.exception.AppointmentNotFoundException;
import com.verma.sandeep.hospital.mate.exception.DoctorNotFoundException;
import com.verma.sandeep.hospital.mate.exception.MedicosNotFoundException;
import com.verma.sandeep.hospital.mate.exception.PatientsNotFoundException;
import com.verma.sandeep.hospital.mate.exception.SpecializationNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
	
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                 		   new ErrorMessage(new Date().toString(),
                 				   ex.getMessage(),
                 				   HttpStatus.INTERNAL_SERVER_ERROR.value(),
                 				   HttpStatus.INTERNAL_SERVER_ERROR.name()
                 				   )
                 		   );
	    }

    @ExceptionHandler(SpecializationNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleSpecializationNotFoundException(SpecializationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        		                                   .body(
        		                                		   new ErrorMessage(new Date().toString(),
        		                                				   ex.getMessage(),
        		                                				   HttpStatus.NOT_FOUND.value(),
        		                                				   HttpStatus.NOT_FOUND.name()
        		                                				   )
        		                                		   );
    }
    
    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleDoctorNotFoundException(DoctorNotFoundException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        		                                   .body(
        		                                		   new ErrorMessage(new Date().toString(),
        		                                				   e.getMessage(),
        		                                				   HttpStatus.INTERNAL_SERVER_ERROR.value(),
        		                                				   HttpStatus.INTERNAL_SERVER_ERROR.name()
        		                                				   )
        		                                		   );
    }
    
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorMessage> handleFileNotFoundExceptions(IOException ioe) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        		                                   .body(
        		                                		   new ErrorMessage(new Date().toString(),
        		                                				   ioe.getMessage(),
        		                                				   HttpStatus.INTERNAL_SERVER_ERROR.value(),
        		                                				   HttpStatus.INTERNAL_SERVER_ERROR.name()
        		                                				   )
        		                                		   );
    }
    
    @ExceptionHandler(PatientsNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlePatientsNotFoundException(PatientsNotFoundException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        		                                   .body(
        		                                		   new ErrorMessage(new Date().toString(),
        		                                				   e.getMessage(),
        		                                				   HttpStatus.INTERNAL_SERVER_ERROR.value(),
        		                                				   HttpStatus.INTERNAL_SERVER_ERROR.name()
        		                                				   )
        		                                		   );
    }
    
    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleAppointmentNotFoundException(AppointmentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        		                                   .body(
        		                                		   new ErrorMessage(new Date().toString(),
        		                                				   e.getMessage(),
        		                                				   HttpStatus.INTERNAL_SERVER_ERROR.value(),
        		                                				   HttpStatus.INTERNAL_SERVER_ERROR.name()
        		                                				   )
        		                                		   );
    }
    
    @ExceptionHandler(MedicosNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleMedicosNotFoundException(MedicosNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        		                                   .body(
        		                                		   new ErrorMessage(new Date().toString(),
        		                                				   e.getMessage(),
        		                                				   HttpStatus.NOT_FOUND.value(),
        		                                				   HttpStatus.NOT_FOUND.name()
        		                                				   )
        		                                		   );
    }

}
