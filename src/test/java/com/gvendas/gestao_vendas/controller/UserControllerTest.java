package com.gvendas.gestao_vendas.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gvendas.gestao_vendas.controller.categoria.CategoriaControlador;

@WebMvcTest(CategoriaControlador.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void deveRetornarMensagemDeBoasVindas_QuandoChamarEndpointerUser() throws Exception {
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("Bem-vindo ao sistema de gestão de vendas!"));
                
    }
    
}
