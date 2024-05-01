#language: pt

@starbugs
Funcionalidade: Catálogo de musicas na plataforma Parodify
  Como usuario do site quero buscar sobre alguma musica determinada pela exibição do site
  para que eu possa buscar as musicas e interagir com elas

  @buscar_musicas
  Esquema do Cenário: Acessar pagina do Parodify
                      Buscar na plataforma a musica que estou procurando
                      Obter sucesso na pesquisa

    Dado que estou na página principal do Parodify
    Quando efetuo a busca referente as musicas apresentadas na plataforma
      | Musicas              |
      | <titulo das Musicas> |
    Então valido que a busca foi feita com sucesso

    Exemplos:
      | Titulo das Musicas|
      | Bughium           |

    @validação_home
    Cenário: Acessar pagina do Parodify
             Efetuar busca com sucesso
             interagir com o botão home

    Dado que estou na página principal do Parodify
    Quando efetuo a busca referente as musicas apresentadas na plataforma
      | Musicas              |
      | <titulo das Musicas> |
    Então vou interagir com o botão home
      E validar que foi direcionado para página principal do Parodify

    @funcionamento_reproducao
    Cenário: Acessar pagina do Parodify
             Efetuar busca com sucesso
             interagir com os itens de reprodução em tela

    Dado que estou na página principal do Parodify
    Quando efetuo a busca referente as musicas apresentadas na plataforma
      | Musicas |
      | Bughium |
    Então vou interagir com o botão play
      E vou interagir com o botão mute
      E validar que a funcionalidade esta de acordo

    @validar_playlists
    Cenário: Acessar pagina do Parodify
             Efetuar busca com sucesso
             interagir com os itens de reprodução em tela
    Dado que estou na página principal do Parodify
    Quando verifico que as playlists estão visiveis
      | Playlist     |
      | Back in Test |
      Então valido que as playlists estão sendo exibidas na plataforma


    @validando_playlist
    Cenário: Acessar pagina do Parodify
             Interagir com botão playlist

      Dado que estou na página principal do Parodify
      Quando vou interagir com o botão playlist
      Então valido interação com susceso


    @validando_favoritos
    Cenário: Acessar pagina do Parodify
             Interagir com botão favoritos

      Dado que estou na página principal do Parodify
      Quando vou interagir com o botão favoritos
      Então valido interação com susceso

