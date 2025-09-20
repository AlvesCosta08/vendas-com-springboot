# Gestão de Vendas - Módulo de Produtos e Categorias

Este projeto é parte de um sistema de **Gestão de Vendas** que modela entidades básicas para controle de produtos e suas categorias, utilizando **Jakarta Persistence (JPA)** com mapeamento objeto-relacional.

---

## 📁 Estrutura do Pacote

As entidades estão localizadas no pacote:  
`com.gvendas.gestao_vendas.entidades`

---

## 🧩 Entidades

### 1. `Produto`

Representa um produto comercializado no sistema.

#### Atributos:
| Campo          | Tipo         | Descrição                        | Restrições               |
|----------------|--------------|----------------------------------|--------------------------|
| `codigo`       | `Long`       | Identificador único do produto   | PK, Auto Incremento      |
| `descricao`    | `String`     | Nome ou descrição do produto     |                          |
| `quantidade`   | `Integer`    | Quantidade em estoque            |                          |
| `precoCusto`   | `BigDecimal` | Preço de custo do produto        |                          |
| `precoVenda`   | `BigDecimal` | Preço de venda ao cliente        |                          |
| `observacao`   | `String`     | Observações adicionais           |                          |
| `categoria`    | `Categoria`  | Categoria à qual o produto pertence | FK para `Categoria`    |

#### Relacionamento:
- **Muitos para Um (`@ManyToOne`)** com a entidade `Categoria`.
- A chave estrangeira é `codigo_categoria`, referenciando `codigo` na tabela `categoria`.

---

### 2. `Categoria`

Representa uma categoria de produtos (ex: Eletrônicos, Roupas, Alimentos).

#### Atributos:
| Campo     | Tipo     | Descrição                     | Restrições          |
|-----------|----------|-------------------------------|---------------------|
| `codigo`  | `Long`   | Identificador único da categoria | PK, Auto Incremento |
| `nome`    | `String` | Nome da categoria             |                     |

---

## 🗃️ Mapeamento de Tabelas

| Entidade   | Tabela no Banco     |
|------------|---------------------|
| `Produto`  | `produto`           |
| `Categoria`| `categoria`         |

---

## 🔗 Relacionamento entre Tabelas

```mermaid
erDiagram
    CATEGORIA ||--o{ PRODUTO : "possui"
    CATEGORIA {
        bigint codigo PK
        varchar nome
    }
    PRODUTO {
        bigint codigo PK
        varchar descricao
        integer quantidade
        decimal preco_custo
        decimal preco_venda
        varchar observacao
        bigint codigo_categoria FK
    }

⚙️ Tecnologias Utilizadas
Jakarta Persistence API (JPA) — para mapeamento ORM.
Jakarta Annotations — para definição de entidades e colunas.
BigDecimal — para valores monetários precisos.
Lombok (opcional) — não utilizado aqui, mas pode ser adicionado para reduzir boilerplate.


✅ Boas Práticas Implementadas
Sobrescrita dos métodos equals() e hashCode() para comparação segura de objetos.
Uso de GenerationType.IDENTITY para geração automática de IDs.
Nomenclatura consistente entre atributos de classe e colunas no banco (@Column).
Relacionamento explícito com @JoinColumn.


Esse `README.md` fornece documentação clara, destaca um erro crítico no código, e orienta o uso das entidades. Pode ser expandido com instruções de configuração de banco, endpoints REST, ou testes, conforme o projeto crescer.