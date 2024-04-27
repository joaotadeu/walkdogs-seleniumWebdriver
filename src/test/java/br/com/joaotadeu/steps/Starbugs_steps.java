package br.com.joaotadeu.steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class Starbugs_steps {
    @E("o valor total da compra deve ser de {string}")
    public void oValorTotalDaCompraDeveSerDeR$(int arg0, int arg1) {
    }

    @E("que iniciei a compra do item {string}")
    public void queInicieiACompraDoItem(String arg0) {
    }

    @Quando("faço a busca do seguinte CEP: {string}")
    public void facoABuscaDoSeguinteCEP(String arg0) {
    }

    @E("informo os demais dados do endereço:")
    public void informoOsDemaisDadosDoEndereco() {
    }

    @E("escolho a forma de pagamento: {string}")
    public void escolhoAFormaDePagamento(String arg0) {
    }

    @E("por fim finalizo a compra")
    public void porFimFinalizoACompra() {
    }

    @Então("sou redirecionado para a página de confirmação de Pedidos e visualizo a mensagem {string}")
    public void souRedirecionadoParaAPaginaDeConfirmacaoDePedidosEVisualizoAMensagem(String arg0) {
    }

    @E("deve ser informado o seguinte prazo de entrega: {string}")
    public void deveSerInformadoOSeguintePrazoDeEntrega(String arg0) {
    }

    @Então("devo ver um popup informando que o produto está indisponível com a mensagem {string}")
    public void devoVerUmPopupInformandoQueOProdutoEstaIndisponivelComAMensagem(String arg0) {
    }
}
