package br.com.joaotadeu.steps.steps_login_qax;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class qax_login_steps {
    @Dado("que estou na página de login no portal QAX")
    public void que_estou_na_página_de_login_no_portal_qax() {
        System.out.println("Passei por aqui");
    }
    @Quando("preencho as credencias do usuario:")
    public void preencho_as_credencias_do_usuario(io.cucumber.datatable.DataTable dataTable) {
        System.out.println("Passei por aqui");
    }
    @Então("valido que a resposta do sistema está de acordo com a validação verificada")
    public void valido_que_a_resposta_do_sistema_está_de_acordo_com_a_validação_verificada() {
        System.out.println("Passei por aqui");
    }


}
