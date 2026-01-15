package br.com.automacao.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // Construtor: Quando uma página nasce, ela EXIGE receber o driver
    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Configura uma espera explícita de 10 segundos para elementos desta página
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Método encapsulado para esperar e clicar
    // By locator = o "endereço" do elemento (id, css, xpath)
    protected void click(By locator) {
        // Espera o elemento ser clicável antes de agir
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    // Método encapsulado para escrever
    protected void type(By locator, String text) {
        // Espera estar visível
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    // Método para validar texto
    protected String getText(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }
}