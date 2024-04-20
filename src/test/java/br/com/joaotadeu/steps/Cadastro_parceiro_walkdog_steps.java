package br.com.joaotadeu.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Cadastro_parceiro_walkdog_steps {

    //Transforma a Variável e uma Variável global
    private final WebDriver navegador = new FirefoxDriver();

    @Dado("que estou na pagina principal do WalkDog")
    public void que_estou_na_pagina_principal_do_walk_dog() {

        //Configuração do navegador
        WebDriverManager.firefoxdriver().setup();

        //Abrindo navegador
        navegador.get("https://walkdog.vercel.app/");
    }
    @Quando("preencho os dados pessoais do parceiro")
    public void preencho_os_dados_pessoais_do_parceiro() {
        navegador.findElement(By.cssSelector(".content > main:nth-child(1) > a:nth-child(4) > span:nth-child(1)")).click();


    }
    @Quando("escolho atividade extra")
    public void escolho_atividade_extra() {

    }
    @Quando("faço upload do documento de verificação do parceiro")
    public void faço_upload_do_documento_de_verificação_do_parceiro() {

    }
    @Então("devo ver a mensagem de cadastro com sucesso")
    public void devo_ver_a_mensagem_de_cadastro_com_sucesso() {

    }
}
