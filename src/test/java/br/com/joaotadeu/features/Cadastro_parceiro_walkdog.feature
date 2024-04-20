#language: pt

  Funcionalidade: Como usuário quero acessando o site de cadastro Walkdog
                  Cadastrar parceiro com sucesso
                  Validando que o parceiro foi cadastrado com sucesso

  @cadastro_com_sucesso
  Cenario: Cadastro de parceiro no WebApp Walkdog com sucesso
    Dado que estou na pagina principal do WalkDog
    Quando preencho os dados pessoais do parceiro
      | Nome Completo     | E-mail          | CPF         | CEP      | Numero | Complemento |
      | João T. S Pereira | teste@gmail.com | 55224563957 | 01310930 | 2100   | Avenida     |
    E escolho atividade extra
      | Atividades Extras |
      | Cuidar            |
    E faço upload do documento de verificação do parceiro
        | Caminho Documento |
        | ????????????????? |
    Então devo ver a mensagem de cadastro com sucesso