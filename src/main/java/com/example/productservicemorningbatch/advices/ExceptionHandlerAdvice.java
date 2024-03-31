package com.example.productservicemorningbatch.advices;

import com.example.productservicemorningbatch.dtos.ArithmeticExceptionDto;
import com.example.productservicemorningbatch.dtos.ArrayIndexOutOfBoundDto;
import com.example.productservicemorningbatch.dtos.ExceptionDto;
import com.example.productservicemorningbatch.exceptions.invalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticExpression(){
        ArithmeticExceptionDto dto=new ArithmeticExceptionDto();
        dto.setMessage("something went wrong due to Arithmetic Exception");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ArrayIndexOutOfBoundDto> handleArrayIndexOutOfBound(){
        return null;
    }
    @ExceptionHandler(invalidProductIdException.class)
    public ResponseEntity<ExceptionDto> handleInvalidProductException(invalidProductIdException exception){
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setProductId(exception.getProductId());
        exceptionDto.setMessage("Invalid product id passed, pass a valid request");
        return new ResponseEntity<>(exceptionDto,HttpStatus.BAD_REQUEST);
    }
}
