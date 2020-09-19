/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.domain.model;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author thiago
 */
public class TaskTest {
    
    Task tarefa;
    UserClient user;
    
    @BeforeEach
    public void init(){
        tarefa = new Task();
        tarefa.setId(1l);
        tarefa.setTaskDescription("teste");
        
        user = new UserClient();
        tarefa.setUser(user);
    }
    
    @Test
    public void testeDeFlagDeTarefaFinalizadaQuandoEstaFinalizada(){
        
        //Cenario
        tarefa.setPlannedDate(LocalDate.now().minusDays(1l));
        tarefa.setFinishedDate(LocalDate.now());
        
        //Execução
        boolean resultado = tarefa.isFinish();
        
        //Resultado
        assertTrue(resultado);
    }
    
    @Test
    public void testeDeFlagDeTarefaFinalizadaQuandoNaoEstaFinalizada(){
        //Cenario
        tarefa.setPlannedDate(LocalDate.now().plusDays(1l));
        tarefa.setFinishedDate(null);
        
        //Execução
        boolean resultado = tarefa.isFinish();
        
        //Resultado
        assertFalse(resultado);
    }
    
    @Test
    public void testeDeFlagDeAtrasoQuandoTarefaEstaAtrasada(){
        //Cenario
        tarefa.setPlannedDate(LocalDate.now().minusDays(1l));
        tarefa.setFinishedDate(null);
        
        //Execucao
        boolean resultaddo = tarefa.isLate();
        
        //Resultado
        assertTrue(resultaddo);
    }
    
    @Test
    public void testeDeFlagDeAtrasoQuandoNaoTarefaEstaAtrasada(){
        //Cenario
        tarefa.setPlannedDate(LocalDate.now().minusDays(1l));
        tarefa.setFinishedDate(LocalDate.now());
        
        //Execucao
        boolean resultaddo = tarefa.isLate();
        
        //Resultado
        assertTrue(resultaddo);
    }
    
    @Test 
    public void testeDeStatusTarefaConcluidaComAtraso(){
        //Cenario
        tarefa.setPlannedDate(LocalDate.now().minusDays(1l));
        tarefa.setFinishedDate(LocalDate.now());
        
        //Execucao
        EStatus status = tarefa.getStatus();
        
        //Verificacao
        assertEquals(EStatus.CONCLUIDO_ATRASADO, status);
    }
    
    @Test
    public void testeDeStatusTarefaConcluidaNoPrazo(){
        //Cenario
        tarefa.setPlannedDate(LocalDate.now());
        tarefa.setFinishedDate(LocalDate.now().minusDays(1l));
        
        //Execução
        EStatus status = tarefa.getStatus();
        
        //Verificacao
        assertEquals(EStatus.CONCLUIDO_PRAZO, status);
    }
    
    @Test
    public void testeDeStatusTarefaNaoConcluidaEAtrasada(){
        //Cenario
        tarefa.setPlannedDate(LocalDate.now().minusDays(1));
        tarefa.setFinishedDate(null);
        
        //Execução
        EStatus status = tarefa.getStatus();
        
        //Verificacao
        assertEquals(EStatus.ATRASADO, status);
    }
    
    @Test
    public void testeDeStatusTareaNaoConcluidaENaoAtrasada(){
        //Cenario
        tarefa.setPlannedDate(LocalDate.now().plusDays(1));
        tarefa.setFinishedDate(null);
        
        //Execução
        EStatus status = tarefa.getStatus();
        
        //Verificacao
        assertEquals(EStatus.NOVO, status);
    }
}
