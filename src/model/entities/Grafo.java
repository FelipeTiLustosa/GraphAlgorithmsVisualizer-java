package model.entities;

import java.util.*;

public class Grafo {

    private Map<Integer, List<Aresta>> adjacencias;
    private int numVertices;

    public Grafo() {
        this.adjacencias = new HashMap<>();
        this.numVertices = 0;
    }

    // Método para inserir um vértice
    public void inserirVertice(int id) {
        if (!adjacencias.containsKey(id)) {
            adjacencias.put(id, new ArrayList<>());
            numVertices++;
            System.out.println("Vértice " + id + " inserido com sucesso.");
        } else {
            System.out.println("Vértice " + id + " já existe.");
        }
    }

    // Método para inserir uma aresta direcionada
    public void inserirArestaDirecionada(int origem, int destino, int peso) {
        if (!adjacencias.containsKey(origem) || !adjacencias.containsKey(destino)) {
            System.out.println("Um ou ambos os vértices não existem.");
            return;
        }
        try {
            adjacencias.get(origem).add(new Aresta(destino, peso));  // Aresta unidirecional
            System.out.println("Aresta direcionada de " + origem + " para " + destino + " com peso " + peso + " inserida com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao inserir aresta direcionada: " + e.getMessage());
        }
    }

    // Método para inserir uma aresta não direcionada
    public void inserirArestaNaoDirecionada(int origem, int destino, int peso) {
        if (!adjacencias.containsKey(origem) || !adjacencias.containsKey(destino)) {
            System.out.println("Um ou ambos os vértices não existem.");
            return;
        }
        try {
            adjacencias.get(origem).add(new Aresta(destino, peso));  // Aresta de origem para destino
            adjacencias.get(destino).add(new Aresta(origem, peso));  // Aresta de destino para origem
            System.out.println("Aresta não direcionada entre " + origem + " e " + destino + " com peso " + peso + " inserida com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao inserir aresta não direcionada: " + e.getMessage());
        }
    }

    // Método para remover um vértice
    public void removerVertice(int vertice) {
        if (!adjacencias.containsKey(vertice)) {
            System.out.println("Vértice " + vertice + " não existe.");
            return;
        }

        // Remove todas as arestas associadas ao vértice
        for (Integer v : adjacencias.keySet()) {
            adjacencias.get(v).removeIf(aresta -> aresta.getDestino() == vertice);
        }

        // Remove o vértice
        adjacencias.remove(vertice);
        numVertices--;
        System.out.println("Vértice " + vertice + " removido com sucesso.");
    }

    // Método para remover uma aresta
    public void removerAresta(int origem, int destino) {
        if (!adjacencias.containsKey(origem) || !adjacencias.containsKey(destino)) {
            System.out.println("Um ou ambos os vértices não existem.");
            return;
        }
        try {
            adjacencias.get(origem).removeIf(aresta -> aresta.getDestino() == destino);
            adjacencias.get(destino).removeIf(aresta -> aresta.getDestino() == origem);
            System.out.println("Aresta entre " + origem + " e " + destino + " removida com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao remover aresta: " + e.getMessage());
        }
    }

    // Método para exibir o grafo (lista de adjacências)
    public void exibirGrafo() {
        if (adjacencias.isEmpty()) {
            System.out.println("O grafo está vazio.");
            return;
        }
        System.out.println("Representação do Grafo:");
        for (Map.Entry<Integer, List<Aresta>> entry : adjacencias.entrySet()) {
            System.out.print("Vértice " + entry.getKey() + ": ");
            for (Aresta aresta : entry.getValue()) {
                System.out.print("(Destino: " + aresta.getDestino() + ", Peso: " + aresta.getPeso() + ") ");
            }
            System.out.println();
        }
    }

    // Método para informar o grau de um vértice
    public void informarGrau(int vertice) {
        if (!adjacencias.containsKey(vertice)) {
            System.out.println("Vértice " + vertice + " não existe.");
            return;
        }
        int grau = adjacencias.get(vertice).size();
        System.out.println("Grau do vértice " + vertice + " é: " + grau);
    }

