package com.priyanshudev.productcatalog.proxyServer.controllers;

import com.priyanshudev.productcatalog.proxyServer.dtos.ErrorResponseDto;
import com.priyanshudev.productcatalog.proxyServer.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionAdvices {

//    @ExceptionHandler(Exception.class)
//    public String exception(Exception e) {
//        return e.getMessage();
//    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> notFoundException(NotFoundException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
