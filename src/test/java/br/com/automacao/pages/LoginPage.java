package br.com.automacao.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// "extends BasePage" = Herança. LoginPage ganha os poderes da BasePage.
public class LoginPage extends BasePage {

    // 1. Mapeamento dos Elementos (Locators)
    // Usamos 'By' para guardar o endereço. É mais organizado que String.
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    // 2. Construtor
    // Passa o driver recebido para a classe Pai (BasePage)
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // 3. Ações
    public void visit() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String user, String pass) {
        // Usa os métodos que criamos na BasePage
        type(usernameField, user);
        type(passwordField, pass);
        click(loginButton);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}