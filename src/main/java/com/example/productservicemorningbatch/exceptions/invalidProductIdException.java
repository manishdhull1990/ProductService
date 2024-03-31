package com.example.productservicemorningbatch.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class invalidProductIdException extends Exception{
    private Long productId;
    public invalidProductIdException(Long productId,String message){
        super(message);
        this.productId=productId;
    }
}
