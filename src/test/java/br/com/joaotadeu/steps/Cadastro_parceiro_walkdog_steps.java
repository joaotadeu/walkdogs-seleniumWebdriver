package br.com.joaotadeu.steps;

import io.cucumber.java.After;

import io.cucumber.java.Before;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class Cadastro_parceiro_walkdog_steps {

    //Transforma a Variável e uma Variável global
    private WebDriver navegador;

    @Before
    public void setUp(){
        //Configuração do navegador
        navegador = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
        System.out.println("Iniciando Teste...");
    }

    @After
    public void fecharNavegador(){
        navegador.quit();

    }

    @Dado("que estou na pagina principal do WalkDog")
    public void que_estou_na_pagina_principal_do_walk_dog() {
        navegador.get("https://walkdog.vercel.app/");
        navegador.findElement(By.cssSelector(".content > main:nth-child(1) > a:nth-child(4) > span:nth-child(1)")).click();

    }

    @Quando("preencho os dados pessoais do parceiro")
    public void preencho_os_dados_pessoais_do_parceiro(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> dadosParceiro = dataTable.asMaps(String.class, String.class);

        WebElement nomeInput = navegador.findElement(By.name("name"));
        WebElement emailInput = navegador.findElement(By.name("email"));
        WebElement cpfInput = navegador.findElement(By.name("cpf"));
        WebElement cepInput = navegador.findElement(By.name("cep"));
        WebElement numeroInput = navegador.findElement(By.name("addressNumber"));
        WebElement complementoInput = navegador.findElement(By.name("addressDetails"));

        // Preenchendo os campos com os dados do parceiro
        nomeInput.sendKeys(dadosParceiro.get(0).get("Nome Completo"));
        emailInput.sendKeys(dadosParceiro.get(0).get("E-mail"));
        cpfInput.sendKeys(dadosParceiro.get(0).get("CPF"));
        cepInput.sendKeys(dadosParceiro.get(0).get("CEP"));
        navegador.findElement(By.cssSelector("input[type=\"button\"]")).click();
        numeroInput.sendKeys(dadosParceiro.get(0).get("Numero"));
        complementoInput.sendKeys(dadosParceiro.get(0).get("Complemento"));
    }

    @Quando("escolho atividade extra")
    public void escolho_atividade_extra(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> atividadesExtras = dataTable.asMaps(String.class, String.class);

        // Verificar se há atividades extras especificadas
        if (!atividadesExtras.isEmpty()) {
            String atividadeExtra = atividadesExtras.get(0).get("Atividade Extra");

            // Verificando o tipo de atividade e realizando a seleção correspondente
            switch (atividadeExtra.toLowerCase()) {
                case "cuidar":
                    // Lógica para selecionar a atividade "cuidar"
                    navegador.findElement(By.cssSelector(".walker-service > li:nth-child(1) > span:nth-child(2)")).click();
                    break;
                case "adestrar":
                    // Lógica para selecionar a atividade "adestrar"
                    navegador.findElement(By.cssSelector(".walker-service > li:nth-child(2) > span:nth-child(2)")).click();
                    break;
                default:
                    // Tratamento para tipos de atividade inválidos
                    System.out.println("Tipo de atividade extra inválido: " + atividadeExtra);
                    break;
            }
        } else {
            System.out.println("Nenhuma atividade extra especificada.");
        }
    }

    @Quando("faço upload do documento de verificação do parceiro")
    public void faço_upload_do_documento_de_verificação_do_parceiro(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> dadosDocumento = dataTable.asMaps(String.class, String.class);

        // Obter o caminho do arquivo da DataTable
        String caminhoDoArquivo = dadosDocumento.get(0).get("Caminho Documento");

        // Localizar o elemento de upload na página
        WebElement fileInput = navegador.findElement(By.cssSelector("input[type='file']"));

        // Enviar o caminho do arquivo para o elemento de upload
        fileInput.sendKeys(caminhoDoArquivo);

        navegador.findElement(By.cssSelector(".button-register")).click();

    }

    @Então("devo ver a mensagem {string}")
    public void devoVerAMensagem(String mensagemEsperada) {
        // Tempo limite de espera
        Duration timeout = Duration.ofSeconds(30);

        // Registrar o tempo de início
        Instant startTime = Instant.now();

        // Definir uma variável para indicar se o elemento foi encontrado
        boolean elementoEncontrado = false;

        // Enquanto o elemento não for encontrado e o tempo limite não for excedido
        while (!elementoEncontrado && Duration.between(startTime, Instant.now()).compareTo(timeout) < 0) {
            try {
                // Tentar localizar o elemento
                WebElement mensagemElement = navegador.findElement(By.cssSelector("#swal2-html-container"));

                // Se o elemento for encontrado, atualizar a variável e sair do loop
                if (mensagemElement.isDisplayed()) {
                    elementoEncontrado = true;
                    // Verificar se a mensagem exibida é igual à mensagem esperada
                    String mensagemExibida = mensagemElement.getText();
                    Assert.assertEquals(mensagemEsperada, mensagemExibida);
                }
            } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
                // Elemento não encontrado ou elemento alterado na página, continuar tentando
                System.out.println("Elemento não encontrado. Tentando novamente...");
            }
        }

        // Verificar se o elemento foi encontrado
        if (!elementoEncontrado) {
            System.out.println("Tempo limite de espera excedido. Elemento não encontrado.");
            // Tratar a falha, se necessário...
        }
    }
}
