package com.example.ilerijava.controller;
import com.example.ilerijava.entity.User;
import com.example.ilerijava.repository.UserRepository;
import com.example.ilerijava.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class HelloController {

    private final HelloService helloService;
    private final UserRepository userRepository;

    public HelloController(HelloService helloService, UserRepository userRepository) {
        this.helloService = helloService;
        this.userRepository = userRepository;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return helloService.getHelloMessage();
    }

    @GetMapping("/goodbye")
    public String sayGoodbye(@RequestParam(required = false, defaultValue = "1") Long id) {
        return helloService.getGoodbyeMessage(id);
    }


    @GetMapping("/user")
    public String getUserById(@RequestParam Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(u -> "Kullanıcı: " + u.getUsername()).orElse("Kullanıcı bulunamadı");
    }


}

