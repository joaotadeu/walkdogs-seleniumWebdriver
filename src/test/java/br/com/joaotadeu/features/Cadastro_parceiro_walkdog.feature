#language: pt

  Funcionalidade: Como usuário quero acessando o site de cadastro Walkdog
                  Cadastrar parceiro com sucesso
                  Validando que o parceiro foi cadastrado com sucesso

  @cadastro_com_sucesso
  Cenario: Cadastro de parceiro no WebApp Walkdog com sucesso
    Dado que estou na pagina principal do WalkDog
    Quando preencho os dados pessoais do parceiro
      E escolho atividade extra
      E faço upload do documento de verificação do parceiro
    Então devo ver a mensagem de cadastro com sucesso