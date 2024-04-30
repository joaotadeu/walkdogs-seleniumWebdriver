#language: pt

Funcionalidade: Catálogo de cafés
  Como um usuário do site, eu quero ver o catálogo de cafés na pagina principal
  Para que eu possa escolher e saber mais sobre os produtos disponíveis

  @validação_catalogo
  Cenario: Acessando pagina web do starbugs e efetuar compras
    Dado que estou na pagina principal da Starbugs
    Então eu devo visualizar uma lista de cafes disponíveis

  @checkout_cafe
  Esquema do Cenario: Iniciar a compra de um café
    Dado que estou na pagina principal da Starbugs
    Quando desejo comprar o seguinte produto
      | Nome   | Preço   | Entrega   |
      | <Nome> | <Valor> | <Entrega> |
    Então devo ver a página de Checkout com os detalhes do produto
    E o valor total da compra deve ser de '<Valor total>'

    Exemplos:
      | Nome      | Valor | Entrega | Valor total |
      | Capuccino | 19,99 | 10,00   | 29,99       |

  @compra_sucesso
  Cenario: Compra bem sucedida
    Dado que estou na pagina principal da Starbugs
    Quando desejo comprar o seguinte produto
      | Nome   | Preço   | Entrega   |
      | <Nome> | <Valor> | <Entrega> |
    Então devo ver a página de Checkout com os detalhes do produto
    E o valor total da compra deve ser de '<Valor total>'
    Quando faço a busca do seguinte CEP: "14020055"
    E informo os demais dados do endereço:
      | Numero   | Detalhes     |
      | 2072     | Apto 65      |
    E escolho a forma de pagamento: "Cartão de Débito"
    E por fim finalizo a compra
    Então sou redirecionado para a página de confirmação de Pedidos e visualizo a mensagem "Uhull! Pedido confirmado"
    E deve ser informado o seguinte prazo de entrega: "20 min - 30 min"

  @compra_indisponivel
  Cenário: Café indisponível
    Dado que estou na página principal da Starbugs
    E que desejo comprar o seguinte produto:
      | Nome              |
      | Expresso Cremoso  |
    Quando inicio a compra desse item
    Então devo ver um popup informando que o produto está indisponível com a mensagem "Produto indisponível"