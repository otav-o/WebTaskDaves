/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webtask.aula.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;


/**
 *
 * @author thiago
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TestaSiteTest {
    
//@LocalServerPort
//    private int porta;
//    
//    private WebDriver driver;
//    
//    @BeforeEach
//    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "chromedriver85.exe");
//        driver = new ChromeDriver();
//    }
//    
//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }
//    
//    public void logar(){
//        //Setando valores pra inserção
//        driver.findElement(By.id("username")).sendKeys("123");
//        driver.findElement(By.id("password")).sendKeys("123");
//        driver.findElement(By.className("login100-form-btn")).click();
//    }
//    
////    @Test
////    public void testaPesquisagoogle(){
////        
////        driver.get("https://google.com");
////        
////        Assertions.assertEquals("Google",driver.getTitle());
////        
////        
////    }
//    
//    @Test
//    public void testeDeTelaNovaTarefaCompleta() throws InterruptedException{
//        
//        driver.get("http://localhost:"+porta);
//        
//        logar(); 
//        //Entrando no menu
//        driver.findElement(By.xpath("//*[@id=\"menu\"]/ul[3]/li[4]")).click();
//        
//        //Setando valores pra inserção
//        driver.findElement(By.id("cpNome")).sendKeys("teste");
//        driver.findElement(By.id("cpData")).sendKeys("03/10/2020");
//        driver.findElement(By.cssSelector(".btn-primary")).click();
//        
//        Thread.sleep(1000l);
//        //Verificação
//        Assertions.assertEquals("Dados alterados com sucesso.", driver.findElement(By.cssSelector("strong")).getText());
//        
//        
//    }
//    
//    @Test
//    public void testaAoCriarTarefaSeAMesmaApareceNaLista(){
//        driver.get("http://localhost:"+porta);
//        
//        logar(); 
//        //Entrando no menu
//        driver.findElement(By.xpath("//*[@id=\"menu\"]/ul[3]/li[4]")).click();
//        
//        //Setando valores pra inserção
//        driver.findElement(By.id("cpNome")).sendKeys("teste");
//        driver.findElement(By.id("cpData")).sendKeys("03/10/2020");
//        driver.findElement(By.cssSelector(".btn-primary")).click();
//        
//        //Entrando no menu de lista
//         driver.findElement(By.xpath("//*[@id=\"menu\"]/ul[3]/li[5]")).click();
//        //Setando valores pra inserção
//        
//        Assertions.assertEquals("teste", driver.findElement(By.xpath("//*[@id=\"listagem\"]/div/table/tbody/tr[1]/td[1]")).getText());
//        
//    }
//    
//    @Test
//    public void testDeLoginLogout(){
//        driver.get("http://localhost:"+porta);
//        
//        logar();
//        
//        //Saindo do sistema
//        driver.findElement(By.xpath("//*[@id=\"menu\"]/ul[3]/li[6]")).click();
//        
//        try {
//            //Tentando entrar com a nova senha
//            driver.findElement(By.id("username")).sendKeys("123");
//            driver.findElement(By.id("password")).sendKeys("123");
//            driver.findElement(By.className("login100-form-btn")).click();
//            //Localizar algum elemento da tela do sistema
//            driver.findElement(By.xpath("//*[@id=\"menu\"]/ul[3]/li[2]")).click();
//            Assertions.assertTrue(true);
//        } catch (Exception e) {
//            fail();
//        }
//        
//    }
//    
//    @Test
//    public void testeDeMudancaDeEmail() throws InterruptedException{
//        driver.get("http://localhost:"+porta);
//        
//        logar(); 
//        //Entrando no menu
//        driver.findElement(By.xpath("//*[@id=\"menu\"]/ul[3]/li[2]")).click();
//        
//        //Mudando o email
//        
//        driver.findElement(By.id("cpEmail")).clear();
//        driver.findElement(By.id("cpEmail")).sendKeys("adm@admin");
//        driver.findElement(By.id("cpCpf")).sendKeys("123");
//        Thread.sleep(1000l);
//        driver.findElement(By.cssSelector(".btn-primary")).click();
//        
//        
//        //Entrando no menu
//        driver.findElement(By.xpath("//*[@id=\"menu\"]/ul[3]/li[3]")).click();
//        driver.findElement(By.xpath("//*[@id=\"menu\"]/ul[3]/li[2]")).click();
//        
//        Thread.sleep(1000l);
//        String email = driver.findElement(By.id("cpEmail")).getAttribute("value");
//        System.out.println("Email: " + email);
//        
//        Assertions.assertEquals("adm@admin", email);
//    }
    
    
    
}
