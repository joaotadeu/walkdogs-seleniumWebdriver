package br.com.joaotadeu.steps.steps_login_qax;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
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

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class qax_login_steps {

    private WebDriver navegadorQAX;

    @Before
    public void setUpParodify(){
        // Configuração do navegador
        WebDriverManager.firefoxdriver().setup();
        navegadorQAX = new FirefoxDriver();
        System.out.println("Iniciando Teste...");
    }

    @After
    public void fecharNavegadorStarbugs(){
        navegadorQAX.quit();
        System.out.println("Finalizando Teste...");
    }

    @Dado("que estou na página de login no portal QAX")
    public void que_estou_na_página_de_login_no_portal_qax() {
        navegadorQAX.get("https://loginxp.vercel.app/");

        WebElement QAXHomepage = navegadorQAX.findElement(By.cssSelector(".App-header"));
        assertTrue(QAXHomepage.isDisplayed());
    }
    @Quando("preencho as credencias do usuario:")
    public void preencho_as_credencias_do_usuario(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> usuarios = dataTable.asMaps(String.class, String.class);

        WebElement usuario = navegadorQAX.findElement(By.cssSelector("input[name=user]"));
        WebElement senha = navegadorQAX.findElement((By.cssSelector("input[name=pass]")));

        usuario.sendKeys(usuarios.get(0).get("usuario"));
        senha.sendKeys(usuarios.get(0).get("senha"));

        navegadorQAX.findElement(By.cssSelector("button[type=button]")).click();

    }

    @Então("valido que a resposta do sistema está de acordo com a validação verificada")
    public void valido_que_a_resposta_do_sistema_está_de_acordo_com_a_validação_verificada(DataTable dataTable) {
        List<Map<String, String>> mensagemEsperadaLis = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : mensagemEsperadaLis) {
            String mensagemEsperada = row.get("mensagem_esperada");

            // Encontre o elemento usando o XPath específico
            WebElement elemento = navegadorQAX.findElement(By.cssSelector(".go4109123758"));

            String textoExibido = elemento.getText();
            assertEquals(mensagemEsperada, textoExibido);
        }
    }
    @Quando("Preencho as credencias do usuario {string} e senha {string}")
    public void preenchoAsCredenciasDoUsuarioESenha(String usuario, String senha) {

    }

    @Então("valido que o usuario foi logado com sucesso")
    public void validoQueOUsuarioFoiLogadoComSucesso() {
    }

}
