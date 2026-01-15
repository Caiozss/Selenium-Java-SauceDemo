package br.com.automacao.tests;

import br.com.automacao.utils.Relatorio; // Import da nossa classe de Relatório
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*; // Importa BeforeEach, AfterEach, TestInfo, etc.
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;

    // --- 1. CONFIGURAÇÃO DO RELATÓRIO (Roda 1x antes de tudo) ---
    @BeforeAll
    public static void setupRelatorio() {
        Relatorio.iniciarRelatorio();
    }

    // --- 2. CONFIGURAÇÃO DO TESTE (Roda antes de CADA teste) ---
    @BeforeEach
    public void setUp(TestInfo testInfo) {
        // Cria o teste no relatório com o nome da função (ex: "testFluxoCompletoDeCompra")
        Relatorio.criarTeste(testInfo.getDisplayName());

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // A. Argumentos Básicos
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled"); // Esconde que é robô

        // B. Preferências para bloquear popups de senha (O "Pulo do Gato")
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("safebrowsing.enabled", true);

        // Aplica as preferências
        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        // C. Modo Headless (Opcional - mude para true se quiser rodar escondido)
        boolean headless = false;
        if (headless) {
            options.addArguments("--headless=new");
        }

        // Inicia o driver
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    // --- 3. FINALIZAÇÃO DO TESTE (Roda depois de CADA teste) ---
    @AfterEach
    public void tearDown() {
        // Registra no relatório
        Relatorio.logPassou("Teste finalizado com sucesso (ou falha tratada).");

        if (driver != null) {
            driver.quit();
        }
    }

    // --- 4. SALVAR RELATÓRIO (Roda 1x no final de tudo) ---
    @AfterAll
    public static void finalizarRelatorio() {
        Relatorio.fecharRelatorio();
    }
}