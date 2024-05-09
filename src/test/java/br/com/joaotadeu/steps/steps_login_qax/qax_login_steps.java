package br.com.joaotadeu.steps.steps_login_qax;

import br.com.joaotadeu.DriverFactory.DriverFactory;
import br.com.joaotadeu.pages.qaxLoginPages;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class qax_login_steps {

    private WebDriver navegadorQAX;
    private qaxLoginPages paginaQAX;

    @Before
    public void setUpQAX(){
        navegadorQAX = DriverFactory.getDriver();
        paginaQAX = new qaxLoginPages(navegadorQAX);
        System.out.println("Iniciando Teste...");
    }

    @After
    public void fecharNavegadorStarbugs(){
        DriverFactory.killDriver();
        System.out.println("Finalizando Teste...");
    }

    @Dado("que estou na página de login no portal QAX")
    public void que_estou_na_página_de_login_no_portal_qax() {
        paginaQAX.acessarPaginaQAX();

        WebElement QAXHomepage = navegadorQAX.findElement(By.cssSelector(".App-header"));
        assertTrue(QAXHomepage.isDisplayed());
    }

    @Quando("preencho as credencias do usuario:")
    public void preencho_as_credencias_do_usuario(DataTable dataTable) {
        List<Map<String, String>> usuarios = dataTable.asMaps(String.class, String.class);

        String usuario = usuarios.get(0).get("usuario");
        String senha = usuarios.get(0).get("senha");

        paginaQAX.preencherUsuario(usuario);
        paginaQAX.preencherSenha(senha);
        paginaQAX.clicarBotaoLogin();
    }


    @Então("valido que a resposta do sistema está de acordo com a validação verificada")
    public void valido_que_a_resposta_do_sistema_está_de_acordo_com_a_validação_verificada(DataTable dataTable) {
        List<Map<String, String>> mensagemEsperadaLis = dataTable.asMaps(String.class, String.class);

        WebDriverWait wait = new WebDriverWait(navegadorQAX, Duration.ofSeconds(10)); // Defina a duração explicitamente

        paginaQAX.aguardarToast();

        String textoExibido = paginaQAX.obterTextoToast();

        for (Map<String, String> row : mensagemEsperadaLis) {
            String mensagemEsperada = row.get("mensagem_esperada");
            assertEquals(mensagemEsperada, textoExibido);
        }

    }

    @Quando("Preencho as credencias do usuario {string} e senha {string}")
    public void preenchoAsCredenciasDoUsuarioESenha(String usuario, String senha) {
        paginaQAX.preencherUsuarios(usuario);
        paginaQAX.preencherSenhas(senha);
        paginaQAX.clicarBotaoLogin();
    }


    @Então("valido que o usuario foi logado com sucesso")
    public void validoQueOUsuarioFoiLogadoComSucesso() {
        WebElement mensagemBoasVindas = navegadorQAX.findElement(By.cssSelector(".swal2-popup"));
        Assert.assertTrue(mensagemBoasVindas.isDisplayed());
    }

}
