package br.com.joaotadeu.steps;

import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Map;

public class Cadastro_parceiro_walkdog_steps {

    //Transforma a Variável e uma Variável global
    private static final WebDriver navegador = new FirefoxDriver();

    @Dado("que estou na pagina principal do WalkDog")
    public void que_estou_na_pagina_principal_do_walk_dog() {
        //Configuração do navegador
        WebDriverManager.firefoxdriver().setup();

        //Abrindo navegador
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

        for (Map<String, String> atividade : atividadesExtras) {
            // Obtendo o tipo de atividade extra (cuidar ou adestrar) da DataTable
            String tipoAtividade = atividade.get("Atividades Extras").toLowerCase();

            // Verificando o tipo de atividade e realizando a seleção correspondente
            switch (tipoAtividade) {
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
                    System.out.println("Tipo de atividade extra inválido: " + tipoAtividade);
                    break;
            }
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

        WebElement elementoMensagem = navegador.findElement(By.cssSelector("#swal2-html-container"));
        String textoAtual = elementoMensagem.getText();
        Assert.assertTrue("O texto exibido não corresponde ao esperado: " + mensagemEsperada, textoAtual.startsWith(mensagemEsperada));

    }

    @After
    public static void fecharNavegador(){
        navegador.quit();
    }

}
