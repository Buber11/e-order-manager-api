//package com.example.eordermanagerapi;
//
//import com.example.eordermanagerapi.Controller.AuthenticationController;
//import com.example.eordermanagerapi.DTO.UserDTO.SignUPAnswerDto;
//import com.example.eordermanagerapi.DTO.UserDTO.SignUpDTO;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//
//@WebMvcTest(AuthenticationController.class)
//public class SignUpTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testRegister() throws Exception {
//        // Przygotowanie danych testowych
//
//
//        SignUpDTO signUpDTO = new SignUpDTO(
//
//                "password123", // password
//                "John", // name
//                "Doe", // surname
//                "john.doe@example.com", // email
//                "M", // sex
//                "A", // status
//                "CLIENT"// role
//        );
//
//        SignUPAnswerDto answerDto = new SignUPAnswerDto(
//
//                "John",
//                "Doe",
//                "john.doe@example.com",
//                "M",
//                "A",
//                "CLIENT"
//
//       );
//
//
//        // Wywołanie metody kontrolera za pomocą żądania HTTP POST
//        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(signUpDTO)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpectAll(
//                        MockMvcResultMatchers.jsonPath("$.password").doesNotExist(),
//                        MockMvcResultMatchers.jsonPath("$.name").value(answerDto.name()),
//                        MockMvcResultMatchers.jsonPath("$.surname").value(answerDto.surname()),
//                        MockMvcResultMatchers.jsonPath("$.email").value(answerDto.email()),
//                        MockMvcResultMatchers.jsonPath("$.sex").value(answerDto.sex()),
//                        MockMvcResultMatchers.jsonPath("$.status").value(answerDto.status()),
//                        MockMvcResultMatchers.jsonPath("$.role").value(answerDto.role())
//                );
//    }
//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
