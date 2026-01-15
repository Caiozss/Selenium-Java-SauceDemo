package br.com.automacao.tests;

import br.com.automacao.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// extends BaseTest: Herda o setUp (abrir browser) e tearDown (fechar)
public class LoginTest extends BaseTest {

    @Test
    public void testLoginComSucesso() {
        // 1. Instanciar a página passando o driver que o BaseTest criou
        LoginPage loginPage = new LoginPage(driver);

        // 2. Ação
        loginPage.visit();
        loginPage.login("standard_user", "secret_sauce");

        // 3. Validação (Assertion)
        // No Java usamos Assertions do JUnit
        String urlAtual = driver.getCurrentUrl();
        Assertions.assertTrue(urlAtual.contains("inventory.html"), "A URL deveria conter inventory.html");
    }

    @Test
    public void testLoginBloqueado() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.visit();
        loginPage.login("locked_out_user", "secret_sauce");

        String mensagemErro = loginPage.getErrorMessage();
        Assertions.assertTrue(mensagemErro.contains("locked out"), "A mensagem de erro está incorreta!");
    }
}