    // Método para verificar se o grafo é conexo
    public boolean verificarConectividade() {
        if (adjacencias.isEmpty()) {
            return true; // Um grafo vazio é considerado conexo
        }

        Set<Integer> visitados = new HashSet<>();
        Integer verticeInicial = adjacencias.keySet().iterator().next(); // Pega qualquer vértice para começar a DFS
        dfs(verticeInicial, visitados);

        // Verifica se todos os vértices foram visitados
        if (visitados.size() != numVertices) {
            System.out.println("O grafo não é conexo.");
            return false;
        }

        // Adiciona uma verificação adicional para lidar com grafos direcionados
        // Esta verificação faz uma DFS invertida para garantir que o grafo é fortemente conexo
        Set<Integer> visitadosReverso = new HashSet<>();
        dfsReverso(verticeInicial, visitadosReverso);

        if (visitadosReverso.size() != numVertices) {
            System.out.println("O grafo não é fortemente conexo.");
            return false;
        }

        System.out.println("O grafo é conexo.");
        return true;
    }

    // Método auxiliar para realizar a DFS (Busca em Profundidade)
    private void dfs(int vertice, Set<Integer> visitados) {
        visitados.add(vertice);

        for (Aresta aresta : adjacencias.get(vertice)) {
            int destino = aresta.getDestino();
            if (!visitados.contains(destino)) {
                dfs(destino, visitados);
            }
        }
    }

    // Método auxiliar para realizar a DFS em sentido reverso (para grafos direcionados)
    private void dfsReverso(int vertice, Set<Integer> visitados) {
        visitados.add(vertice);

        for (Map.Entry<Integer, List<Aresta>> entry : adjacencias.entrySet()) {
            for (Aresta aresta : entry.getValue()) {
                if (aresta.getDestino() == vertice && !visitados.contains(entry.getKey())) {
                    dfsReverso(entry.getKey(), visitados);
                }
            }
        }
    }


    // Método para obter o número de vértices
    public int getNumVertices() {
        return numVertices;
    }

    public Map<Integer, List<Aresta>> getAdjacencias() {
        return adjacencias;
    }

    public Map<Integer, String> getVertices() {
        Map<Integer, String> vertices = new HashMap<>();
        for (Integer id : adjacencias.keySet()) {
            vertices.put(id, id.toString()); // Apenas retorna o ID como rótulo
        }
        return vertices;
    }

    // Método para converter a lista de adjacências para matriz de adjacência
    public void converterParaMatrizAdjacencia(boolean ehDirecionado) {
        // Cria um mapeamento dos IDs dos vértices para índices
        Map<Integer, Integer> indice = new HashMap<>();
        int contador = 0;
        for (Integer v : adjacencias.keySet()) {
            indice.put(v, contador++);
        }

        int numVertices = adjacencias.size();
        int[][] matriz = new int[numVertices][numVertices];

        // Preenche a matriz com os pesos das arestas
        for (Map.Entry<Integer, List<Aresta>> entry : adjacencias.entrySet()) {
            int i = indice.get(entry.getKey()); // Obtém o índice do vértice
            for (Aresta aresta : entry.getValue()) {
                int j = indice.get(aresta.getDestino()); // Obtém o índice do destino
                matriz[i][j] = aresta.getPeso();
                if (!ehDirecionado) {
                    matriz[j][i] = aresta.getPeso(); // Se não direcionado, adicione também o caminho reverso
                }
            }
        }

        // Exibe a matriz de adjacência com formatação
        System.out.println("Matriz de Adjacência:");
        System.out.print("   "); // Espaço para alinhar o canto superior esquerdo
        for (int v : adjacencias.keySet()) {
            System.out.printf("%4d", v); // Exibe o número do vértice com espaçamento
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            // Exibe o rótulo da linha (número do vértice)
            System.out.printf("%2d ", getVerticePorIndice(indice, i));
            for (int j = 0; j < numVertices; j++) {
                System.out.printf("%4d", matriz[i][j]); // Exibe o valor com espaçamento
            }
            System.out.println();
        }
    }

    // Método auxiliar para obter o vértice original pelo índice
    private int getVerticePorIndice(Map<Integer, Integer> indice, int valor) {
        for (Map.Entry<Integer, Integer> entry : indice.entrySet()) {
            if (entry.getValue() == valor) {
                return entry.getKey();
            }
        }
        return -1; // Caso não seja encontrado
    }


}
