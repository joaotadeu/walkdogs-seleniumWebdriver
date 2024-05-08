#language: pt

  @login_qax
  Funcionalidade: Como usuario desejo efetuar o login no portal QAX
                  Logar com sucesso, sem sucesso e validar que a apliação se comporta como deveria
                  Validar campos nulos e validar respostas de sucesso e sem sucesso

  @login_sem_sucesso
  Esquema do Cenario: Como usuario desejo validar o sucesso da aplicação
                      sem sucesso e validando campos nulos

    Dado que estou na página de login no portal QAX
    Quando preencho as credencias do usuario:
      | usuario   | senha   |
      | <usuario> | <senha> |
    Então valido que a resposta do sistema está de acordo com a validação verificada
      | mensagem_esperada   |
      | <mensagem_esperada> |

    Exemplos:
      | usuario | senha   | mensagem_esperada              |
      | teste   | [empty] | Informe a sua senha secreta!   |
      | [empty] | teste   | Informe o seu nome de usuário! |
      | teste   | 123     | Oops! Credenciais inválidas :( |

    @login_com_sucesso
    Cenário: Como usuario vou validar o sucesso no login da aplicação
      Dados que estou na página de login no portal QAX
      Quando Preencho as credencias do usuario "qa" e senha "xperience"
      Então valido que o usuario foi logado com sucesso