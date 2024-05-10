package br.com.joaotadeu.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class walkdogsPages {
    public static class WalkdogsHomePage {
        private final WebDriver driver;

        public WalkdogsHomePage(WebDriver driver) {
            this.driver = driver;
        }

        public void abrirPaginaPrincipal() {
            driver.get("https://walkdog.vercel.app/");
            driver.findElement(By.cssSelector(".content > main:nth-child(1) > a:nth-child(4) > span:nth-child(1)")).click();
        }
    }

    public static class CadastroParceiroPage {
        private final WebDriver driver;

        public CadastroParceiroPage(WebDriver driver) {
            this.driver = driver;
        }

        public void preencherDadosPessoais(String nomeCompleto, String email, String cpf, String cep, String numero, String complemento) {
            driver.findElement(By.name("name")).sendKeys(nomeCompleto);
            driver.findElement(By.name("email")).sendKeys(email);
            driver.findElement(By.name("cpf")).sendKeys(cpf);
            driver.findElement(By.name("cep")).sendKeys(cep);
            driver.findElement(By.name("addressNumber")).sendKeys(numero);
            driver.findElement(By.name("addressDetails")).sendKeys(complemento);
            driver.findElement(By.cssSelector("input[type='button']")).click();
        }

        public void tentativaCadastro() {
            driver.findElement(By.cssSelector(".button-register")).click();
        }

        public void escolherAtividadeExtra(String atividade) {
            switch (atividade.toLowerCase()) {
                case "cuidar":

                    driver.findElement(By.cssSelector(".walker-service > li:nth-child(1) > span:nth-child(2)")).click();
                    break;
                case "adestrar":

                    driver.findElement(By.cssSelector(".walker-service > li:nth-child(2) > span:nth-child(2)")).click();
                    break;
                default:

                    System.out.println("Tipo de atividade extra inválido: " + atividade);
                    break;
            }
        }

        public void fazerUploadDocumento(String caminhoDocumento) {
            driver.findElement(By.cssSelector("input[type='file']")).sendKeys(caminhoDocumento);
            driver.findElement(By.cssSelector(".button-register")).click();
        }

        public String obterMensagem() {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            try {
                WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));

                return elemento.getText();
            } catch (Exception e) {

                return "Elemento com ID 'swal2-html-container' não encontrado.";
            }
        }

        public String obterMensagemErro(String campo) {
            String campoLocator = String.format("div.field-group > div:contains('%s') > span", campo);
            return driver.findElement(By.cssSelector(campoLocator)).getText();
        }

        public void validarMensagensDeErro(Map<String, String> dados) {

            WebElement nomeErrorElement = driver.findElement(By.cssSelector("#page-walker > form:nth-child(2) > fieldset:nth-child(3) > div:nth-child(2) > div:nth-child(1) > span:nth-child(2)"));
            String nomeErrorMessage = nomeErrorElement.getText();
            Assert.assertEquals(dados.get("Nome Completo"), nomeErrorMessage);


            WebElement emailErrorElement = driver.findElement(By.cssSelector("div.field-group:nth-child(3) > div:nth-child(1) > span:nth-child(2)"));
            String emailErrorMessage = emailErrorElement.getText();
            Assert.assertEquals(dados.get("E-mail"), emailErrorMessage);


            WebElement cpfErrorElement = driver.findElement(By.cssSelector("div.field-group:nth-child(3) > div:nth-child(2) > span:nth-child(2)"));
            String cpfErrorMessage = cpfErrorElement.getText();
            Assert.assertEquals(dados.get("CPF"), cpfErrorMessage);


            WebElement numeroErrorElement = driver.findElement(By.cssSelector("div.field-group:nth-child(4) > div:nth-child(1) > span:nth-child(2)"));
            String numeroErrorMessage = numeroErrorElement.getText();
            Assert.assertEquals(dados.get("Número"), numeroErrorMessage);


            WebElement documentoErrorElement = driver.findElement(By.cssSelector("span.alert-error:nth-child(7)"));
            String documentoErrorMessage = documentoErrorElement.getText();
            Assert.assertEquals(dados.get("Documento"), documentoErrorMessage);
        }
    }
}
