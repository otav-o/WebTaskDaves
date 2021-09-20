/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author daves
 */
public class TaskTest {
    
    public TaskTest() {
    }

    //BDD
    @Test
    public void testSomeMethod() {
        
        //preparo o ambiente
        int a = 1;
        int b = 2;
        
        //executo a ação
        int c = a+b;
                
        //verifico o resultado  c==3
        assertEquals(3, c);
    }
    
    @Test
    public void testSomeMethod2() {
        
        //preparo o ambiente
        int a = 1;
        int b = 2;
        
        //executo a ação
        int c = a*b;
                
        //verifico o resultado  c==3
        assertEquals(2, c);
    }
    
    @Test
    public void testParaVerificarTaskValid() {
        
        //preparo o ambiente
        Task t = new Task();
        t.setTaskDescription("abc abc");
        boolean esperado = false;
        
        //executo a ação
        boolean resultado = t.isDescriptionValid();
                
        //verifico o resultado  c==3
        assertEquals(esperado, resultado);
    }
    
    
    @Test
    @DisplayName("testa se a tarefa está valida com espaço em branco no início")
    public void testParaVerificarTaskValidEspecoNoInicio() {
        
        //preparo o ambiente
        Task t = new Task();
        t.setTaskDescription(" abcabc");
        boolean esperado = false;
        
        //executo a ação
        boolean resultado = t.isDescriptionValid();
                
        //verifico o resultado 
        assertEquals(esperado, resultado);
    }
    
    
    @Test
    @DisplayName("testa se a tarefa está valida contendo números")
    public void testParaVerificarTaskValidComNumeros() {
        
        //preparo o ambiente
        Task t = new Task();
        t.setTaskDescription("abc123");
        boolean esperado = false;
        
        //executo a ação
        boolean resultado = t.isDescriptionValid();
                
        //verifico o resultado 
        assertEquals(esperado, resultado);
    }
    
    @Test
    @DisplayName("testa se a tarefa está valida contendo números no início")
    public void testParaVerificarTaskValidComNumerosNoInicio() {
        
        //preparo o ambiente
        Task t = new Task();
        t.setTaskDescription("111abc");
        boolean esperado = false;
        
        //executo a ação
        boolean resultado = t.isDescriptionValid();
                
        //verifico o resultado 
        assertEquals(esperado, resultado);
    }
    
    @Test
    @DisplayName("testa se a tarefa está valida contendo somente números")
    public void testParaVerificarTaskValidSomenteNumeros() {
        
        //preparo o ambiente
        Task t = new Task();
        t.setTaskDescription("11");
        boolean esperado = false;
        
        //executo a ação
        boolean resultado = t.isDescriptionValid();
                
        //verifico o resultado 
        assertEquals(esperado, resultado);
    }
    
    @Test
    @DisplayName("testa se a tarefa está valida")
    public void testParaVerificarTaskCorreta() {
        
        //preparo o ambiente
        Task t = new Task();
        t.setTaskDescription("abc");
        boolean esperado = true;
        
        //executo a ação
        boolean resultado = t.isDescriptionValid();
                
        //verifico o resultado 
        assertEquals(esperado, resultado);
    }
    
    
    
}
