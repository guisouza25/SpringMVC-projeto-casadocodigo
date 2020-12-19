## Projeto Spring MVC

Projeto de uso educacional de uma aplicação de comércio de livros desenvolvido durante a formação Java da Alura, semelhante à Casa do Código.

Neste projeto foram utilizadas as seguintes ferramentas:

* Spring MVC
* Spring Security
* Hibernate JPA
* Amazon Web Services
* Boostrap
* Apache TomCat versão 9
* JDK versão 8

## Deploy

O projeto encontra-se publicado (deployed) na Amazon Web Services em uma instância EC2, com um banco de dados RDS e utilizando um Bucket S3 para armazenamento das imagens. Acesse o link:

	http://15.228.73.52:8080/casadocodigo/

## Acesso de ADMIN

A aplicação possui funcionalidades gerenciais na qual é possivel acessar resumidamente todos os livros cadastrados, bem como editar e inserir novos livros. 
Acesse a URI informando email e senha:

	http://15.228.73.52:8080/casadocodigo/login

Email: admin@casadocodigo.com.br

Senha: 123456
  
## Compilação

Caso queira compilar o projeto e subir em um servidor local pode excutar o comando `mvn clean package` para gerar o WAR ou importar o projeto no Eclipse. 
A aplicação utiliza o MySql e já deve existir o database `casadocodigo`. Caso queira trocar o banco altere a classe `JPAConfiguration`. Acesse a url:

	http://localhost:8080/casadocodigo
  
acessando `http://localhost:8080/casadocodigo/login` para o acesso de admin.
