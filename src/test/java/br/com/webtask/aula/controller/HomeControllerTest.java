/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.controller;

import br.com.webtask.aula.domain.repo.TaskRepo;
import br.com.webtask.aula.domain.repo.UserRepo;
import br.com.webtask.aula.util.LogadoUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author thiago
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@WebMvcTest(HomeController.class)
public class HomeControllerTest {
    
    @Autowired
    private MockMvc mock;
    
    @MockBean
    private LogadoUtil userLogado;
    
    @MockBean
    private TaskRepo taskRepo;
    
    @MockBean
    private UserRepo userRepo;
    
    @Test
    public void deveAcessarALogin() throws Exception{
        
        //Execucao
        ResultActions ra = mock.perform(get("/login"));
        
        //verificação
        ra.andExpect( status().isOk() ).andDo(print());
    }
    
    @Test
    public void deveRetornar404PAginaNaoExist() throws Exception{
               
        //execução
        ResultActions ra = mock.perform(get("/xyz"));
        
        //verificação
        ra.andExpect( status().isNotFound() )
                .andDo(print());
        
    }
    
    
}
