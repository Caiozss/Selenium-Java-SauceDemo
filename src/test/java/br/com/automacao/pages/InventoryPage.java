package br.com.automacao.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    // 1. Mapeamento (Locators)
    // Usamos id e className aqui
    private By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartIcon = By.className("shopping_cart_link");

    // 2. Construtor
    // Repasse o driver para a BasePage
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // 3. Ações
    public void adicionarMochilaAoCarrinho() {
        click(addBackpackButton);
    }

    public void acessarCarrinho() {
        click(cartIcon);
    }
}