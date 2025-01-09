package com.example.ilerijava.service;

import org.springframework.stereotype.Service;
@Service

public class HelloService {

    public String getHelloMessage() {
        return "Merhaba, Spring Boot!";
}
    public String getGoodbyeMessage() {
        return "Görüşmek üzere!";
    }

}
