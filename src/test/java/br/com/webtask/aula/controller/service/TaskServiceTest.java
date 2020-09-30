/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.controller.service;

import br.com.webtask.aula.domain.model.EStatus;
import br.com.webtask.aula.domain.model.Task;
import br.com.webtask.aula.domain.model.UserClient;
import br.com.webtask.aula.domain.repo.TaskRepo;
import br.com.webtask.aula.domain.repo.UserRepo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author thiago
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class TaskServiceTest {
    
    @MockBean
    TaskRepo taskRepo;
    
    @MockBean
    UserRepo userRepo;
    
    @InjectMocks
    TaskService taskService;
    
    @BeforeEach
    public void init(){
       MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void deveSalvarTarefaEmAberto(){
        //Cenário        
        UserClient user = new UserClient(1l, "Teste", "11122233344", "teste@test", "1234", true, new ArrayList<>());
        Task task = new Task(1l, "Teste tarefa aberta", LocalDate.now().plusDays(1), null, user);
        
        Mockito.when(taskRepo.save(task)).thenReturn(task);
        
        //Execução
        Task taskSalva = taskService.salvar(task); 
        
        //Verificação
        assertEquals(EStatus.NOVO, taskSalva.getStatus());
    }
    
    @Test
    public void deveSalvarTarefaVencida(){
        //Cenário        
        UserClient user = new UserClient(1l, "Teste", "11122233344", "teste@test", "1234", true, new ArrayList<>());
        Task task = new Task(1l, "Teste tarefa aberta", LocalDate.now().minusDays(1), LocalDate.now(), user);
        
        Mockito.when(taskRepo.save(task)).thenReturn(task);
        
        //Execução
        Task taskSalva = taskService.salvar(task); 
        
        //Verificação
        assertEquals(EStatus.CONCLUIDO_ATRASADO, taskSalva.getStatus());
    }
    
    @Test
    public void deveRetornarListaDeTarefas(){
        
        //Cenário        
        UserClient user = new UserClient(1l, "Cliente Teste", "11122233344", "teste@test", "1234", true, null);
        Task task = new Task(1l, "Tarefa 1", LocalDate.now().plusDays(1), null, user);
        Task task2 = new Task(2l, "Tarefa 2", LocalDate.now().plusDays(1), null, user);
        ArrayList<Task> tarefas = new ArrayList<>();
        tarefas.add(task);
        tarefas.add(task2);
        user.setTasks(tarefas);
        
        Mockito.when(taskRepo.findByUserIdOrderByPlannedDateDesc(1l)).thenReturn(tarefas);
        
        //Execução
        ArrayList<Task> tarefasBuscadas = (ArrayList<Task>) taskService.minhaLista(user.getId());
        
        //Verificação
        assertEquals(2, tarefasBuscadas.size());
        
    }
    
    @Test
    public void deveRetornarListaVazia(){
        //Cenário        
        UserClient user = new UserClient(1l, "Cliente Teste", "11122233344", "teste@test", "1234", true, new ArrayList<>());
        
        Mockito.when(taskRepo.findByUserIdOrderByPlannedDateDesc(1l)).thenReturn(new ArrayList<>());
        
        //Execução
        ArrayList<Task> tarefasBuscadas = (ArrayList<Task>) taskService.minhaLista(user.getId());
        
        //Verificação
        assertEquals(0, tarefasBuscadas.size());
    }
    
    @Test
    public void deveFinalizarTarefa(){
        //Cenario
        Task task = new Task(1l, "Tarefa 1", LocalDate.now().plusDays(1), LocalDate.now(), new UserClient());
        Mockito.when(taskRepo.save(task)).thenReturn(task);
        
        //Execução
        Task tarefaFinalizada = taskService.finalizar(task);
        
        
        //Verificação
        assertEquals(task, tarefaFinalizada);
    }
    
    @Test
    public void deveRetornartarefaPorId(){
        //Cenario
        Task task = new Task(1l, "Tarefa 1", LocalDate.now().plusDays(1), LocalDate.now(), new UserClient());
        Mockito.when(taskRepo.getOne(1l)).thenReturn(task);
        
        //Execução
        Task tarefaBuscada = taskService.getTask(1l);
        
        
        //Verificação
        assertEquals(task, tarefaBuscada);
    }
    
    
}
