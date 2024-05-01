package br.com.joaotadeu.steps.steps_parodify;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class parodify_steps {

    @Dado("que estou na página principal do Parodify")
    public void que_estou_na_página_principal_do_parodify() {
        System.out.println("Passei aqui");
    }

    @Quando("efetuo a busca referente as musicas apresentadas na plataforma")
    public void efetuo_a_busca_referente_as_musicas_apresentadas_na_plataforma(io.cucumber.datatable.DataTable dataTable) {
        System.out.println("Passei aqui");
    }

    @Então("valido que a busca foi feita com sucesso")
    public void valido_que_a_busca_foi_feita_com_sucesso() {
        System.out.println("Passei aqui");
    }


    @Então("vou interagir com o botão home")
    public void vouInteragirComOBotaoHome() {
        
    }

    @E("validar que foi direcionado para página principal do Parodify")
    public void validarQueFoiDirecionadoParaPaginaPrincipalDoParodify() {
        
    }

    @Então("vou interagir com o botão play")
    public void vouInteragirComOBotaoPlay() {
        
    }

    @E("vou interagir com o botão mute")
    public void vouInteragirComOBotaoMute() {
    }

    @E("validar que a funcionalidade esta de acordo")
    public void validarQueAFuncionalidadeEstaDeAcordo() {
    }

    @Quando("verifico que as playlists estão visiveis")
    public void verificoQueAsPlaylistsEstaoVisiveis() {
    }

    @Então("valido que as playlists estão sendo exibidas na plataforma")
    public void validoQueAsPlaylistsEstaoSendoExibidasNaPlataforma() {
    }

    @Quando("vou interagir com o botão playlist")
    public void vouInteragirComOBotaoPlaylist() {
    }

    @Então("valido interação com susceso")
    public void validoInteracaoComSusceso() {
    }

    @Quando("vou interagir com o botão favoritos")
    public void vouInteragirComOBotaoFavoritos() {
    }
}
