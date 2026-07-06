# Sistema-Biblioteca

## Sobre o Projeto
O **Sistema Biblioteca** é uma aplicação web desenvolvida para automatizar e gerenciar os fluxos críticos de uma biblioteca, garantindo integridade de dados e facilidade de uso para os bibliotecários. O sistema abrange desde o cadastro de alunos e livros até a gestão completa de empréstimos e devoluções, incluindo o cálculo automático de multas por atraso.

---

## Funcionalidades Principais
* **Gestão de Alunos:** Cadastro e controle de estudantes matriculados.
* **Gestão de Acervo:** Cadastro de livros e controle de disponibilidade de exemplares.
* **Fluxo de Empréstimos:** Registro de saídas de livros atrelados aos alunos, com cálculo automático de data prevista para devolução.
* **Fluxo de Devolução:** Baixa de empréstimos com cálculo automático de dias de atraso e geração de multas (R$ 2,00 por dia de atraso), retornando o status do livro para disponível.

---

## Tecnologias Utilizadas
* **Backend:** Java 17, Spring Boot 3.x
* **Persistência:** Hibernate JPA, Banco de Dados MySQL
* **Frontend:** HTML5, CSS3, Thymeleaf (Server-side rendering)
* **Arquitetura:** Padrão MVC em camadas (Model, View, Controller, DAO)
* **DevOps:** GitHub Actions (CI/CD para testes unitários e build)

---

## Configuração e Execução (Como rodar o projeto)
**Em Run/Debug Configurations, no campo Environment variables digite: DB_PASSWORD=insira aqui a sua senha do MySQL. Clique em Aplicar e OK. Isso foi feito para que nenhuma senha seja exposta.**


---

## Documentação do Projeto
Toda a documentação detalhada de Engenharia de Software, incluindo as Decisões de Arquitetura, Diagramas UML (Classes e Sequência), Casos de Teste e validações estão centralizadas no nosso Notion:

https://app.notion.com/p/Documenta-o-Engenharia-de-Software-Sistema-Biblioteca-37d3353a4d518054a651cc0bdb16d569?source=copy_link

---
**Desenvolvido por:** Luanna Garla, Keila Horiye e Gabriela Vieira.
