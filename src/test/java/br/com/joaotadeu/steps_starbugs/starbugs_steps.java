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
    public void devoVerAPaginaDeCheckoutComOsDetalhesDoProduto() {
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

    @E("escolho a forma de pagamento:")
    public void escolhoAFormaDePagamento(DataTable dataTable) {
        List<Map<String, String>> formaPagamento = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> opcao : formaPagamento) {
            String metodoPagamento = opcao.get("Pagamento").toLowerCase();

            switch (metodoPagamento) {
                case "cartao de credito":
                    WebElement cartaoCredito = navegadorStarbugs.findElement(By.cssSelector("div.sc-fnGiBr:nth-child(1) > label:nth-child(2) > div:nth-child(1)"));
                    cartaoCredito.click();
                    break;
                case "cartao de debito":
                    WebElement cartaoDebito = navegadorStarbugs.findElement(By.cssSelector("div.sc-fnGiBr:nth-child(2) > label:nth-child(2) > div:nth-child(1)"));
                    cartaoDebito.click();
                    break;
                case "avista no pix":
                    WebElement pix = navegadorStarbugs.findElement(By.cssSelector("div.sc-fnGiBr:nth-child(3) > label:nth-child(2) > div:nth-child(1)"));
                    pix.click();
                    break;
                default:
                    System.out.println("Método de pagamento não reconhecido: " + metodoPagamento);
                    break;
            }
        }
    }

    @E("por fim finalizo a compra")
    public void porFimFinalizoACompra() {
        navegadorStarbugs.findElement(By.cssSelector(".sc-idXgbr")).click();
    }

    @Então("sou redirecionado para a página de confirmação de Pedidos e visualizo a mensagem {string}")
    public void souRedirecionadoParaAPaginaDeConfirmacaoDePedidosEVisualizoAMensagem(String pedidoConfirmado) {

        WebElement mensagemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), '" + pedidoConfirmado + "')]")));

        // Obter o texto do elemento
        String mensagem = mensagemElement.getText();

        // Validar se o texto do elemento é igual ao texto esperado
        Assert.assertEquals(pedidoConfirmado, mensagem);

    }

    @E("deve ser informado o seguinte prazo de entrega: {string}")
    public void deveSerInformadoOSeguintePrazoDeEntrega(String prazoEntrega) {

        WebElement mensagemElement = navegadorStarbugs.findElement(By.xpath("//strong[contains(text(), '" + prazoEntrega +"' )]"));

        // Obter o texto do elemento
        String mensagem = mensagemElement.getText();

        // Validar se o texto do elemento é igual ao texto esperado
        Assert.assertEquals(prazoEntrega, mensagem);

    }

    @Então("devo ver um popup informando que o produto está indisponível com a mensagem {string}")
    public void devoVerUmPopupInformandoQueOProdutoEstaIndisponivelComAMensagem(String compraindisponivel) {
        WebElement mensagemElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#swal2-html-container")));

        // Obter o texto do elemento
        String mensagem = mensagemElement.getText();

        // Validar se o texto do elemento é igual ao texto esperado
        Assert.assertEquals(compraindisponivel, mensagem);

    }

}