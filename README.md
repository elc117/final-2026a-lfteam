# 🥋 Jiu-Jitsu Manager

## 1. Identificação

**Nome:** Luis Felipe  
**Curso:** Sistemas de Informação — UFSM  

---

## 2. Proposta

Sistema web para gerenciamento de equipes de Jiu-Jitsu, permitindo o cadastro e controle de atletas, professores, equipes, treinos e graduações.

Desenvolvido com Java 21 + Javalin para o backend (API REST) e HTML/CSS/JavaScript para o frontend.

---

## 3. Processo de Desenvolvimento

Durante o desenvolvimento do projeto, comecei identificando quais informações precisariam ser gerenciadas pelo sistema e, a partir disso, criei as classes Atleta, Professor, Equipe, Graduação e Treino, levenda em conta que pratico jiu-jitsu, então foi tranquilo identificar os grupos necessários. Em cada uma delas defini atributos privados para armazenar os dados e utilizei getters e setters. Depois disso, desenvolvi a API utilizando Java e Javalin, implementando gradualmente as operações de cadastro, consulta, edição e remoção de dados. Conforme o projeto evoluía, fui ajustando as classes para permitir a conversão automática entre JSON e objetos Java, adicionando construtores vazios e métodos setters quando necessário.

Precisei ver exemplos e consultar materias para iniciar e desenvolver os códigos e entender com os endpoints funcinavam e qual era alógica por trás deles, relembrar e ver estruturas que melhor se encaixavam.

A fase que apresentou mais desafios foi a integração com o banco de dados PostgreSQL e a publicação da aplicação. Encontrei dificuldades na conexão com o banco hospedado no Render, pois o formato da URL fornecida pela plataforma não era compatível com o esperado pelo JDBC. Nesta parte, precisei utilizar ajuda da IA para me ajudar a identificar o erro em questão e uma possivel solução, onde implementei uma conversão automática da URL, resolvendo o problema, essa solução foi utilizada por recomendação da IA. Também precisei configurar o CORS para permitir a comunicação entre o frontend e o backend hospedados em plataformas diferentes, no qual também precisei buscar ajuda com IA. Outro desafio foi o deploy da aplicação, já que o Render não executava o projeto Java da forma esperada. A solução encontrada foi utilizar Docker para empacotar toda a aplicação, permitindo que ela fosse executada corretamente em ambiente de produção.
Eu já havia trabalhado com base de dados antes mas de uma forma totalmente diferente e mais "simples", então de começo fiquei bem desorientado para entender e implementar o que o projeto pedia.

---

## 4. Diagrama de Classes

![Diagrama de Classes](docs/classes.png)

---

## 5. Orientações para Execução

### Pré-requisitos

- Java 21
- Maven

### Executar localmente

```bash
# Clonar o repositório
git clone https://github.com/elc117/final-2026a-lfteam.git
cd final-2026a-lfteam

# Compilar
mvn clean package -DskipTests

# Executar
java -jar target/jiujitsu-manager-1.0-SNAPSHOT.jar
```

Acesse em: `http://localhost:7070`

### Executar no Codespaces

1. Abrir o repositório no GitHub
2. Clicar em **Code → Codespaces → Create codespace on main**
3. No terminal: `mvn clean package -DskipTests && java -jar target/jiujitsu-manager-1.0-SNAPSHOT.jar`

### Versão online

- **Frontend:** https://elc117.github.io/final-2026a-lfteam/
- **Backend:** https://jiu-jitsu-maneger.onrender.com

---
---

## 7. Referências e Créditos

### Documentação

