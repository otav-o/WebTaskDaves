/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.domain.repo;

import br.com.webtask.aula.domain.model.Task;
import br.com.webtask.aula.domain.model.UserClient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author thiago
 */

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class TaskRepoTest {
    
    @Autowired
    UserRepo userDao;
    
    @Autowired
    TaskRepo taskDao;
    
    UserClient user1, user2, user3;
    Task task, task2;
    
    @BeforeEach
    public void init(){
        System.out.println("Inserindo");
        
        user1 = new UserClient(1l, "Teste", "11122233344" ,"user1@test", "1234", true, new ArrayList<>());
        user2 = new UserClient(1l, "Teste2", "22233344455" ,"user2@test", "1234", true, new ArrayList<>());
        user3 = new UserClient(1l, "Teste3", "33344455566" ,"user3@test", "1234", true, new ArrayList<>());
        
        task = new Task(1l, "Tarefa 1", LocalDate.now().plusDays(1), null, user1);
        task2 = new Task(2l, "Tarefa 2", LocalDate.now().plusDays(1), null, user1);
        ArrayList<Task> tarefas = new ArrayList<>();
        tarefas.add(task);
        tarefas.add(task2);
        user1.setTasks(tarefas);
        
        userDao.save(user1);
        taskDao.save(task);
        taskDao.save(task2);
    }
    
    @AfterEach
    public void clean(){
        System.out.println("apagando");
        userDao.deleteAll();
    }
    
    @Test
    public void deveAcharUmaTarefaPelaDescricao(){
        //Cenário       
        String descricaoEsperada = user1.getTasks().get(0).getTaskDescription();
        //Execução
        ArrayList<Task> tarefaProcurada = (ArrayList<Task>) taskDao.findByTaskDescription(descricaoEsperada);
        
        //Avaliação
        assertEquals(descricaoEsperada, tarefaProcurada.get(0).getTaskDescription());
    }
    
    @Test
    public void naoDeveEncontrarTarefaComDescricaoInexistente(){
        //Cenário       
        
        //Execução
        ArrayList<Task> tarefaProcurada = (ArrayList<Task>) taskDao.findByTaskDescription("Não tem essa tarefa");
        
        //Avaliação
        if (tarefaProcurada.size() > 0) {
            fail();
        }else{
            assertTrue(true);
        }
    }
    
}
