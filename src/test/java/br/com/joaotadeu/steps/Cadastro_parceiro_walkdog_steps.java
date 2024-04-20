package br.com.joaotadeu.steps;

import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Map;

public class Cadastro_parceiro_walkdog_steps {

    //Transforma a Variável e uma Variável global
    private final WebDriver navegador = new FirefoxDriver();

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

        WebElement nomeInput = navegador.findElement(By.name("name")); // Supondo que o ID do campo de nome seja "name"
        WebElement emailInput = navegador.findElement(By.name("email")); // Supondo que o ID do campo de email seja "email"
        WebElement cpfInput = navegador.findElement(By.name("cpf")); // Supondo que o ID do campos de CPF seja "CPF"
        WebElement cepInput = navegador.findElement(By.name("cep"));
        WebElement numeroInput = navegador.findElement(By.name("addressNumber"));
        WebElement complementoInput = navegador.findElement(By.name("addressDetails"));

        // Preenchendo os campos com os dados do parceiro
        nomeInput.sendKeys(dadosParceiro.get(0).get("Nome Completo"));
        emailInput.sendKeys(dadosParceiro.get(0).get("E-mail"));
        cpfInput.sendKeys(dadosParceiro.get(0).get("CPF"));
        cepInput.sendKeys(dadosParceiro.get(0).get("CEP"));
        navegador.findElement(By.cssSelector("#page-walker > form:nth-child(2) > fieldset:nth-child(4) > div:nth-child(2) > div:nth-child(2) > input:nth-child(1)")).click();
        numeroInput.sendKeys(dadosParceiro.get(0).get("Numero"));
        complementoInput.sendKeys(dadosParceiro.get(0).get("Complemento"));
    }
    @Quando("escolho atividade extra")
    public void escolho_atividade_extra(io.cucumber.datatable.DataTable dataTable) {


    }
    @Quando("faço upload do documento de verificação do parceiro")
    public void faço_upload_do_documento_de_verificação_do_parceiro(io.cucumber.datatable.DataTable dataTable) {

    }
    @Então("devo ver a mensagem de cadastro com sucesso")
    public void devo_ver_a_mensagem_de_cadastro_com_sucesso() {

    }

    @After
    public void Hooks(){
        navegador.quit();
    }
}
