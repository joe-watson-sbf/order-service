package com.joedev.orderservice.controller;

import com.joedev.orderservice.dto.ResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private final String url = "http://localhost:8080/api/clients";


    @Test
    void addClients() throws Exception {
        ResponseDto actual = new ResponseDto("Clients saved successfully!");

        String content = "[{\"name\": \"Joe Watson SBF\",\"address\": " +
                "\"54773 Medellin, Colombia\"},{\"name\": \"John\"," +
                " \"address\": \"279 Santiago\"}]";


        this.mockMvc.perform(post(url + "/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString(actual.message())));
    }

    @Test
    void getAllClientsTest() throws Exception {
        String actual = "Joe Watson SBF";
        this.mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].name").value(actual));
    }

    @Test
    void getOneClientTest() throws Exception {

        String actual = "Joe Watson SBF";

        this.mockMvc.perform(get(url +"/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(actual));
    }



}