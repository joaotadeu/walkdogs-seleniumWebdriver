package br.com.joaotadeu.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class TesteCucumber_steps {
    @Dado("que tenho o cucumber instalado")
    public void que_tenho_o_cucumber_instalado() {
        System.out.println("Passei aqui");
    }

    @Quando("executo o cucumber")
    public void executo_o_cucumber() {
        System.out.println("Passei aqui");
    }

    @Então("valido que o cucumber está funcional")
    public void valido_que_o_cucumber_está_funcional() {
        System.out.println("Passei aqui");
    }

}
