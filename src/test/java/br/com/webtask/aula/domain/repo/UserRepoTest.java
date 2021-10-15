package br.com.webtask.aula.domain.repo;

import br.com.webtask.aula.domain.model.UserClient;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // indica que vai usar banco de dados
@ActiveProfiles("test") // annotation com o mesmo nome do final do application-***.properties. Assim, vai usar o banco de teste
public class UserRepoTest {
    public UserRepoTest() {}

    @Autowired
    private UserRepo userRepo;

    private UserClient user1 = new UserClient(null, "Gustin", "321", "gu@gu", "123", true, null);
    private UserClient user2 = new UserClient(null, "Pedrin", "555", "ped@ped", "123", true, null);


    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    @Transactional // método que acessa banco de dados
    public void setUp(){
        userRepo.save(user1); // inserir esse usuário no banco de dados antes de cada teste
        userRepo.save(user2);
    }

    @AfterEach
    public void tearDown() {
        userRepo.deleteAll();
    }

    @Test
    public void testSomeMethod() {
        UserClient userEsperado = user1;
//        Mockito.when(userRepo.findByName("Gustin").get()).thenReturn(user1);
        UserClient userObtido = userRepo.findByName("Gustin").get();
        assertEquals(userEsperado, userObtido);
    }

    @Test
    @Transactional
    public void testaSaveNovoUser() {
        UserClient user2 = new UserClient(null, "Gustin123", "333", "gu123@gu", "123", true, null);
        userRepo.save(user2);

        UserClient userObtido = userRepo.findByName("Gustin123").get();

        assertEquals(user2, userObtido);
    }

    @Test
    public void testBuscarTodos() {
        long userEsperado = 2;
        long userObtido = userRepo.count();
        assertEquals(userEsperado, userObtido);
    }
}