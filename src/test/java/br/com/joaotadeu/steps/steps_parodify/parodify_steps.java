package br.com.joaotadeu.steps.steps_parodify;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class parodify_steps {

    private WebDriver navegadorParodify;
    private WebDriverWait wait;

    @Before
    public void setUpParodify(){
        // Configuração do navegador
        WebDriverManager.firefoxdriver().setup();
        navegadorParodify = new FirefoxDriver();
        //wait = new WebDriverWait(navegadorParodify, Duration.ofSeconds(10));
        System.out.println("Iniciando Teste...");
    }

    @After
    public void fecharNavegadorStarbugs(){
        navegadorParodify.quit();
        System.out.println("Finalizando Teste...");
    }

    @Dado("que estou na página principal do Parodify")
    public void que_estou_na_página_principal_do_parodify() {
        navegadorParodify.get("https://parodify.vercel.app/");

        WebElement parodifyHomepage = navegadorParodify.findElement(By.id("root"));
        assertTrue(parodifyHomepage.isDisplayed());

    }

    @Quando("efetuo a busca referente as musicas apresentadas na plataforma")
    public void efetuo_a_busca_referente_as_musicas_apresentadas_na_plataforma(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> listaMusicas = dataTable.asMaps(String.class, String.class);

        WebElement tituloMusicas = navegadorParodify.findElement(By.id("search-input"));

        tituloMusicas.sendKeys(listaMusicas.get(0).get("Titulo das Musicas"));
        navegadorParodify.findElement(By.cssSelector("button[class='ml-4']")).click();
    }

    @Então("valido que a busca foi feita com sucesso")
    public void valido_que_a_busca_foi_feita_com_sucesso() {
        WebDriverWait wait = new WebDriverWait(navegadorParodify, Duration.ofSeconds(10));
        WebElement parodifyBusca = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cover")));
        assertTrue(parodifyBusca.isDisplayed());
    }


    @Então("vou interagir com o botão home")
    public void vouInteragirComOBotaoHome() {
        navegadorParodify.findElement(By.cssSelector(".bg-active")).click();
    }

    @E("validar que foi direcionado para página principal do Parodify")
    public void validarQueFoiDirecionadoParaPaginaPrincipalDoParodify() {
        WebDriverWait wait = new WebDriverWait(navegadorParodify, Duration.ofSeconds(10));
        WebElement parodifyBusca = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".px-8")));
        assertTrue(parodifyBusca.isDisplayed());
    }

    @Então("vou interagir com o botão play")
    public void vouInteragirComOBotaoPlay() {
        navegadorParodify.findElement(By.cssSelector("button.play:nth-child(3)")).click();
    }

    @E("vou interagir com o botão mute")
    public void vouInteragirComOBotaoMute() {
        navegadorParodify.findElement(By.cssSelector("div.min-w-\\[11\\.25rem\\]:nth-child(3) > button:nth-child(4)")).click();
    }

    @E("validar que a funcionalidade esta de acordo")
    public void validarQueAFuncionalidadeEstaDeAcordo() {
        WebElement parodifyHomepage = navegadorParodify.findElement(By.id("root"));
        assertTrue(parodifyHomepage.isDisplayed());
    }

    @Quando("verifico que as playlists estão visiveis")
    public void verificoQueAsPlaylistsEstaoVisiveis() {
        WebElement parodifyHomepage = navegadorParodify.findElement(By.cssSelector(".mx-6"));
        assertTrue(parodifyHomepage.isDisplayed());
    }

    @Quando("vou interagir com o botão playlist")
    public void vouInteragirComOBotaoPlaylist() {
        navegadorParodify.findElement(By.cssSelector("nav.mt-6 > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")).click();
    }

    @Então("valido interação com susceso")
    public void validoInteracaoComSusceso() {
        WebElement parodifyHomepage = navegadorParodify.findElement(By.cssSelector("nav.mt-6 > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
        assertTrue(parodifyHomepage.isDisplayed());
    }

    @Quando("vou interagir com o botão favoritos")
    public void vouInteragirComOBotaoFavoritos() {
        navegadorParodify.findElement(By.cssSelector(".bg-gradient-to-br")).click();

        WebElement parodifyHomepage = navegadorParodify.findElement(By.cssSelector("nav.mt-6 > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)"));
        assertTrue(parodifyHomepage.isDisplayed());
    }
}
