API-Mecado | Sistema Mercado Dany

Integrantes:
- Catarina Klein
- Lucas Schlei
- Murilo Kerschbaum

Sobre o Projeto:

O Sistema Mercado Dany surgiu de uma discussão descontraída entre o Murilo e o docente Vinícius sobre qual bairro possuía os melhores mercados: Jaraguá Esquerdo ou Corupá. 
Durante a conversa, o Mercado Dany foi citado como referência por Murilo, dando origem à ideia de desenvolver um sistema completo para gerenciamento de um mercado.



O projeto foi dividido em dois repositórios:

Backend: desenvolvido em Java, utilizando o Spring Boot, no IntelliJ IDEA
Frontend: desenvolvido em Kotlin, no Android Studio

A escolha dessas tecnologias se deve à forte integração entre Java e Kotlin, facilitando a comunicação entre as camadas da aplicação.



* Estrutura do Sistema

O backend segue uma arquitetura em camadas, organizada em packages para melhor separação de responsabilidades:

* Model

Contém as entidades do sistema, ou seja, as classes que representam os dados (Usuário, Produto, Vendas, Carrinho).

Define os atributos
Possui métodos getters e setters
Representa as tabelas do banco de dados



* Repository

Responsável pela comunicação com o banco de dados.

Utiliza o Spring Data JPA o que evita a necessidade de escrever SQL manual



* Service

Camada onde fica a lógica de negócio.

Processa regras do sistema
Valida dados antes de salvar
Atua como intermediário entre Controller e Repository



* Controller

Responsável por expor o backend como uma API REST.

Recebe requisições HTTP:
GET
POST
PUT
DELETE
Retorna respostas para o frontend
Faz a comunicação entre usuário e sistema



* DTO (Data Transfer Object)

Utilizado para transferência de dados entre frontend e backend.

Evita expor diretamente as entidades (Model)
Permite controlar exatamente o que entra e o que sai da API
Exemplo:
Request (entrada de dados)
Response (retorno da API)



* Mapper

Responsável por converter objetos entre diferentes camadas.

Converte:
DTO → Model
Model → DTO
Ajuda a manter a separação de responsabilidades
Evita que o Controller ou Service precisem fazer conversões manualmente



* Usuário do Sistema

O sistema possui como principal usuário o:

Cliente do mercado

Ele interage com o sistema por meio do aplicativo, consumindo os dados da API desenvolvida no backend.



* Versionamento

O projeto utiliza Git e segue boas práticas de versionamento:



* Branches
master: versão estável do sistema
develop: ambiente de desenvolvimento
branches auxiliares para novas funcionalidades (feature branches)



* Commits
Commits frequentes e descritivos
Representam pequenas alterações no sistema



* Pull Requests (PR)
Utilizados para revisão de código
Garantem maior controle antes de integrar mudanças



* Merge
Integração das alterações aprovadas nas branches principais

Esse fluxo garante organização, rastreabilidade e colaboração eficiente entre os desenvolvedores.




* Tecnologias Utilizadas
Java + Spring Boot
Kotlin (Android)
Spring Data JPA
Git e GitHub



* Considerações Finais

O Sistema Mercado Dany foi desenvolvido com foco em organização, boas práticas e separação de responsabilidades,
simulando um ambiente real de desenvolvimento.

Além de atender aos requisitos funcionais, o projeto também reforça conceitos importantes como:

* Arquitetura em camadas
* Desenvolvimento de APIs REST
* Versionamento com Git
