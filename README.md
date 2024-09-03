# GRAFOS EM JAVA <img src="https://skillicons.dev/icons?i=java" alt="Java Icon" style="vertical-align: middle; height: 35px;"/>

## 1. Visão Geral

O projeto de grafos é uma aplicação que permite a manipulação e análise de grafos direcionados e não direcionados. O sistema oferece uma interface para adicionar e remover vértices e arestas, além de realizar diversas operações sobre o grafo, como cálculos de grau, verificação de conexidade, e algoritmos de caminho mínimo e árvores geradoras mínimas.

Este projeto é ideal para quem deseja entender e experimentar conceitos de teoria dos grafos e algoritmos relacionados.

## 2. Funcionalidades

Abaixo estão listadas as funcionalidades do sistema, organizadas em uma tabela para melhor visualização:

| Funcionalidade                           | Descrição                                                                                     |
|------------------------------------------|-----------------------------------------------------------------------------------------------|
| **Inserir Vértice**                      | Adiciona um novo vértice ao grafo.                                                             |
| **Inserir Aresta**                       | Adiciona uma aresta entre dois vértices com um peso específico.                               |
| **Remover Vértice**                      | Remove um vértice e todas as arestas associadas a ele.                                        |
| **Remover Aresta**                       | Remove uma aresta específica entre dois vértices.                                             |
| **Visualizar Grafo**                     | Exibe a representação do grafo, podendo ser através de texto ou visualização gráfica.          |
| **Informar Grau de um Vértice**          | Calcula e exibe o grau de um vértice específico.                                               |
| **Verificar se o Grafo é Conexo**        | Verifica se há um caminho entre todos os pares de vértices.                                    |
| **Converter para Matriz de Adjacência**  | Transforma a lista de adjacências em uma matriz de adjacências.                                |
| **Caminhamento em Amplitude (BFS)**      | Implementa o algoritmo de Busca em Largura.                                                   |
| **Caminhamento em Profundidade (DFS)**   | Implementa o algoritmo de Busca em Profundidade.                                              |
| **Caminho Mínimo (Dijkstra)**            | Implementa o algoritmo de Dijkstra para encontrar o caminho mais curto entre dois vértices.    |
| **Árvore Geradora Mínima (Prim)**        | Implementa o algoritmo de Prim para encontrar a árvore geradora mínima do grafo.              |

## 3. Tecnologias Utilizadas

- **Linguagem de Programação**: [Java](https://www.java.com/)
- **IDE**: [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- **Bibliotecas**: Nenhuma biblioteca externa foi utilizada para a implementação dos algoritmos de grafos.
- **Gerenciamento de Versão**: [Git](https://git-scm.com/)
- **Ambiente de Desenvolvimento**: [Docker](https://www.docker.com/) (se aplicável)

## 4. Links para Recursos Adicionais

- **Teoria dos Grafos**: [Wikipedia - Teoria dos Grafos](https://pt.wikipedia.org/wiki/Teoria_dos_grafos)
- **Algoritmo de Dijkstra**: [Wikipedia - Algoritmo de Dijkstra](https://pt.wikipedia.org/wiki/Algoritmo_de_Dijkstra)
- **Algoritmo de Prim**: [Wikipedia - Algoritmo de Prim](https://pt.wikipedia.org/wiki/Algoritmo_de_Prim)
- **Busca em Largura (BFS)**: [Wikipedia - Busca em Largura](https://pt.wikipedia.org/wiki/Busca_em_largura)
- **Busca em Profundidade (DFS)**: [Wikipedia - Busca em Profundidade](https://pt.wikipedia.org/wiki/Busca_em_profundidade)

## 5. Configuração e Execução

Para configurar e executar o projeto localmente, siga as etapas abaixo:

### 5.1. Pré-requisitos

- Certifique-se de ter o [Java](https://www.java.com/) instalado em sua máquina.
- Tenha o [IntelliJ IDEA](https://www.jetbrains.com/idea/) instalado para gerenciar o projeto.

### 5.2. Clonar o Repositório

```bash
git clone <URL_DO_REPOSITORIO>
cd <DIRETORIO_DO_PROJETO>
```
## 5. Compilação e Execução

### 5.3. Compilar e Executar

No IntelliJ IDEA:

1. Abra o projeto.
2. Compile o projeto.
3. Execute a classe principal para iniciar a aplicação.

## 6. Contribuição

Contribuições são bem-vindas! Se você deseja contribuir para o projeto, siga estas etapas:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature ou correção.
3. Faça suas alterações e teste.
4. Envie um pull request para revisão.

## 7. Estrutura de Pastas

Abaixo está a organização das pastas do projeto, com uma breve descrição do conteúdo de cada uma:

```bash
ProjetoGrafo/
│
├── src/
│   ├── model/
│   │   ├── entities/
│   │   │   ├── Grafo.java               # Classe que representa o grafo
│   │   │   ├── Aresta.java              # Classe que representa uma aresta no grafo
│   │   └── applicantion/
│   │       ├── Program.java             # Classe principal que executa a aplicação
│   └── view/
│       ├── BFSVisualizer.java           # Visualizador para a busca em largura (BFS)
│       ├── DFSVisualizer.java           # Visualizador para a busca em profundidade (DFS)
│       ├── DijkstraVisualizer.java      # Visualizador para o algoritmo de Dijkstra
│       ├── PrimVisualizer.java          # Visualizador para o algoritmo de Prim
│       ├── GraphVisualizer.java         # Visualizador geral do grafo
│
├── lib/
│   └── pom.xml                          # Arquivo de configuração do Maven
│
├── README.md                            # Arquivo de documentação do projeto
└── .gitignore                           # Arquivo para ignorar arquivos desnecessários no Git

```
## 8.  Link do video explicativo sobre o projeto
