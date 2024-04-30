package br.com.joaotadeu.steps_starbugs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class starbugs_steps {

    private WebDriver navegadorStarbugs;
    private WebDriverWait wait;

    @Before
    public void setUpStarbugsStarbugs(){
        // Configuração do navegador
        WebDriverManager.firefoxdriver().setup();
        navegadorStarbugs = new FirefoxDriver();
        wait = new WebDriverWait(navegadorStarbugs, Duration.ofSeconds(10));
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
    public void desejoComprarOSeguinteProduto(DataTable dataTable) {
        List<Map<String, String>> produtos = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> produto : produtos) {
            String nomeProduto = produto.get("Nome");

            // Localizando o elemento pelo nome do produto
            WebElement produtoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(@class, 'coffee-name') and text()='" + nomeProduto + "']")));

            // Validando o preço
            String precoEsperado = produto.get("Preço");
            WebElement precoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(@class, 'hblMil') and text()='" + precoEsperado + "']")));
            String precoAtual = precoElement.getText();

            // Localizando o botão de compra para o mesmo produto
            WebElement botaoComprar = produtoElement.findElement(By.xpath("./following-sibling::div//button[contains(@class, 'buy-coffee')]"));

            // Clicando no botão de compra
            botaoComprar.click();
        }
    }

    @Então("devo ver a página de Checkout com os detalhes do produto")
    public void devoVerAPaginaDeCheckoutComOsDetalhesDoProduto() throws InterruptedException {
        WebElement checkoutPedido = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sc-hHTYSt")));
        assertTrue(checkoutPedido.isDisplayed());
    }

    @E("o valor total da compra deve ser de {string}")
    public void oValorTotalDaCompraDeveSerDe(String valorTotalEsperado) {
        // Removendo o símbolo de R$ e substituindo vírgulas por pontos para garantir que seja um número válido
        String valorTotalEsperadoLimpo = valorTotalEsperado.replace("R$ ", "").replace(",", ".");

        // Convertendo a string para um tipo numérico (long)
        long valorTotalEsperadoNum = (long) (Double.parseDouble(valorTotalEsperadoLimpo) * 100);

        // Obtendo o elemento que contém o valor total da compra na tela
        WebElement valorTotalElement = navegadorStarbugs.findElement(By.cssSelector("p.total-price"));

        // Obtendo o texto do valor total da compra na tela e convertendo para um tipo numérico (long)
        String valorTotalTexto = valorTotalElement.getText().replace("R$ ", "").replace(",", ".");
        long valorTotalTela = (long) (Double.parseDouble(valorTotalTexto) * 100);

        // Validando se o valor total da compra na tela corresponde ao valor esperado
        Assert.assertEquals(valorTotalEsperadoNum, valorTotalTela);
    }

    @Quando("faço a busca do seguinte CEP: {string}")
    public void facoABuscaDoSeguinteCEP(String Cep) {
        WebElement campoBuscaCEP = navegadorStarbugs.findElement(By.cssSelector("input[name=cep]"));
        campoBuscaCEP.sendKeys(Cep);
        navegadorStarbugs.findElement(By.cssSelector("input[value='Buscar CEP']")).click();
    }

    @E("informo os demais dados do endereço:")
    public void informoOsDemaisDadosDoEndereco(DataTable dataTable) {
        List<Map<String, String>> dadosEndereco = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> dados : dadosEndereco) {
            String numero = dados.get("Numero");
            String detalhes = dados.get("Detalhes");

            WebElement campoNumero = navegadorStarbugs.findElement(By.cssSelector("input[name=number]"));
            campoNumero.sendKeys(numero);

            WebElement campoComplemento = navegadorStarbugs.findElement(By.cssSelector("input[name=complement]"));
            campoComplemento.sendKeys(detalhes);
        }

    }

    @E("escolho a forma de pagamento: {string}")
    public void escolhoAFormaDePagamento(String arg0) {
    }

    @E("por fim finalizo a compra")
    public void porFimFinalizoACompra() {
    }

    @Então("sou redirecionado para a página de confirmação de Pedidos e visualizo a mensagem {string}")
    public void souRedirecionadoParaAPaginaDeConfirmacaoDePedidosEVisualizoAMensagem(String arg0) {
    }

    @E("deve ser informado o seguinte prazo de entrega: {string}")
    public void deveSerInformadoOSeguintePrazoDeEntrega(String arg0) {
    }

    @Dado("que estou na página principal da Starbugs")
    public void queEstouNaPaginaPrincipalDaStarbugs() {
    }

    @E("que desejo comprar o seguinte produto:")
    public void queDesejoComprarOSeguinteProduto() {
    }

    @Quando("inicio a compra desse item")
    public void inicioACompraDesseItem() {
    }

    @Então("devo ver um popup informando que o produto está indisponível com a mensagem {string}")
    public void devoVerUmPopupInformandoQueOProdutoEstaIndisponivelComAMensagem(String arg0) {
    }
}