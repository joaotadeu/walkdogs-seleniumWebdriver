package br.com.joaotadeu.steps.steps_walkdogs;

import br.com.joaotadeu.DriverFactory.DriverFactory;
import br.com.joaotadeu.pages.walkdogsPages.CadastroParceiroPage;
import br.com.joaotadeu.pages.walkdogsPages.WalkdogsHomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import java.io.File;
import java.util.List;
import java.util.Map;

public class walkdog_steps {

    // Transforma a Variável em uma Variável global
    private WebDriver navegadorWalkdogs;
    private WalkdogsHomePage homePage;
    private CadastroParceiroPage cadastroPage;

    @Before
    public void setUpWalkdogs() {
        navegadorWalkdogs = DriverFactory.restartDriver();
        homePage = new WalkdogsHomePage(navegadorWalkdogs);
        cadastroPage = new CadastroParceiroPage(navegadorWalkdogs);
        System.out.println("Iniciando Teste...");
    }

    @After(order = 0)
    public void tirarPrintWalkdogs(Scenario cenario) {
        if (navegadorWalkdogs != null) { // Verifica se o navegador não é nulo
            try {
                File file = ((TakesScreenshot) navegadorWalkdogs).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(file, new File("evidencias/screenshot/" + cenario.getId() + ".jpg"));
            } catch (Exception e) {
                System.out.println("Erro ao capturar o screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("Navegador não inicializado. Não foi possível capturar o screenshot.");
        }
    }

    @After(order = 1)
    public void fecharNavegadorWalkdogs() {
        if (navegadorWalkdogs != null) {
            System.out.println("Finalizando Teste...");
            navegadorWalkdogs.quit();
            navegadorWalkdogs = null;
        }
    }

    @Dado("que estou na pagina principal do WalkDog")
    public void que_estou_na_pagina_principal_do_walk_dog() {
        homePage.abrirPaginaPrincipal();
    }

    @Quando("preencho os dados pessoais do parceiro")
    public void preencho_os_dados_pessoais_do_parceiro(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> dadosParceiro = dataTable.asMaps(String.class, String.class);
        Map<String, String> dados = dadosParceiro.get(0);
        cadastroPage.preencherDadosPessoais(dados.get("Nome Completo"), dados.get("E-mail"), dados.get("CPF"), dados.get("CEP"), dados.get("Numero"), dados.get("Complemento"));
    }

    @Quando("escolho atividade extra")
    public void escolho_atividade_extra(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> atividadesExtras = dataTable.asMaps(String.class, String.class);

        String atividade = atividadesExtras.get(0).get("Atividade Extra");
        cadastroPage.escolherAtividadeExtra(atividade);
    }

    @Quando("faço upload do documento de verificação do parceiro")
    public void faço_upload_do_documento_de_verificação_do_parceiro(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> dadosDocumento = dataTable.asMaps(String.class, String.class);
        String caminhoDocumento = dadosDocumento.get(0).get("Caminho Documento");
        cadastroPage.fazerUploadDocumento(caminhoDocumento);
    }

    @Então("devo ver a mensagem {string}")
    public void devoVerAMensagem(String mensagemEsperada) {
        String mensagemExibida = cadastroPage.obterMensagem();
        Assert.assertEquals(mensagemEsperada, mensagemExibida);
    }

    @Quando("tento cadastrar um parceiro sem preencher os campos obrigatórios")
    public void tentoCadastrarUmParceiroSemPreencherOsCamposObrigatorios() {
        cadastroPage.tentativaCadastro();
    }

    @Então("devo ver uma mensagem de erro informando que os campos obrigatórios não foram preenchidos")
    public void devo_ver_uma_mensagem_de_erro_informando_que_os_campos_obrigatórios_não_foram_preenchidos(DataTable dataTable) {
        List<Map<String, String>> dados = dataTable.asMaps(String.class, String.class);
        cadastroPage.validarMensagensDeErro(dados.get(0));
    }
}
