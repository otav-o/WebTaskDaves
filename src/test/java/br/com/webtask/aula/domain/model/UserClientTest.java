/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author daves
 */
public class UserClientTest {
    
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("Iniciou os Testes");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("Fim dos Testes");
    }
    
    @BeforeEach
    public void setUp() {
        System.out.println("vai começar um teste");
    }
    
    @AfterEach
    public void tearDown() {
        System.out.println("terminou um teste");
        
    }
        
   //Mockito
   //Banco preparado     
    
    @Test
    public void testEmailValido() {
        System.out.println("teste1");
        
        //Cenário
        UserClient cli = new UserClient();
        cli.setEmail("daves@daves.com");
        boolean esperado = true;
        
        //Execução
        boolean resultado = cli.isEmailValid();
        
        
        //verificação
        assertEquals(esperado, resultado);
        
    }
    
    
    @Test
    public void testEmailSoComArroba() {
        System.out.println("teste2");
        
        //Cenário
        UserClient cli = new UserClient();
        cli.setEmail("@");
        boolean esperado = false;
        
        //Execução
        boolean resultado = cli.isEmailValid();
        
        
        //verificação
        assertEquals(esperado, resultado);
        
    }
    
    
    @Test
    public void testEmailComArrobaNoFim() {   
        System.out.println("teste3");     
        //Cenário
        UserClient cli = new UserClient();
        cli.setEmail("daves@");
        boolean esperado = false;
        
        //Execução
        boolean resultado = cli.isEmailValid();        
        
        //verificação
        assertEquals(esperado, resultado);        
    }
    
    
    @Test
    public void testEmailSemFinal() {     
        System.out.println("teste4");   
        //Cenário
        UserClient cli = new UserClient();
        cli.setEmail("daves@daves");
        boolean esperado = false;
        
        //Execução
        boolean resultado = cli.isEmailValid();        
        
        //verificação
        assertEquals(esperado, resultado);        
    }
    
}
