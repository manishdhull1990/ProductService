package com.example.productservicemorningbatch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SampleTestFile {
    @Test
    void testOnePlusOneIsTwo(){

        int i=1+1; //Arrange + Act

        assert i==2;
    }
    @Test
    void testMultiplyFunction(){

        int x=2*2; //Arrange + Act
        int y=3*3;

        assert x==4;
        assert y==9;
        assertEquals(7,y);
    }
}
