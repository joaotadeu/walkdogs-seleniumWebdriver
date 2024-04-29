package br.com.joaotadeu.teste;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@DisplayName("Teste automatizado teste de ambiente")
public class TestAmbienteTest {

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
