package br.com.automacao.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Relatorio {

    private static ExtentReports extent;
    private static ExtentTest test;

    // Inicia o relatório (cria o arquivo HTML vazio)
    public static void iniciarRelatorio() {
        if (extent == null) {
            // Define onde salvar: pasta 'relatorios' na raiz
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("relatorios/dashboard.html");

            // Configurações visuais (Tema escuro, Título)
            htmlReporter.config().setTheme(Theme.DARK);
            htmlReporter.config().setDocumentTitle("Relatório de Automação");
            htmlReporter.config().setReportName("Testes de Regressão - SauceDemo");

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
    }

    // Cria um novo teste no relatório (Ex: "Login com Sucesso")
    public static void criarTeste(String nomeTeste) {
        test = extent.createTest(nomeTeste);
    }

    // Registra que passou
    public static void logPassou(String mensagem) {
        test.pass(mensagem);
    }

    // Registra que falhou e anexa o print
    public static void logFalhou(String mensagem) {
        test.fail(mensagem);
    }

    // Finaliza e salva o arquivo
    public static void fecharRelatorio() {
        if (extent != null) {
            extent.flush();
        }
    }
}