package br.com.webtask.aula.controller;

import br.com.webtask.aula.controller.service.TaskService;
import br.com.webtask.aula.domain.repo.TaskRepo;
import br.com.webtask.aula.domain.repo.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest // starta toda a aplicação. Banco em memória, servidor web, etc. Com ele, não precisa mockar (apagar as propriedades @Mockbean abaixo). Roda o arquivo de configuração application-test.properties
@AutoConfigureMockMvc // !
//@WebMvcTest(TaskControllerTest.class) // annotations importantes!
class TaskControllerTest {

    @Autowired
    private MockMvc request; // representa a requisição mockada

// Não precisa mais mockar (annotation SpringBootTest)
//    @MockBean
//    private TaskService service;
//
//    @MockBean
//    private UserRepo userRepo;
//
//    @MockBean
//    private TaskRepo taskRepo;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testaPaginaCreateTask() throws Exception { // testando a requisição get

        ResultActions r = request.perform(MockMvcRequestBuilders.get("/task"));

        r.andExpect(MockMvcResultMatchers.status().is(302)).andDo(MockMvcResultHandlers.print());
        // .isOk() é status 200
        // .isFound() é 302
    }

    @Test
    void testarLogin() throws Exception { // testando a requisição POST. Passar os parâmetros

        ResultActions r = request.perform(
                MockMvcRequestBuilders.post("/login")
                        .param("username", "123") // pegar o nome dos campos pelo inspecionar elemento. É o name
                        .param("password", "123"));

        r.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("home")) // verifica a página retornada
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void listaTask() {
    }

    @Test
    void closeTask() {
    }

    @Test
    void saveTask() {
    }
}