package com.example.ilerijava;

import com.example.ilerijava.controller.HelloController;
import com.example.ilerijava.entity.User;
import com.example.ilerijava.repository.UserRepository;
import com.example.ilerijava.service.HelloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HelloControllerTest {

    private MockMvc mockMvc;
    private HelloService helloService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Mock bağımlılıkları oluştur
        helloService = mock(HelloService.class);
        userRepository = mock(UserRepository.class);

        // Controller'ı mock nesnelerle oluştur
        HelloController helloController = new HelloController(helloService, userRepository);


        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    public void testSayHello() throws Exception {

        when(helloService.getHelloMessage()).thenReturn("Hello, Spring Boot!");

        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Spring Boot!"));
    }

    @Test
    public void testGetUserById() throws Exception {
        // Mock User nesnesi
        User testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("TestUser");


        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));


        mockMvc.perform(get("/api/user").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("User: TestUser"));
    }

    @Test
    public void testSayGoodbye() throws Exception {

        when(helloService.getGoodbyeMessage(1L)).thenReturn("Goodbye, TestUser");


        mockMvc.perform(get("/api/goodbye").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Goodbye, TestUser"));
    }
}
