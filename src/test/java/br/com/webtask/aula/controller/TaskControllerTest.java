package br.com.webtask.aula.controller;

import br.com.webtask.aula.config.security.user.UserLogado;
import br.com.webtask.aula.controller.service.TaskService;
import br.com.webtask.aula.domain.model.UserClient;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Set;

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

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private PasswordEncoder password;

    @BeforeEach
    void setUp() {
        UserClient uc1 = UserClient.builder()
                .ativo(true).cpf("124").email("admin@admi4n")
                .name("admin").senha(password.encode("123")).build();
        userRepo.save(uc1);
    }

    @AfterEach
    void tearDown() {
        taskRepo.deleteAll();
        userRepo.deleteAll();
    }

    @Test
    void testandoPaginaProtegidaSemLogar() throws Exception { // testando a requisição get

        ResultActions r = request.perform(MockMvcRequestBuilders.get("/task"));

        r.andExpect(MockMvcResultMatchers.status().is(302)).andDo(MockMvcResultHandlers.print());
        // .isOk() é status 200
        // .isFound() é 302
    }

    @Test
    void testarLogin() throws Exception { // testando a requisição POST. Passar os parâmetros

        ResultActions r = request.perform(
                MockMvcRequestBuilders.post("/login")
                        .param("username", "124") // pegar o nome dos campos pelo inspecionar elemento. É o name
                        .param("password", "123"));

//        r.andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("home")) // verifica a página retornada
//                .andDo(MockMvcResultHandlers.print());

        r.andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    @WithMockUser
    void testarPaginaNaoExiste() throws Exception {

        ResultActions r = request.perform(
                MockMvcRequestBuilders.get("/abc")) ;

        r.andExpect(MockMvcResultMatchers.status().is(404));
        // isNotFound
    }

    @Test
//    @WithMockUser // não funciona sempre, pois usuário mockado não possui tarefas.
    public void TestAcessarMinhasTarefas() throws Exception {

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        UserLogado ul = new UserLogado("123", "123", grantedAuthorities, 1, "zezin", "ze@ze");
        // passar um usuário logado

        ResultActions r = request.perform(
                MockMvcRequestBuilders.get("/task/list")
                        .with(SecurityMockMvcRequestPostProcessors.user(ul)));

        r.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
//    @WithMockUser
    public void TestarSaveTask() throws Exception {

        UserClient ucl = userRepo.findByCpf("124").get();;

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
        UserLogado ul = new UserLogado("123", "123", grantedAuthorities, ucl.getId(), "zezin", "ze@ze");
        // passar um usuário logado

        ResultActions r = request.perform(
                MockMvcRequestBuilders.post("/task")
                        .with(SecurityMockMvcRequestPostProcessors.user(ul))
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .param("taskDescription", "abc")
                        .param("plannedDate", "2021-09-28")); // preenche a tela em nível de api

        r.andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"))
                .andDo(MockMvcResultHandlers.print());
    }
}