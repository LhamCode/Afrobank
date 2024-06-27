# Afrofinacial Bank

Sistema bancário usando Java 17 e Spring Boot, com PostgreSQL como banco de dados. O sistema terá como funcionalidades principais a criação e gestão de contas (corrente ou pagamento), além de permitir a realização de transações financeiras.


## Arquitetura do Sistema

O projeto seguirá os princípios da orientação a objetos (OO), utilizando encapsulamento, herança e polimorfismo para organizar as diferentes partes do sistema. Vamos adotar o padrão de projeto MVC (Model-View-Controller) para separar a lógica de negócios, dados e apresentação

## Estrutura do Projeto

```plaintext
📂banco-api/
│
├──📂 src/
│   ├──📂 main/
│   │   ├── 📂java/
│   │   │   └── 📂com/
│   │   │       └── 📂banco/
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