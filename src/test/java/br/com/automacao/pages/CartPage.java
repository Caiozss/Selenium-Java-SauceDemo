package br.com.automacao.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By itemTitle = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Validação: Retorna o texto do primeiro item da lista
    public String getNomeDoItem() {
        return getText(itemTitle);
    }

    public void irParaCheckout() {
        click(checkoutButton);
    }
}