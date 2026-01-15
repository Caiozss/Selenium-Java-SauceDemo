package br.com.automacao.utils;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils; // Precisa da dependência commons-io no pom.xml
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    // --- FERRAMENTA 1: Leitor de JSON ---
    public static <T> T getMassaDados(String arquivo, Class<T> classeModelo) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/" + arquivo));
            return gson.fromJson(reader, classeModelo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // --- FERRAMENTA 2: Criador de Screenshots ---
    public static void tirarPrint(WebDriver driver, String nomeDoTeste) {
        // 1. Converte o driver em uma "Câmera" (TakesScreenshot)
        File foto = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // 2. Gera um nome único com Data e Hora para não substituir fotos antigas
        // Exemplo de nome final: prints/Sucesso_Compra_2026-01-14_15-30-00.png
        String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String nomeArquivo = "prints/" + nomeDoTeste + "_" + dataHora + ".png";

        try {
            // 3. Salva o arquivo na pasta
            FileUtils.copyFile(foto, new File(nomeArquivo));
            System.out.println("📸 Print salvo com sucesso em: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("❌ Erro ao salvar o print: " + e.getMessage());
        }
    }
}