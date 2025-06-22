# JsonPlaceHolder

Este projeto é um exemplo de aplicativo Android desenvolvido em **Kotlin** utilizando **Jetpack Compose** e **Retrofit** para consumir dados da API [JSONPlaceholder](https://jsonplaceholder.typicode.com).

## Funcionalidades

- Tela inicial com dois botões: **Ver Posts** e **Ver Todos**
- Consumo das rotas:
    - `/posts` – Lista de posts
    - `/todos` – Lista de tarefas
- Exibição das listas com carregamento, tratamento de erro e retry
- Navegação entre as telas com `NavHost`
- ViewModel com `mutableStateOf` para controle de estado da UI

## Tecnologias Usadas

- Kotlin
- Jetpack Compose
- Retrofit
- ViewModel
- State Management
- Navigation Compose
