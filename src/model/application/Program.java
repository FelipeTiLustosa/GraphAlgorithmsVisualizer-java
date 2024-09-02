package model.application;

import model.entities.Grafo;
import view.*;

import javax.swing.*;
import java.util.Scanner;

public class Program {
    private static final Scanner scanner = new Scanner(System.in);
    private static Grafo grafo = new Grafo();
    private static boolean ehDirecionado = false; // Variável de instância para o tipo de grafo

    public static void main(String[] args) {
        while (true) {
            exibirMenuPrincipal();
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println(
                "\033[0m╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║   \033[1;31mO\033[0m \033[1;32m■ ■                    \033[0m║                                                                                ║");
        System.out.println(
                "\033[0m║   \033[1;32m■ ■\033[0m                      ║                                                                                ║");
        System.out.println(
                "║   \033[1;32m■ ■ ■\033[0m INSTITUTO FEDERAL  ║      SISTEMA DE GERENCIAMENTO  DE GRAFOS                                       ║");
        System.out.println(
                "║   \033[1;32m■ ■\033[0m   Piauí              ║                                                                                ║");
        System.out.println(
                "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(
                "║                                                                                                             ║");
        System.out.println(
                "║                                        Escolha uma opção:                                                   ║");
        System.out.println(
                "║                                                                                                             ║");
        System.out.println(
                "\033[0m║\033[1;32m                         1. Inserir Vértice                                                                  \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         2. Inserir Aresta                                                                   \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         3. Remover Vértice                                                                  \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         4. Remover Aresta                                                                   \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         5. Visualizar Grafo                                                                 \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         6. Informar Grau de um Vértice                                                      \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         7. Verificar se o Grafo é Conexo                                                    \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         8. Converter para Matriz de Adjacência                                              \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         9. Caminhamento em Amplitude (BFS)                                                  \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                        10. Caminhamento em Profundidade (DFS)                                               \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                        11. Caminho Mínimo (Dijkstra)                                                        \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                        12. Árvore Geradora Mínima (Prim)                                                    \033[0m║");
        System.out.println(
                "\033[0m║\033[1;32m                         0. Sair do Sistema                                                                  \033[0m║\033[0m");
        System.out.println(
                "║                                                                                                             \033[0m║");
        System.out.println(
                "╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
        System.out.print("\033[0m Digite a opção escolhida: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");

        switch (escolha) {
            case 1:
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.out.print(" Quantos vértices deseja inserir? ");
                int qtdVertices = scanner.nextInt();
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");

                for (int i = 0; i < qtdVertices; i++) {
                    System.out.print("Insira o ID do vértice " + (i + 1) + ": ");
                    int idVertice = scanner.nextInt();
                    grafo.inserirVertice(idVertice);
                }
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                break;

            case 2:
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.out.print(" Quantas arestas deseja inserir? ");
                int qtdArestas = scanner.nextInt();
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.out.print("O grafo é direcionado? (S/N): ");
                char escolhaDirecionado = scanner.next().toUpperCase().charAt(0);
                ehDirecionado = (escolhaDirecionado == 'S');

                for (int i = 0; i < qtdArestas; i++) {
                    System.out.print("Insira o vértice de origem da aresta " + (i + 1) + ": ");
                    int origem = scanner.nextInt();

                    System.out.print("Insira o vértice de destino da aresta " + (i + 1) + ": ");
                    int destino = scanner.nextInt();

                    System.out.print("Insira o peso da aresta " + (i + 1) + ": ");
                    int peso = scanner.nextInt();

                    if (ehDirecionado) {
                        grafo.inserirArestaDirecionada(origem, destino, peso);
                    } else {
                        grafo.inserirArestaNaoDirecionada(origem, destino, peso);
                    }
                }
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                break;

            case 3:
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.out.print(" Quantos vértices deseja remover? ");
                int qtdRemoverVertices = scanner.nextInt();
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                for (int i = 0; i < qtdRemoverVertices; i++) {
                    System.out.print("Insira o ID do vértice que deseja remover: ");
                    int idVerticeRemover = scanner.nextInt();
                    grafo.removerVertice(idVerticeRemover);
                }
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                break;

            case 4:
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.out.print("Quantas arestas deseja remover? ");
                int qtdRemoverArestas = scanner.nextInt();
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                for (int i = 0; i < qtdRemoverArestas; i++) {
                    System.out.print("Insira o vértice de origem da aresta a ser removida: ");
                    int origemRemover = scanner.nextInt();

                    System.out.print("Insira o vértice de destino da aresta a ser removida: ");
                    int destinoRemover = scanner.nextInt();

                    grafo.removerAresta(origemRemover, destinoRemover);
                }
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                break;

            case 5:
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                grafo.exibirGrafo();
                SwingUtilities.invokeLater(() -> new GraphVisualizer(grafo));
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                break;

            case 6:System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.out.print(" Digite o vértice: ");
                int vertice = scanner.nextInt();
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                grafo.informarGrau(vertice);
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                break;

            case 7:
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                grafo.verificarConectividade();
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                break;

            case 8:
                grafo.converterParaMatrizAdjacencia(ehDirecionado);
                break;

            case 9:
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.out.print(" Digite o vértice inicial para BFS: ");
                int verticee = scanner.nextInt();
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                BFSVisualizer visualizer = new BFSVisualizer(grafo);
                visualizer.bfs(verticee);
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                break;

            case 10:
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.out.print(" Digite o vértice inicial para DFS: ");
                int verticeDFS = scanner.nextInt();
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                DFSVisualizer dfsVisualizer = new DFSVisualizer(grafo);
                dfsVisualizer.dfs(verticeDFS);
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                break;

            case 11:
                // Implementar Caminho Mínimo (Dijkstra)
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.out.print(" Digite o vértice inicial: ");
                int verticeInicial = scanner.nextInt();
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.out.print(" Digite o vértice de destino: ");
                int verticeDestino = scanner.nextInt();
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                DijkstraVisualizer dijkstraVisualizer = new DijkstraVisualizer(grafo);
                dijkstraVisualizer.dijkstra(verticeInicial, verticeDestino);
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                break;

            case 12:
                // Implementar Árvore Geradora Mínima (Prim)
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.out.print("Digite o vértice inicial: ");
                int verticeInicialPrim = scanner.nextInt();
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                PrimVisualizer primVisualizer = new PrimVisualizer(grafo);
                primVisualizer.prim(verticeInicialPrim);
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                break;

            case 0:

                System.out.println(" Saindo do Sistema. Até logo!");
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.exit(0);
                break;

            default:
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
                System.out.println("Opção inválida.");
                System.out.println("\033[1;36m╠-------------------------------------------------------------------------------------------------------------╣\033[0m");
        }

    }

}
