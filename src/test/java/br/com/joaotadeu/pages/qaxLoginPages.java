package br.com.joaotadeu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class qaxLoginPages {

    private WebDriver navegadorDriver;
    private WebDriverWait wait;

    public qaxLoginPages(WebDriver driver) {
        this.navegadorDriver = driver;
    }

    public void acessarPaginaQAX() {
        navegadorDriver.get("https://loginxp.vercel.app/");
    }

    public void preencherUsuario(String usuario) {
        WebElement usuarioElemento = navegadorDriver.findElement(By.cssSelector("input[name=user]"));
        if (!"[empty]".equals(usuario)) {
            usuarioElemento.clear();
            usuarioElemento.sendKeys(usuario);
        }

    }

    public void preencherSenha(String senha) {
        WebElement senhaElemento = navegadorDriver.findElement(By.cssSelector("input[name=pass]"));
        if (!"[empty]".equals(senha)) {
            senhaElemento.clear();
            senhaElemento.sendKeys(senha);
        }
    }

    public void clicarBotaoLogin() {
        navegadorDriver.findElement(By.cssSelector("button[type=button]")).click();
    }

    public void aguardarToast() {
        WebDriverWait wait = new WebDriverWait(navegadorDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".App > div:nth-child(2) > div:nth-child(1)")));
    }

    public String obterTextoToast() {
        WebElement toast = navegadorDriver.findElement(By.cssSelector(".App > div:nth-child(2) > div:nth-child(1)"));
        return toast.getText().trim();
    }

    public void preencherUsuarios(String usuario) {
        WebElement usuarioElemento = navegadorDriver.findElement(By.cssSelector("input[name=user]"));
        usuarioElemento.sendKeys(usuario);
    }

    public void preencherSenhas(String senha) {
        WebElement senhaElemento = navegadorDriver.findElement((By.cssSelector("input[name=pass]")));
        senhaElemento.sendKeys(senha);
    }
}
