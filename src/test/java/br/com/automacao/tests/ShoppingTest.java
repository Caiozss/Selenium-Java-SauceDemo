package br.com.automacao.tests;

import br.com.automacao.pages.CartPage;
import br.com.automacao.pages.InventoryPage;
import br.com.automacao.pages.LoginPage;
import br.com.automacao.utils.TestData; // Ajustado para seu nome
import br.com.automacao.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import br.com.automacao.pages.CheckoutPage;

public class ShoppingTest extends BaseTest {

    @Test
    public void testFluxoCompletoDeCompra() {
        // Carrega dados
        TestData dados = Utils.getMassaDados("MassaDados.json", TestData.class);

        // Instancia páginas
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver); // Nova página!

        // --- PASSO 1: Login ---
        loginPage.visit();
        loginPage.login(dados.user, dados.pass);

        // --- PASSO 2: Adicionar ao Carrinho ---
        inventoryPage.adicionarMochilaAoCarrinho();
        inventoryPage.acessarCarrinho();

        // Validação intermediária
        Assertions.assertEquals(dados.productName, cartPage.getNomeDoItem());

        // --- PASSO 3: Ir para Checkout ---
        cartPage.irParaCheckout();

        // --- PASSO 4: Preencher Dados (Vindos do JSON) ---
        checkoutPage.preencherFormulario(dados.firstName, dados.lastName, dados.zipCode);

        // --- PASSO 5: Finalizar ---
        checkoutPage.finalizarCompra();

        // --- VALIDAÇÃO FINAL ---
        String msgSucesso = checkoutPage.getMensagemSucesso();
        Assertions.assertEquals("Thank you for your order!", msgSucesso);

        // --- EVIDÊNCIA ---
        // Tira uma foto da tela final de sucesso
        Utils.tirarPrint(driver, "Sucesso_Compra");
    }
}