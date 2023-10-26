<p align="center">
  <img src="https://github.com/jsericksk/Cedro-News-App/raw/main/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.webp" width="100">
</p>
<h1 align="center">
  Cedro News
</h1>

<p align="center">
  <img src="screenshots/demo-00.gif" width="320" height="691" />
</p>

Projeto criado com o objetivo de consumir a [minha API](https://github.com/jsericksk/Cedro-News-Api) que extrai informações relevantes da página de notícias do [site da prefeitura de Cedro-CE](https://cedro.ce.gov.br/informa.php).

Assim como a API, esse aplicativo não tem como finalidade ser de grande utilidade, apenas criei para ter um sisteminha completo de API + app. O ícone do app foi criado no [Picsvg](https://picsvg.com/) com base no [brasão da cidade](https://cedro.ce.gov.br/simbolos.php).

- Algumas notícias têm uma certa má formatação de texto (ex.: contém muita ou falta quebra de linha). Devido ao HTML da página, não consegui contornar isso nem na API, nem no app.

## Download APK

Se estiver interessado em testar o app, você pode baixar o APK nas [releases](https://github.com/jsericksk/Cedro-News-App/releases).

## :hammer_and_wrench: Bibliotecas utilizadas

- [Retrofit](https://github.com/square/retrofit): Consumo de API.
- [Koin](https://github.com/InsertKoinIO/koin): Injeção de dependências.
- [Landscapist](https://github.com/skydoves/landscapist): Carregamento de imagens.
- [Paging3-Compose](https://developer.android.com/topic/libraries/architecture/paging/v3-overview): Paginação de dados.