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

Uma vez que a aplicação estiver rodando acesse `localhost:8080` no seu navegado.

## Diagramas

Na pasta `walmart/docs` estão contidos alguns diagramas UML que dão uma visão geral da arquitetura da aplicação.

## Tecnologias Utilizadas

* Spring Boot
* AngularJS
* Maven
* BoxFuse

## Disponível no AWS

A aplicação também está disponível em `shopping-marceloandradep.boxfuse.io:8080`
