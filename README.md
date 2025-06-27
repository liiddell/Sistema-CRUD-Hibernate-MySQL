# Sistema CRUD Hibernate + MySQL

Este projeto é um sistema simples de cadastro de clientes, utilizando Java com Hibernate e MySQL. A arquitetura está organizada em pacotes seguindo boas práticas de separação de responsabilidades.

---

## 🏗️ Estrutura do Sistema

**Principais componentes do sistema:**

- **`hibernate.cfg.xml` (Configuração):** Arquivo XML onde estão as informações de conexão com o banco de dados, dialeto e mapeamento da entidade.

- **`HibernateUtil` (Utilitário de configuração):** Classe que centraliza a criação e gerenciamento do `SessionFactory` do Hibernate, responsável por criar conexões com o banco.

- **`Cliente` (Model/Entidade):** Representa os dados dos clientes no sistema. Utiliza anotações JPA como `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, etc.

- **`ClienteDAO` (Camada de persistência):** Responsável por interagir com o banco de dados usando Hibernate. Contém métodos como `salvar()`, `atualizar()`, `deletar()`, `buscarPorId()` e `listarTodos()`.

- **`Main` (Classe principal):** Fornece um menu interativo via console para executar as operações CRUD. Lê entrada do usuário usando `Scanner`.

---

## ⚙️ Passos para Configurar o Hibernate

**1. Configurar o Maven (`pom.xml`)**  
Incluídas as dependências necessárias:
- `hibernate-core` para o funcionamento do Hibernate.
- `mysql-connector-java` para conectar com o MySQL.
- `jakarta.persistence-api` para usar as anotações JPA.

**2. Criar a entidade (classe `Cliente`)**
- Anotada com `@Entity` e `@Table`.
- Utiliza `@Id`, `@GeneratedValue`, `@Column` para mapear os campos corretamente no banco.

**3. Criar o arquivo `hibernate.cfg.xml`**
- Define o driver JDBC, URL do banco, usuário e senha.
- Define o dialeto (`MySQL8Dialect` ou `MariaDBDialect`).
- Ativa `hbm2ddl.auto = update` para criar ou atualizar tabelas automaticamente.
- Mapeia a classe `Cliente`.

**4. Implementar o `HibernateUtil`**
- Usa `StandardServiceRegistryBuilder` para carregar as configurações.
- Gera uma `SessionFactory` reutilizável em todo o sistema.

**5. Criar a DAO (`ClienteDAO`)**
- Abre sessões com `SessionFactory`.
- Controla transações com `beginTransaction()`, `commit()` e `rollback()`.
- Usa `session.save()`, `session.update()`, `session.delete()` e HQL.

**6. Criar o menu (`Main`)**
- Menu interativo via console que chama os métodos da DAO com base na entrada do usuário.

---

## 🎓 Principais Aprendizados

- **Separação de responsabilidades:** como dividir o código em camadas (modelo, DAO, utilitário e view).
- **Uso de ORM com Hibernate:** abstração da SQL nativa para uso de objetos.
- **Transações e sessões:** importância de abrir/fechar sessões e controlar transações para integridade dos dados.
- **Mapeamento JPA:** como utilizar anotações como `@Entity`, `@Table`, `@Column`, etc.
- **Configuração via XML:** aprendizado sobre a estrutura e sintaxe de arquivos como `hibernate.cfg.xml`.

---

## ⚠️ Dificuldades Enfrentadas

- **Entendimento do Hibernate:** configurar corretamente o `SessionFactory` e evitar problemas de múltiplas instâncias.
- **Controle de erros:** evitar que o sistema quebre ao ocorrer falhas no banco, especialmente sem tratamento adequado.
- **Conexão com o banco:** lidar com erros de driver ou URL incorreta, principalmente durante o primeiro contato com JDBC.
- **Mapeamento incorreto:** erros comuns ao não registrar a classe no `hibernate.cfg.xml` ou nome de tabela diferente do banco.
