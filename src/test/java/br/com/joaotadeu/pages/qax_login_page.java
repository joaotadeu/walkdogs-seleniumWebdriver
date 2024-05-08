package br.com.joaotadeu.pages;

import org.openqa.selenium.WebDriver;

public class qax_login_page {

    private WebDriver navegadorDriver;

    public qax_login_page(WebDriver driver) {
        this.navegadorDriver = driver;
    }

    public void acessarPaginaQAX() {
        navegadorDriver.get("https://loginxp.vercel.app/");
    }

}
