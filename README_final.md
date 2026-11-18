# FrotaGesta

Sistema desktop para controle de frota de veículos, desenvolvido em **Java (Swing)** com banco de dados em **MySQL**.

O sistema permite cadastrar veículos e motoristas, controlar viagens, registrar abastecimentos e acompanhar quais veículos precisam de manutenção.

## Funcionalidades

- Cadastro de veículos e motoristas (com validação de placa e CPF)
- Início e encerramento de viagens
- Atualização automática do odômetro do veículo ao final da viagem
- Alerta automático de manutenção quando o veículo atinge o limite de quilometragem
- Registro de abastecimentos
- Painel de status da frota (disponível, em viagem ou em manutenção)
- Remoção de veículos e motoristas

## Tecnologias utilizadas

- Java (Swing) para a interface gráfica
- MySQL para o banco de dados
- JDBC para a comunicação entre a aplicação e o banco

## Organização do projeto

- **visao** — telas da aplicação (interface gráfica)
- **Classes_DAO** — classes responsáveis por acessar o banco de dados
- **Classes_do_banco** — classes que representam as tabelas do banco (Veículo, Motorista, Viagem, etc.)
- **Trabalho_lab_BD.sql** — script de criação do banco (tabelas, triggers e procedure)
- **Insercoes.sql** — script com dados de teste

## Regras de negócio no banco

- Uma viagem só pode ser aberta se o motorista tiver a categoria de CNH compatível com o veículo.
- Um veículo ou motorista que já está em viagem não pode ser usado em outra viagem ao mesmo tempo.
- Ao encerrar uma viagem, o sistema verifica se a quilometragem final é válida e atualiza o odômetro do veículo.
- Quando o veículo atinge o limite de quilometragem definido, um alerta de manutenção é gerado automaticamente.

## Como executar

1. Execute o script `Trabalho_lab_BD.sql` no MySQL para criar o banco de dados.
2. Execute o script `Insercoes.sql` para popular o banco com dados de teste.
3. Ajuste o usuário e a senha do banco no arquivo `Conexao.java`, se necessário.
4. Adicione o driver do MySQL (Connector/J) ao projeto.
5. Compile o projeto:
   - **Windows (PowerShell):**
     ```
     javac -cp "mysql-connector-j-9.6.0.jar;." -d bin (Get-ChildItem -Recurse -Filter *.java).FullName
     ```
   - **Linux:**
     ```
     javac -d bin $(find . -name "*.java")
     ```

6. Execute a aplicação:
   - **Windows (PowerShell):**
     ```
     java -cp "mysql-connector-j-9.6.0.jar;bin" visao.TelaPrincipal
     ```
   - **Linux:**
     ```
     java -cp bin:mysql-connector-j-9.6.0.jar visao.TelaPrincipal
     ```


## Autores
Alunos: Gabriel Pivetta Loss e João Marcello Galdino Pereira

