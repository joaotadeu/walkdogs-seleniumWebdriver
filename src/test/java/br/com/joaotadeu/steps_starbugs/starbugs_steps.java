package br.com.joaotadeu.steps_starbugs;

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

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class starbugs_steps {

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

    @Quando("desejo comprar o seguinte produto")
    public void desejoComprarOSeguinteProduto(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> produtos = dataTable.asMaps(String.class, String.class);
        Thread.sleep(3000);
        for (Map<String, String> produto : produtos) {
            String nomeProduto = produto.get("Nome");

            // Localizando o elemento pelo nome do produto
            WebElement produtoElement = navegadorStarbugs.findElement(By.xpath("//h1[contains(@class, 'coffee-name') and text()='" + nomeProduto + "']"));

            // Validando o preço
            String precoEsperado = produto.get("Preço");
            WebElement precoElement = navegadorStarbugs.findElement(By.xpath("//strong[contains(@class, 'hblMil') and text()='" + precoEsperado + "']"));
            String precoAtual = precoElement.getText();

            // Localizando o botão de compra para o mesmo produto
            WebElement botaoComprar = produtoElement.findElement(By.xpath("./following-sibling::div//button[contains(@class, 'buy-coffee')]"));

            // Clicando no botão de compra
            botaoComprar.click();

        }
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
