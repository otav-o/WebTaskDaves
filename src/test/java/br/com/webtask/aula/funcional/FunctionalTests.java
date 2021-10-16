package br.com.webtask.aula.funcional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
class FunctionalTests {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        driver = new ChromeDriver();

        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // demorar x segundos entre cada passo

        driver.get("http://localhost:8090/login");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    public void testeLoginFalha() {
        driver.manage().window().setSize(new Dimension(1234, 1087));
        driver.findElement(By.id("username")).sendKeys("abc");
        driver.findElement(By.id("password")).sendKeys("abc");
        driver.findElement(By.cssSelector(".login100-form-btn")).click();
        driver.findElement(By.cssSelector(".flex-sb-m")).click();
        assertThat(driver.findElement(By.cssSelector(".error")).getText(), is("Login ou Senha incorreta"));
    }

    @Test
    public void testeLoginSucesso() {
        driver.manage().window().setSize(new Dimension(1234, 1087));
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("123");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.cssSelector(".login100-form-btn")).click();
        driver.findElement(By.cssSelector(".container:nth-child(2)")).click();
        assertThat(driver.getTitle(), is("Web Task"));
    }

    @Test
    public void testeCadastrarTarefa() {
        driver.manage().window().setSize(new Dimension(1234, 1080));
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("123");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.cssSelector(".login100-form-btn")).click();
        driver.findElement(By.cssSelector(".nav-item:nth-child(4) span")).click();
        driver.findElement(By.id("cpNome")).click();
        driver.findElement(By.id("cpNome")).sendKeys("Dormir");
        driver.findElement(By.id("cpData")).click();
        driver.findElement(By.id("cpData")).click();
        driver.findElement(By.id("cpData")).sendKeys("2021-10-30");
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector(".alert")).click();
        assertThat(driver.findElement(By.cssSelector("strong")).getText(), is("Dados alterados com sucesso."));
    }
}