- [Javalin Documentation](https://javalin.io/documentation)
- [Javalin Maven Setup](https://javalin.io/tutorials/maven-setup)
- [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/documentation/)
- [Render Deploy Docs](https://render.com/docs)
- 
---

## 📓 Diário de Desenvolvimento

### 08/06/2026 — Modelagem e Estruturação Inicial

**Modelagem do Sistema**

- Identificação das principais entidades do domínio: Atleta, Professor, Equipe, Treino e Graduação
- Definição dos relacionamentos entre as entidades
- Elaboração do diagrama UML de classes
- Inclusão da ilustração do diagrama no repositório para melhor entendimento

**Estruturação do Projeto**

- Criação da estrutura inicial dos diretórios do projeto
- Organização dos arquivos nas pastas `src/main/java/model`

**Implementação Inicial**

Foram criadas as seguintes classes: `Atleta.java`, `Professor.java`, `Equipe.java`, `Treino.java` e `Graduacao.java`.

As classes foram implementadas inicialmente com os atributos principais necessários para representar os objetos do sistema — atributos simples como id, nome, idade, descrição, etc.

A ideia é ir deixando o projeto mais completo, saindo da estrutura inicial, para ter um norte de onde começar e ir implementando os requisitos pedidos.

---

### 13/06/2026 (01:00 — 06:00) — Configuração do Ambiente e Implementação da API

Dei continuidade no projeto e organizei melhor toda a estrutura inicial. Comecei revisando a proposta do sistema e o planejamento das funcionalidades que propus. Também configurei o ambiente de desenvolvimento utilizando o Codespaces e organizei a estrutura do projeto.

**Pesquisas e desenvolvimento**

Antes de iniciar a implementação, busquei entender os exemplos disponibilizados, para ver quais se encaixavam melhor à minha ideia e como funcionavam também. Verifiquei o material sobre Javalin do dia 27/05 e percebi que o exemplo utilizava Maven para gerenciamento de dependências e organização da aplicação.

Nunca tive experiência com Maven, então precisei fazer pesquisas sobre sua finalidade, como instalar e como ele se integra a projetos Java.

- https://javalin.io/tutorials/maven-setup
- https://javalin.io/documentation

Configurei o Maven no ambiente de desenvolvimento e organizei a estrutura do projeto, estudei a documentação e os exemplos de uso do Javalin para entender como criar uma aplicação web simples. Fiz o primeiro teste para verificar se a configuração da rota estava certa.

**Dificuldades**

Tive dificuldades para entender inicialmente a estrutura correta de um projeto Maven, principalmente em relação à localização dos arquivos e organização das pastas. Também encontrei problemas porque o Maven não estava disponível no ambiente, o que impedia a compilação do projeto. Outro desafio foi compreender os primeiros passos de integração do Javalin com a aplicação.

**Resolução**

Verifiquei a documentação das ferramentas, os exemplos disponibilizados e realizei testes até identificar os problemas. Depois de reorganizar a estrutura do projeto, instalar e configurar o Maven corretamente, consegui compilar a aplicação e executar o primeiro servidor utilizando Javalin.

**Uso de IA**

Utilizei a IA principalmente para ajudar a compreender e corrigir os problemas na criação da rota e para entender a configuração e a instalação do framework Javalin e Maven. Não estive presente na aula em que teve a explicação, então a IA me ajudou a ter um norte e a compreender como as aplicações funcionam.

---

### 14/06/2026 — Deploy do Backend e Frontend

**O que foi feito:**
- Porta dinâmica via variável de ambiente `PORT`, CORS habilitado no Javalin
- Adição do `maven-shade-plugin` no `pom.xml` para geração do fat JAR
- Criação do `Dockerfile` para containerização (necessário pois o Render não oferece runtime Java nativo)
- Deploy do backend no Render: https://jiu-jitsu-maneger.onrender.com
- Atualização do frontend com a URL real da API
- Publicação do frontend no GitHub Pages

**Dificuldades:**
- Render detectou o projeto como Node.js em vez de Java, causando falha no build (`mvn: command not found`)
- Solução: criação de um `Dockerfile` com imagem Maven + Java 21

**Uso de IA:**
IA auxiliou na configuração do Dockerfile, na identificação do problema de runtime no Render e nos ajustes do `pom.xml`.

---

### 19/06 a 24/06/2026 — CRUD Completo + PostgreSQL

**O que foi feito:**
- Adição de setters e construtor vazio nas classes model (necessários para o Jackson deserializar o JSON recebido nas requisições POST e PUT)
- Endpoints POST, PUT e DELETE para todas as entidades
- Frontend atualizado com formulários de cadastro, edição e exclusão
- Criação do banco PostgreSQL no Render
- Integração do backend com PostgreSQL — dados persistem entre reinicializações do servidor

**Dificuldades:**
- A URL do PostgreSQL fornecida pelo Render (`postgresql://user:pass@host/db`) não é aceita diretamente pelo driver JDBC
- Solução: método `toJdbcUrl()` no `Main.java` que converte automaticamente para o formato `jdbc:postgresql://host/db?user=user&password=pass&sslmode=require`

**Uso de IA:**
IA auxiliou na implementação dos endpoints CRUD, na configuração do PostgreSQL e na resolução do problema de formato da URL JDBC.
