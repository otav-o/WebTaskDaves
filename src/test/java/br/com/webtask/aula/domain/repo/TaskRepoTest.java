/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.domain.repo;

import br.com.webtask.aula.domain.model.Task;
import br.com.webtask.aula.domain.model.UserClient;
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
    
    
    UserClient user1, user2, user3;
    
    @BeforeEach
    public void init(){
        System.out.println("Inserindo");
        
        user1 = new UserClient(1l, "Teste", "11122233344" ,"user1@test", "1234", true, new ArrayList<>());
        user2 = new UserClient(1l, "Teste2", "22233344455" ,"user2@test", "1234", true, new ArrayList<>());
        user3 = new UserClient(1l, "Teste3", "33344455566" ,"user3@test", "1234", true, new ArrayList<>());
        userDao.save(user1);
        userDao.save(user2);
        userDao.save(user3);
        
    }
    
    @AfterEach
    public void clean(){
        System.out.println("apagando");
        userDao.deleteAll();
    }
    
}
