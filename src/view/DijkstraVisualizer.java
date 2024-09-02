package view;

import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import model.entities.Aresta;
import model.entities.Grafo;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraVisualizer extends JFrame {
    private Grafo grafo;
    private mxGraph graph;
    private Object parent;
    private Map<Integer, Object> vertexMap;
    private Map<Integer, Integer> predecessores; // Armazenar predecessores para reconstruir o caminho mais curto
    private JTextField destinationField;

    public DijkstraVisualizer(Grafo grafo) {
        this.grafo = grafo;
        this.graph = new mxGraph();
        this.parent = graph.getDefaultParent();
        this.vertexMap = new HashMap<>();
        this.predecessores = new HashMap<>();
        initialize();
    }

    private void initialize() {
        setTitle("Visualização de Caminho Mínimo (Dijkstra)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        add(graphComponent, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        add(controlPanel, BorderLayout.SOUTH);

        JLabel destinationLabel = new JLabel("Destino:");
        controlPanel.add(destinationLabel);

        destinationField = new JTextField(5);
        controlPanel.add(destinationField);

        JButton runButton = new JButton("Executar Dijkstra");
        runButton.addActionListener(e -> {
            try {
                int destino = Integer.parseInt(destinationField.getText());
                dijkstra(1, destino); // Execute Dijkstra a partir do nó 1
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um número válido para o destino.");
            }
        });
        controlPanel.add(runButton);

        graph.getModel().beginUpdate();
        try {
            // Adiciona vértices ao gráfico
            Map<Integer, String> vertices = grafo.getVertices();
            for (Integer id : vertices.keySet()) {
                Object vertex = graph.insertVertex(parent, null, id.toString(), 20, 20, 50, 50, "shape=ellipse;fillColor=lightblue");
                vertexMap.put(id, vertex);
            }

            // Adiciona arestas ao gráfico
            grafo.getAdjacencias().forEach((origem, arestas) -> {
                Object origemVertex = vertexMap.get(origem);
                for (Aresta aresta : arestas) {
                    Object destinoVertex = vertexMap.get(aresta.getDestino());
                    if (destinoVertex != null) {
                        graph.insertEdge(parent, null, aresta.getPeso(), origemVertex, destinoVertex, "strokeColor=black");
                    }
                }
            });
        } finally {
            graph.getModel().endUpdate();
        }

        // Layout orgânico para melhor centralização
        mxGraphLayout layout = new mxOrganicLayout(graph);
        layout.execute(graph.getDefaultParent());

        setVisible(true);
    }

    public void dijkstra(int startVertex, int endVertex) {
        resetHighlight();
        Map<Integer, Integer> distancias = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> distancias.get(a) - distancias.get(b));

        // Inicializa distâncias
        grafo.getVertices().keySet().forEach(v -> distancias.put(v, Integer.MAX_VALUE));
        distancias.put(startVertex, 0);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int atual = queue.poll();
            highlightVertex(atual, Color.GREEN);

            for (model.entities.Aresta aresta : grafo.getAdjacencias().get(atual)) {
                int destino = aresta.getDestino();
                int novaDistancia = distancias.get(atual) + aresta.getPeso();

                if (novaDistancia < distancias.get(destino)) {
                    distancias.put(destino, novaDistancia);
                    predecessores.put(destino, atual);
                    queue.add(destino);
                    highlightEdge(atual, destino, Color.RED);
                }
            }
        }

        System.out.println("Distâncias mais curtas do nó " + startVertex + ":");
        distancias.forEach((nodo, distancia) -> System.out.println("Nó " + nodo + ": " + distancia));

        // Destacar o caminho mínimo a partir do vértice inicial
        highlightShortestPath(startVertex, endVertex);
        System.out.println("Caminho mais curto do nó " + startVertex + " ao nó " + endVertex + ":");
        System.out.println(getShortestPath(startVertex, endVertex));

        System.out.println("Dijkstra concluído.");
    }

    private void highlightVertex(int vertexId, Color color) {
        Object cell = vertexMap.get(vertexId);
        if (cell != null) {
            graph.getModel().beginUpdate();
            try {
                graph.setCellStyle("fillColor=" + getColorString(color), new Object[]{cell});
            } finally {
                graph.getModel().endUpdate();
            }
        }
    }

    private void highlightEdge(int origem, int destino, Color color) {
        Object origemVertex = vertexMap.get(origem);
        Object destinoVertex = vertexMap.get(destino);
        if (origemVertex != null && destinoVertex != null) {
            graph.getModel().beginUpdate();
            try {
                Object[] edges = graph.getEdgesBetween(origemVertex, destinoVertex);
                for (Object edge : edges) {
                    graph.setCellStyle("strokeColor=" + getColorString(color), new Object[]{edge});
                }
            } finally {
                graph.getModel().endUpdate();
            }
        }
    }

    private void highlightShortestPath(int startVertex, int endVertex) {
        // Reconstruir e destacar o caminho mais curto
        int atual = endVertex;
        while (atual != startVertex) {
            int pred = predecessores.getOrDefault(atual, startVertex);
            highlightEdge(pred, atual, Color.BLUE);
            atual = pred;
        }
    }

    private String getShortestPath(int startVertex, int endVertex) {
        StringBuilder path = new StringBuilder();
        int atual = endVertex;
        while (atual != startVertex) {
            path.insert(0, " -> " + atual);
            atual = predecessores.getOrDefault(atual, startVertex);
        }
        path.insert(0, startVertex);
        return path.toString();
    }

    private void resetHighlight() {
        graph.getModel().beginUpdate();
        try {
            for (Object vertex : vertexMap.values()) {
                graph.setCellStyle("fillColor=lightblue", new Object[]{vertex});
            }
            for (Object edge : graph.getChildCells(graph.getDefaultParent())) {
                if (graph.getModel().isEdge(edge)) {
                    graph.setCellStyle("strokeColor=black", new Object[]{edge});
                }
            }
        } finally {
            graph.getModel().endUpdate();
        }
    }

    private String getColorString(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.inserirVertice(1);
        grafo.inserirVertice(2);
        grafo.inserirVertice(3);
        grafo.inserirVertice(4);
        grafo.inserirVertice(5);
        grafo.inserirVertice(6);
        grafo.inserirVertice(7);
        grafo.inserirVertice(8);
        grafo.inserirVertice(9);

        grafo.inserirArestaDirecionada(1, 2, 0);
        grafo.inserirArestaDirecionada(1, 3, 1);
        grafo.inserirArestaDirecionada(2, 6, 20);
        grafo.inserirArestaDirecionada(6, 5, 5);
        grafo.inserirArestaDirecionada(5, 7, 2);
        grafo.inserirArestaDirecionada(5, 8, 0);
        grafo.inserirArestaDirecionada(6, 9, 0);
        grafo.inserirArestaDirecionada(3, 4, 0);
        grafo.inserirArestaDirecionada(3, 4, 0);
        grafo.inserirArestaDirecionada(4, 5, 0);

        SwingUtilities.invokeLater(() -> new DijkstraVisualizer(grafo));
    }
}
