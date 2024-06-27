# Afrofinacial Bank

Sistema bancário usando Java 17 e Spring Boot, com PostgreSQL como banco de dados. O sistema terá como funcionalidades principais a criação e gestão de contas (corrente ou pagamento), além de permitir a realização de transações financeiras.
Projeto final Afrocódigos realizado pela [Olabi](https://www.olabi.org.br/) em parceria com a [J.P. Morgan](https://www.jpmorgan.com.br/pt/about-us).


## Arquitetura do Sistema

O projeto seguirá os princípios da orientação a objetos (OO), utilizando encapsulamento, herança e polimorfismo para organizar as diferentes partes do sistema. Vamos adotar o padrão de projeto MVC (Model-View-Controller) para separar a lógica de negócios, dados e apresentação

## Ferramentas

- [Java 17](https://www.oracle.com/br/java/technologies/downloads/#java17)
- [Spring Boot 3](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL 16](https://www.postgresql.org/download/)
- [Insomnia](https://insomnia.rest/download)
- [IDE](https://www.jetbrains.com/idea/download/)

## Como testar

### Testando a API online

Para testar a API, acesse [aqui](http://ec2-177-71-143-227.sa-east-1.compute.amazonaws.com:8080/swagger-ui/index.html#/).

Talvez o servidor não esteja conectado no momento de acesso. Nesse caso, por favor, abra uma issue.

### Testando a API localmente

Os passos necessários para testar localmente são:

- Instalar as ferramentas necessárias
- Clonar o projeto
- Executar o arquivo [AfrobankApplication.java](src/main/java/com/br/afrofinancialbank/afroban/AfrobankApplication) na IDE
- Testar as rotas da API (rotas listadas [aqui](#rotas-da-api) e json com requisições disponível [aqui](/rotas_insomnia/))) na IDE
- Testar as rotas da API (rotas listadas [aqui](#rotas-da-api) e json com requisições disponível [aqui](/rotas_insomnia/))) na IDE
- Testar as rotas da API (rotas listadas [aqui](#rotas-da-api) e json com requisições disponível [aqui](/rotas_insomnia/))
- 
## Estrutura do Projeto

```plaintext
📂banco-api/
│
├──📂 src/
│   ├──📂 main/
│   │   ├── 📂java/
│   │   │   └── 📂com/
│   │   │       └── 📂banco/
│   │   │           ├── 📂config/
│   │   │           │   └── DatabaseInitializer.java
│   │   │           ├── 📂controller/      # Controladores REST
│   │   │           │   ├── ClienteController.java
│   │   │           │   ├── ContaController.java
│   │   │           │   └── TransacaoController.java
│   │   │           ├── 📂dto/
│   │   │           │   ├── DepositoRequest.java
│   │   │           │   ├── PagamentoRequest.java
│   │   │           │   ├── SaqueRequest.java
│   │   │           │   ├── SaldoResponse.java
│   │   │           │   └── TransferenciaRequest.java
│   │   │           ├──📂 model/           # Classes de modelo (entidades)
│   │   │           │   ├── Cliente.java
│   │   │           │   ├── Conta.java
│   │   │           │   ├── ContaCorrente.java
│   │   │           │   ├── ContaPagamento.java
│   │   │           │   └── Transacao.java
│   │   │           ├── 📂repository/      # Repositórios JPA
│   │   │           │   ├── ClienteRepository.java
│   │   │           │   ├── ContaRepository.java
│   │   │           │   └── TransacaoRepository.java
│   │   │           ├──📂 service/         # Serviços de negócio
│   │   │           │   ├── ClienteService.java
│   │   │           │   ├── ContaService.java
│   │   │           │   └── TransacaoService.java
│   │   │           └── 📂BancoApiApplication.java # Classe principal do Spring Boot
│   │   └──📂 resources/                    # Recursos de configuração
│   │       ├── 📄application.properties    # Configuração do Spring
│   │       └── 📄schema.sql                # Script SQL para inicializar o banco de dados
│   └──📂 test/                             # Testes unitários
│       ├── 📂java/
│       │   └── 📂com/
│       │       └──📂 banco/
│       │           ├── ClienteControllerTests.java
│       │           ├── ContaControllerTests.java
│       │           └── TransacaoControllerTests.java
│       └──📂 resources/
└── 📄pom.xml                               # Configuração do Maven

```
- **DataInitializer.java** - Inicializa e conecta ao banco de dados
- **ClienteController.java**  - Controller da classe Cliente. Relaciona as rotas de requisições HTTP relacionadas à classe Cliente.
- **ContaController.java**  - Controller da classe Conta. Relaciona as rotas de requisições HTTP relacionadas à classe Conta.
- **TransacaoController.java**  - Controller da classe Transacao. Relaciona as rotas de requisições HTTP relacionadas à classe Transacao.
- **ClienteException.java**  - Tratamento de exceções da classe Cliente.
- **ContaException.java**  - Tratamento de exceções da classe Conta.
- **Cliente.java**  - Classe da entidade Cliente.
- **Conta.java** - Classe da entidade Conta.
- **ContaCorrente.java** - Classe da entidade ContaCorrente. Subclasse da classe Conta.
- **ContaPagamento.java**  - Classe da entidade ContaPagamento. Subclasse da classe Conta.
- **Transacao.java**  - Classe da entidade Transacao.
- **ClienteRepository.java** - Repositório de dados de instâncias da classe Cliente.
- **ContaRepository.java** - Repositório de dados de instâncias da classe Conta.
- **TransacaoRepository.java**  - Repositório de dados de instâncias da classe Transacao.
- **ClienteService.java** - Implementação das regras de negócio relacionadas a entidade Cliente.
- **ContaService.java**  - Implementação das regras de negócio relacionadas a entidade Conta.
- **BancoFicticioApplication.java**  - Aplicação Spring Boot.

## Rotas da API

## Endpoints de Clientes
| Método | URL | Ações |
| ------ | --- | ----- |
| POST | /clientes/cadastrar | Cadastra um novo cliente. |
| POST | /clientes/login | Realiza login de um cliente. |
| PUT | /clientes/{id} | Atualiza os dados de um cliente específico. |
| GET | /clientes/{cpf} | Obtém os detalhes de um cliente pelo CPF. |
| GET | /clientes/{id}/saldo | Consulta o saldo de um cliente específico. |
| GET | /clientes | Lista todos os clientes. |

## Endpoints de Contas
| Método | URL | Ações |
| ------ | --- | ----- |
| POST | /contas/pagamento/pix | Realiza um pagamento via Pix de uma conta Pagamento. |
| POST | /contas/corrente/pix | Realiza um pagamento via Pix de uma conta Corrente. |
| POST | /contas/saque | Realiza um saque de uma conta. |
| POST | /contas/transferencia | Realiza uma transferência entre contas. |
| POST | /contas/deposito | Realiza um depósito em uma conta. |
| POST | /contas/pagamento | Realiza um pagamento de uma conta. |

## Schema de Cliente

### Cliente
| Campo | Tipo | Descrição |
| ----- | ---- | --------- |
| id | Long | Identificador único do cliente. |
| nome | String | Nome do cliente. |
| cpf | String | CPF do cliente. |
| email | String | E-mail do cliente. |
| endereco | String | Endereço do cliente. |
| rendaSalarial | Double | Renda salarial do cliente. |
| senha | String | Senha do cliente. |
| contas | List<Conta> | Lista de contas associadas ao cliente. |

### Conta
| Campo | Tipo | Descrição |
| ----- | ---- | --------- |
| id | Long | Identificador único da conta. |
| cliente | Cliente | Cliente associado à conta. |
| saldo | Double | Saldo da conta. |
| tipo | String | Tipo de conta (Corrente ou Pagamento). |
| transacoes | List<Transacao> | Lista de transações da conta. |

### ContaCorrente
| Campo | Tipo | Descrição |
| ----- | ---- | --------- |
| chequeEspecial | Double | Limite de cheque especial da conta corrente. |

### ContaPagamento
| Campo | Tipo | Descrição |
| ----- | ---- | --------- |
| limiteTransferencia | Double | Limite de transferência da conta Pagamento. |

### Transacao
| Campo | Tipo | Descrição |
| ----- | ---- | --------- |
| id | Long | Identificador único da transação. |
| conta | Conta | Conta associada à transação. |
| valor | Double | Valor da transação. |
| tipo | String | Tipo da transação (Pagamento, Pix, Saque, etc). |
| data | LocalDateTime | Data e hora da transação. |

### SaldoResponse
| Campo | Tipo | Descrição |
| ----- | ---- | --------- |
| nomeCliente | String | Nome do cliente. |
| saldo | Double | Saldo da conta. |
| tipoConta | String | Tipo da conta. |
| limiteChequeEspecial | Double | Limite de cheque especial utilizado. |

### Exemplo de Resposta - SaldoResponse
```json
{
  "nomeCliente": "João Silva",
  "saldo": 1500.00,
  "tipoConta": "Corrente",
  "limiteChequeEspecial": 1000.00
}
```

### Exemplo de Resposta - Cliente
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "12345678901",
  "email": "joao.silva@example.com",
  "endereco": "Rua Exemplo, 123",
  "rendaSalarial": 5000.00,
  "senha": "senha123",
  "contas": [
    {
      "id": 1,
      "saldo": 1500.00,
      "tipo": "Corrente",
      "chequeEspecial": 1000.00,
      "transacoes": []
    }
  ]
}
```

## Exemplo de Requisição - Cadastrar Cliente
### Request
```json
{
  "nome": "João Silva",
  "cpf": "12345678901",
  "email": "joao.silva@example.com",
  "endereco": "Rua Exemplo, 123",
  "rendaSalarial": 5000.00,
  "senha": "senha123"
}
```
### Response
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "12345678901",
  "email": "joao.silva@example.com",
  "endereco": "Rua Exemplo, 123",
  "rendaSalarial": 5000.00,
  "senha": "senha123",
  "contas": [
    {
      "id": 1,
      "saldo": 0.00,
      "tipo": "Corrente",
      "chequeEspecial": 1000.00,
      "transacoes": []
    }
  ]
}
```
