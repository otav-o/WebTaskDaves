/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.domain.repo;

import br.com.webtask.aula.domain.model.UserClient;
import java.util.ArrayList;
import java.util.Optional;
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
public class UserRepoTest {
    
    @Autowired
    UserRepo userRepo;
    
    UserClient user1, user2, user3;
    
    @BeforeEach
    public void init(){
        System.out.println("Inserindo");        
        user1 = new UserClient(1l, "Teste", "11122233344" ,"user1@test", "1234", true, new ArrayList<>());
        userRepo.save(user1);        
    }
    
    @AfterEach
    public void clean(){
        System.out.println("apagando");
        userRepo.deleteAll();
    }
    
    @Test
    public void testaFuncaoFindByName(){
        //Cenário
        
        //Execução
        String nomeEsperado = user1.getName();
        Optional<UserClient> usuarioBuscado = userRepo.findByName(nomeEsperado);
        
        if (usuarioBuscado.isPresent()) {
            assertEquals(nomeEsperado, usuarioBuscado.get().getName());
        }else{
            fail();
        }        
    }
    
    @Test
    public void testaFuncaoFindByCpf(){
        //Cenário
        
        //Execução
        String cpfEsperado = user1.getCpf();
        Optional<UserClient> usuarioBuscado = userRepo.findByCpf(cpfEsperado);
        
        //Teste
        if (usuarioBuscado.isPresent()) {
            assertEquals(cpfEsperado, usuarioBuscado.get().getCpf());
        }else{
            fail();
        }      
    }
   
}
