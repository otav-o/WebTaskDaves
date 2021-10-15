package br.com.webtask.aula.controller.service;

import br.com.webtask.aula.domain.model.Task;
import br.com.webtask.aula.domain.model.UserClient;
import br.com.webtask.aula.domain.repo.TaskRepo;
import br.com.webtask.aula.domain.repo.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class TaskServiceTest {

    @MockBean
    TaskRepo taskrepo; // este que é fake

    @MockBean
    UserRepo userRepo;

    @InjectMocks // em vez de usar @Autowired. Injeta taskrepo falso
    TaskService tasks;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testarTasksMinhaLista() throws Exception {
        ArrayList<Task> lista = new ArrayList<>();
        lista.add(new Task(1l, "abc", LocalDate.now(), LocalDate.now().plusDays(1), null));

        Mockito.when(taskrepo.findByUserIdOrderByPlannedDateDesc(1)).thenReturn(lista); // !!
        // Mockito ensina o método a retornar algo, por exemplo.


//        Mockito.when(tasks.minhaLista(1)).thenReturn(XXX);

        Mockito.when(userRepo.getOne(1l)).thenReturn(
                new UserClient(1l, "", "", "", "", true, null));


        List<Task> lista1 = tasks.minhaLista(1);

        assertEquals(lista, lista1);

    }

    @Test
    public void testMetodoParaUsuarioNaoExistente() {
        ArrayList<Task> lista = new ArrayList<>();
        lista.add(new Task(1l, "abc", LocalDate.now(), LocalDate.now().plusDays(1), null));

        Mockito.when(userRepo.getOne(-1l)).thenReturn(null);

        try {
            List<Task> lista1 = tasks.minhaLista(-1);
            fail("Este usuário não existe, logo não há lista.");
        } catch (Exception e) {
            assertTrue(true); // parece gambiarra, não?
        }
    }
}