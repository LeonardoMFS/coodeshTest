package modulo.cadastro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

@DisplayName("Testes de Cadastro")
public class cadastroUsuario {

    @Test
    @DisplayName("Teste de Criação de Conta de Candidato")
    public void testCriaçãoDeContaCandidato(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://beta.coodesh.com/");
        String developerText = driver.findElement(By.xpath("//div//span[contains (text(), 'Sou developer')]")).getText();
        Assertions.assertEquals("Sou developer", developerText);
        String empresaText = driver.findElement(By.xpath("//div//span[contains (text(), 'Sou empresa')]")).getText();
        Assertions.assertEquals("Sou empresa", empresaText);

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        loginButton.click();

        WebElement criarContaButton = driver.findElement(By.xpath("//a[@href='/auth/candidates/signup']"));
        criarContaButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("displayName")));

        WebElement nomeField = driver.findElement(By.id("displayName"));
        nomeField.sendKeys("Leonardo Moreira");

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("kejex40994@videour.com");

        WebElement senhaField = driver.findElement(By.id("password"));
        senhaField.sendKeys("Pwd1234#");

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,300)");

        WebElement checkBox = driver.findElement(By.xpath("//*[contains(text(),'Li e aceito os')]"));
        Actions action = new Actions(driver);
        action.moveToElement(checkBox).click().perform();

       WebElement inscrevaButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
       inscrevaButton.click();

        driver.close();
        driver.quit();
    }

}
