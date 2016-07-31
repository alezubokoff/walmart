# Shopping

Emula um pequeno e-commerce com cadastro de produtos e carrinho de compras. Ao final é possível visualizar uma tela de checkout com o sumário do pedido.

## Instalação

Baixe o repositório.

```
git -clone https://github.com/marceloandradep/walmart.git
```

Vá para a pasta do projeto.

```
cd walmart/source/shopping
```

Crie o pacote da aplicação.

```
mvn package
```

Rode a aplicação com o comando:

```
java -jar target\shopping-1.0.jar
```
## Utilização

Uma vez que a aplicação estiver rodando acesse `localhost:8080` no seu navegador.

## Diagramas

Na pasta `walmart/docs` estão contidos alguns diagramas UML que dão uma visão geral da arquitetura da aplicação.

## Tecnologias Utilizadas

* Spring Boot: Para não criar muito overhead na instalação escolhi o Spring Boot pois ele gera um pacote auto contigo com um servidor de aplicação já embutito. Isto facilita na instalação e nos testes, mas também facilita na hora do deploy com a integração com o BoxFuse.
* AngularJS: Optei por utilizar um framework javascript para criar uma experiência mais rica e responsiva sem utilizar muito código (caso tivesse escolhido utilizar SpringMVC + Templates + JQuery). O requisito do teste relacionado à fidelidade do layout também me levou a escolher um framework que me desse total controle sobre o HTML gerado, o que fez eu descartar utilizar JSF, por exemplo.
* Bootstrap: Decidi utilizar o bootstrap por integrar muito bem com o Angular e também pelo fato de que ele acelera bastante o desenvolvimento dos sites. Não tenho grande experiência na construção de front-end, então pensei em escolher frameworks que facilitassem ao máximo essa tarefa.
* BoxFuse: BoxFuse é uma plataforma que automatiza o deploy no AWS. Ele basicamente cria uma imagem com o mínimo necessário para a aplicação rodar numa instância EC2. Isso inclui um kernel linux minimalista ao invés de SO completo.

## Disponível no AWS

A aplicação também está disponível em `shopping-marceloandradep.boxfuse.io:8080`
