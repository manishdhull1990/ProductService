package com.example.productservicemorningbatch.commons;

import com.example.productservicemorningbatch.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {
    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public UserDto validateToken(String token){
        //Call UserService validate Token API to validate
        ResponseEntity<UserDto> response = restTemplate.postForEntity(
                "http://localhost:3030/users/validate/"+token,
                null,
                UserDto.class
        );
        System.out.println(response);
        if (response.getBody()==null){
            //Token is invalid
            return null;
        }

        return response.getBody();
    }
}
