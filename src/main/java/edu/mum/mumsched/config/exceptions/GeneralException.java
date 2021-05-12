package edu.mum.mumsched.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralException extends RuntimeException{
    public GeneralException(String message){
        super(message);
    }

    public GeneralException(String message, Throwable cause){
        super(message, cause);
    }
}
