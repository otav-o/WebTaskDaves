/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.controller.service;

import br.com.webtask.aula.domain.model.UserClient;
import br.com.webtask.aula.domain.repo.UserRepo;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class UserServiceTest {
    
    @MockBean
    UserRepo userRepo;
    
    @InjectMocks
    UserService userService;
    
    @BeforeEach
    public void init(){
       MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void deveBuscarUmUsuarioExistente(){
        //Cenário
        UserClient user = new UserClient(1l, "Teste", "11122233344", "teste@test", "1234", true, new ArrayList<>());
        
        Mockito.when(userRepo.getOne(1l)).thenReturn(user);
        
        //Execução
        
        UserClient usuarioBuscado= userService.buscarUsuario(1l);
        
        //Verificação
        assertEquals("Teste", usuarioBuscado.getName());
        
    }
    
    @Test
    public void naoDeveBuscarUmUsuarioInexistente(){
        //Cenário        
        Mockito.when(userRepo.getOne(2l)).thenReturn(null);
        
        //Execução
        
        UserClient usuarioBuscado= userService.buscarUsuario(1l);
        
        //Verificação
        assertNull(usuarioBuscado);
    }
    
    @Test
    public void deveSalvarUmCliente(){
        //Cenário
        UserClient user = new UserClient(1l, "Teste", "11122233344", "teste@test", "1234", true, new ArrayList<>());
        
        Mockito.when(userRepo.save(user)).thenReturn(user);
        
        
        //Execução
        UserClient usuarioSalvo = userService.salvar(user);
        
        //Verificação
        assertEquals("Teste", usuarioSalvo.getName());
    }
    
}
