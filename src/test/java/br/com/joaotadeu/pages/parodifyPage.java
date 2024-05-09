package br.com.joaotadeu.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class parodifyPage {

    private WebDriver navegadorDriver;

    public parodifyPage(WebDriver driver) {
        this.navegadorDriver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void acessarParodify() {
        navegadorDriver.get("https://parodify.vercel.app");
    }

}
