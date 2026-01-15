package br.com.automacao.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    // 1. Locators
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By zipField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By completeHeader = By.className("complete-header");

    // 2. Construtor
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // 3. Ações
    public void preencherFormulario(String first, String last, String zip) {
        type(firstNameField, first);
        type(lastNameField, last);
        type(zipField, zip);
        click(continueButton);
    }

    public void finalizarCompra() {
        click(finishButton);
    }

    public String getMensagemSucesso() {
        return getText(completeHeader);
    }
}