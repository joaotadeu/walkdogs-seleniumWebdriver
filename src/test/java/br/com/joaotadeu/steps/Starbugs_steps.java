package br.com.joaotadeu.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Starbugs_steps {

    private WebDriver navegador;

    @Before
    public void setUp(){
        //Configuração do navegador
        navegador = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
        navegador.manage().window().maximize();
        System.out.println("Iniciando Teste...");

    }

    @After(order = 1)
    public void tirarPrint(Scenario cenario) throws IOException {
        File file =  ((TakesScreenshot)navegador).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("evidencias/screenshot/"+cenario.getId()+".jpg"));

    }

    @After(order = 0)
    public void fecharNavegador(){
        navegador.quit();
        System.out.println("Finalizando Teste...");

    }
    @Dado("que estou na pagina principal da Starbugs")
    public void que_estou_na_pagina_principal_da_starbugs() {
        navegador.get("https://starbugs.vercel.app/");
    }

    @Então("eu devo visualizar uma lista de cafes disponíveis")
    public void eu_devo_visualizar_uma_lista_de_cafes_disponíveis() {
        WebElement listaDeCafes = navegador.findElement(By.cssSelector(".sc-hhOBVt"));
        assertTrue(listaDeCafes.isDisplayed());
    }
}
