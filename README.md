# Diário de Desenvolvimento

## 08/06/2026

### Modelagem do Sistema

* Identificação das principais entidades do domínio:

  * Atleta
  * Professor
  * Equipe
  * Treino
  * Graduação

* Definição dos relacionamentos entre as entidades.

* Elaboração do diagrama UML de classes.

* Inclusão da ilustração do diagrama no repositório para melhor entendimento.

### Estruturação do Projeto

* Criação da estrutura inicial dos diretórios do projeto.
* Organização dos arquivos nas pastas src/main/java/model.

### Implementação Inicial

Foram criadas as seguintes classes:

* Atleta.java
* Professor.java
* Equipe.java
* Treino.java
* Graduacao.java

As classes foram implementadas inicialmente com os atributos principais necessários para representar os objetos do sistema.

Atributos simples como id, nome, idade, descrição, etc...

A ideia é ir deixando o projeto mais completo, saindo da estrutura inicial, para ter um norte de onde começar e ir implementado os requisitos repedidos.

## 13/06/2026 - 01:00am até 06:00am

Dei continuidade no projeto e organizei melhor toda a estrutura inicial.
Comecei revisando a proposta do sistema e o planejamento das funcionalidades que propus.

Também configurei o ambiente de desenvolvimento utilizando o codespaces e organizei a estrutura do projeto 

### Pesquisas e desenvolvimento

Antes de iniciar a implementação, busquei entender os exemplos disponibilizados, pra ver quais se encaixavam melhor pra minha ideia e de como funinavam também.

Verifiquei o material sobre Javalin do dia 27/05 e percebi que o exemplo utilizava Maven para gerenciamento de dependências e organização da aplicação.

Nunca tive experiência com Maven, então precisei fazer pesquisas sobre sua finalidade, como instalar e como ele se integra a projetos Java. 

https://javalin.io/tutorials/maven-setup

https://javalin.io/documentation

Configurei o Maven no ambiente de desenvolvimento e organizei a estrutura do projeto, estudei a documentação e os exemplos de uso do Javalin para entender como criar uma aplicação web simples.

Fiz o primeiro teste para verificar se a configuração da rota tava certa

### Dificuldades

Tive dificuldades para entender inicialmente a estrutura correta de um projeto Maven, principalmente em relação à localização dos arquivos e organização das pastas. Também encontrei problemas porque o Maven não estava disponível no ambiente, o que impedia a compilação do projeto. Outro desafio foi compreender os primeiros passos de integração do Javalin com a aplicação.

### Resolução

Verifiquei a documentação das ferramentas, os exemplos disponibilizados e realizei testes até identificar os problemas. depois de reorganizar a estrutura do projeto, instalar e configurar o Maven corretamente, consegui compilar a aplicação e executar o primeiro servidor utilizando Javalin.

### Uso de IA

Utilizei a IA principalmente para ajudar a compreender e corrigir os problemas na criação da rota e pra entender na configuração e na instalação do framework javalin e Maven.
Não estive presente na aula em que teve a explicação então a IA me ajudou a ter um norte e a compreender como as aplicações funcinam.




