package br.com.joaotadeu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class parodifyPage {

    private WebDriverWait wait;
    private WebDriver navegadorDriver;

    public parodifyPage(WebDriver driver) {
        this.navegadorDriver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void acessarParodify() {
        navegadorDriver.get("https://parodify.vercel.app");
    }

    public void realizarBusca(String termoBusca) {
        WebElement searchInput = navegadorDriver.findElement(By.id("search-input"));
        searchInput.sendKeys(termoBusca);

        WebElement searchButton = navegadorDriver.findElement(By.cssSelector("button[class='ml-4']"));
        searchButton.click();
    }

    public void clicarBotaoPlay() {
        WebElement botaoPlay = navegadorDriver.findElement(By.cssSelector("button.play:nth-child(3)"));
        botaoPlay.click();
    }

    public void clicarBotaoMute() {
        WebElement botaoMute = navegadorDriver.findElement(By.cssSelector("div.min-w-\\[11\\.25rem\\]:nth-child(3) > button:nth-child(4)"));
        botaoMute.click();
    }

    public void clicarBotaoHome() {
        WebElement botaoHome = navegadorDriver.findElement(By.cssSelector(".bg-active"));
        botaoHome.click();
    }

    public void clicarPlaylist() {
        WebElement botaoPlaylist = navegadorDriver.findElement(By.cssSelector("nav.mt-6 > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
        botaoPlaylist.click();
    }

}
