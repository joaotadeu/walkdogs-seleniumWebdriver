package br.com.joaotadeu.steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@DisplayName("Teste automatizado teste de ambiente")
public class TestAmbienteTest {

    @Test
    @DisplayName("Teste automatizado")
    public void testAmbiente (){
        //Configuração do ambiente
        WebDriverManager.firefoxdriver().setup();
        WebDriver navegador = new FirefoxDriver();

        //Abrindo navegador
        navegador.get("https://starbugs.vercel.app/");

        //Encerrar
        navegador.quit();
    }

}
