package br.com.joaotadeu.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Starbugs_steps {

    private WebDriver navegadorStarbugs;

    @Before
    public void setUpStarbugsStarbugs(){
        // Configuração do navegador
        WebDriverManager.firefoxdriver().setup();
        navegadorStarbugs = new FirefoxDriver();
        System.out.println("Iniciando Teste...");
    }

    @After
    public void fecharNavegadorStarbugs(){
        navegadorStarbugs.quit();
        System.out.println("Finalizando Teste...");
    }

    @Dado("que estou na pagina principal da Starbugs")
    public void que_estou_na_pagina_principal_da_starbugs() {
        navegadorStarbugs.get("https://starbugs.vercel.app/");
    }

    @Então("eu devo visualizar uma lista de cafes disponíveis")
    public void eu_devo_visualizar_uma_lista_de_cafes_disponíveis() {
        WebElement listaDeCafes = navegadorStarbugs.findElement(By.cssSelector(".sc-hhOBVt"));
        assertTrue(listaDeCafes.isDisplayed());
    }

    @E("que desejo comprar o seguinte produto")
    public void queDesejoComprarOSeguinteProduto(DataTable dataTable) {
        System.out.println("Passei por aqui");
    }

    @Quando("inicio a compra desse item")
    public void inicioACompraDesseItem() {
        System.out.println("Passei por aqui");

    }

    @Então("devo ver a página de Checkout com os detalhes do produto")
    public void devoVerAPaginaDeCheckoutComOsDetalhesDoProduto() {
        System.out.println("Passei por aqui");
    }

    @E("o valor total da compra deve ser de {string}")
    public void oValorTotalDaCompraDeveSerDe(String arg0) {
        System.out.println("Passei por aqui");
    }
}
