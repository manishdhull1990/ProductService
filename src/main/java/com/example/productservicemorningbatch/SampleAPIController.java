package com.example.productservicemorningbatch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//This controller supports REST API's (HTTP Requests).
//This request coming to endpoint /hello, transfer them to this controller
@RestController
@RequestMapping("/hello")
public class SampleAPIController {
    @GetMapping("/{name}/{number}")
    public String sayHello(@PathVariable("name") String name,@PathVariable("number") int number){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=1;i<=number;i++){
            stringBuilder.append("Hello "+name);
        }
        return stringBuilder.toString();
    }
}
