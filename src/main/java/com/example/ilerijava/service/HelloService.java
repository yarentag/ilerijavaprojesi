package com.example.ilerijava.service;

import com.example.ilerijava.entity.User;
import com.example.ilerijava.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service

public class HelloService {
    private final UserRepository userRepository;

    // Constructor Injection
    public HelloService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getHelloMessage() {
        return "Merhaba, Spring Boot!";
    }

    public String getGoodbyeMessage(Long id) {
        return userRepository.findById(id)
                .map(user -> "Görüşmek üzere, " + user.getUsername())
                .orElse("Kullanıcı bulunamadı.");
    }


}
