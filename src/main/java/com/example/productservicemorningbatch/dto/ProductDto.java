package com.example.productservicemorningbatch.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto<T1,T2,T3,T4,T5,T6>
{
    private T1 id;
    private T2 title;
    private T3 price;
    private T4 category;
    private T5 description;
    private T6 image;
}
