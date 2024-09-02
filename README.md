# Projeto de Implementação de Grafos

Este projeto implementa um grafo em Java, permitindo a manipulação de vértices e arestas, bem como a execução de algoritmos clássicos de grafos. O projeto visa fornecer uma estrutura clara e bem organizada para quem deseja explorar a teoria dos grafos através de código.

## Funcionalidades do Sistema

### Inserir Vértice
Adiciona um novo vértice ao grafo, criando um novo nó sem arestas conectadas inicialmente.

### Inserir Aresta
Adiciona uma aresta entre dois vértices, com um peso específico, representando a conexão e a relação entre esses vértices.

### Remover Vértice
Remove um vértice existente no grafo e todas as arestas associadas a ele, mantendo a integridade do grafo.

### Remover Aresta
Remove uma aresta específica entre dois vértices, mantendo os vértices conectados.

### Visualizar Grafo
Exibe a representação do grafo na forma de uma lista de adjacências, ou pode ser adaptado para uma visualização gráfica.

### Informar Grau de um Vértice
Calcula e exibe o grau de um vértice específico, indicando o número de conexões que ele possui.

### Verificar se o Grafo é Conexo
Verifica se há um caminho entre todos os pares de vértices do grafo, determinando se ele é conexo.

### Converter para Matriz de Adjacência
Transforma a lista de adjacências do grafo em uma matriz de adjacências, uma forma alternativa de representar o grafo.

### Caminhamento em Amplitude (BFS)
Implementa o algoritmo de Busca em Largura (BFS) para percorrer o grafo, visitando os vértices camada por camada.

### Caminhamento em Profundidade (DFS)
Implementa o algoritmo de Busca em Profundidade (DFS) para percorrer o grafo, explorando o máximo possível ao seguir cada caminho.

### Caminho Mínimo (Dijkstra)
Implementa o algoritmo de Dijkstra para encontrar o caminho mais curto entre dois vértices, considerando pesos nas arestas.

### Árvore Geradora Mínima (Prim)
Implementa o algoritmo de Prim para encontrar a árvore geradora mínima do grafo, conectando todos os vértices com o menor custo total possível.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal utilizada no projeto.
- **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências.
- **IntelliJ IDEA**: IDE recomendada para desenvolvimento e execução do projeto.

### Bibliotecas e Dependências

Este projeto utiliza o Maven para gerenciar suas dependências. Certifique-se de que as seguintes dependências estejam presentes no arquivo `pom.xml`:

```xml
 <!-- JGraphT para grafos -->
        <dependency>
            <groupId>org.jgrapht</groupId>
            <artifactId>jgrapht-core</artifactId>
            <version>1.5.1</version>
        </dependency>
        <dependency>
            <groupId>org.jgrapht</groupId>
            <artifactId>jgrapht-ext</artifactId>
            <version>1.5.1</version>
        </dependency>

        <!-- JGraphX para visualização gráfica -->
        <dependency>
            <groupId>com.mxgraph</groupId>
            <artifactId>jgraphx</artifactId>
            <version>4.0.6</version>
        </dependency>
       <!-- <dependency>
            <groupId>com.mxgraph</groupId>
            <artifactId>mxgraph</artifactId>
            <version>4.0.1</version>
        </dependency> -->

