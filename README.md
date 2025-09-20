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



📋 Resumo Geral
Os testes verificam se o serviço de categorias está funcionando corretamente, simulando um banco de dados falso.

🔍 Teste por Teste - O que cada um testa:
1. listarTodas_DeveRetornarListaDeCategorias_QuandoExistiremCategorias()
O que testa: ✅ Se o sistema consegue mostrar todas as categorias cadastradas

Exemplo real: Como quando você abre o app de compras e vê todas as seções (Eletrônicos, Roupas, etc.)

2. buscarPorCodigo_DeveRetornarCategoria_QuandoCodigoExistir()
O que testa: ✅ Se o sistema encontra uma categoria específica pelo seu código

Exemplo real: Como quando você digita o código "1" e encontra "Eletrônicos"

3. buscarPorCodigo_DeveRetornarOptionalVazio_QuandoCodigoNaoExistir()
O que testa: ✅ Se o sistema lida corretamente quando alguém procura um código que não existe

Exemplo real: Como quando você digita o código "999" e o sistema diz "não encontrei nada"

4. salvar_DeveSalvarECriarNovaCategoria()
O que testa: ✅ Se o sistema consegue criar e salvar uma nova categoria

Exemplo real: Como quando você cadastra uma nova seção "Brinquedos" no sistema

5. atualizar_DeveAtualizarCategoriaExistente()
O que testa: ✅ Se o sistema consegue modificar uma categoria já existente

Exemplo real: Como quando você muda "Eletrônicos" para "Eletrodomésticos"

6. atualizar_DeveLancarExcecao_QuandoCategoriaNaoExistir()
O que testa: ✅ Se o sistema impede atualizações em categorias que não existem

Exemplo real: Como quando alguém tenta modificar uma seção que foi deletada e o sistema mostra um erro

