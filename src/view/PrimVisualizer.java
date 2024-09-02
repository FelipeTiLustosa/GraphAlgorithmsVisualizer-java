package view;

import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import model.entities.Grafo;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;

public class PrimVisualizer extends JFrame {
    private Grafo grafo;
    private mxGraph graph;
    private Object parent;
    private Map<Integer, Object> vertexMap;

    public PrimVisualizer(Grafo grafo) {
        this.grafo = grafo;
        this.graph = new mxGraph();
        this.parent = graph.getDefaultParent();
        this.vertexMap = new HashMap<>();
        initialize();
    }

    private void initialize() {
        setTitle("Visualização do Prim");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        add(graphComponent, BorderLayout.CENTER);

        graph.getModel().beginUpdate();
        try {
            Map<Integer, String> vertices = grafo.getVertices();
            for (Integer id : vertices.keySet()) {
                Object vertex = graph.insertVertex(parent, null, id.toString(), 20, 20, 50, 50, "shape=ellipse;fillColor=lightblue");
                vertexMap.put(id, vertex);
            }

            grafo.getAdjacencias().forEach((origem, arestas) -> {
                Object origemVertex = vertexMap.get(origem);
                for (model.entities.Aresta aresta : arestas) {
                    Object destinoVertex = vertexMap.get(aresta.getDestino());
                    if (destinoVertex != null) {
                        graph.insertEdge(parent, null, aresta.getPeso(), origemVertex, destinoVertex);
                    }
                }
            });
        } finally {
            graph.getModel().endUpdate();
        }

        // Use organic layout for better centralization
        mxGraphLayout layout = new mxOrganicLayout(graph);
        layout.execute(graph.getDefaultParent());

        setVisible(true);
    }

    public void prim(int startVertex) {
        resetHighlight();

        PriorityQueue<PrimEdge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.peso));
        Map<Integer, Boolean> inMST = new HashMap<>();
        grafo.getVertices().keySet().forEach(v -> inMST.put(v, false));

        pq.add(new PrimEdge(-1, startVertex, 0));

        int totalWeight = 0; // Variável para acumular o peso total das arestas

        while (!pq.isEmpty()) {
            PrimEdge edge = pq.poll();
            int vertex = edge.destino;

            if (!inMST.get(vertex)) {
                inMST.put(vertex, true);
                if (edge.origem != -1) {
                    System.out.println("Aresta adicionada à árvore: " + edge.origem + " -> " + vertex + " (peso: " + edge.peso + ")");
                    highlightEdge(edge.origem, vertex, Color.RED);
                    totalWeight += edge.peso; // Adiciona o peso da aresta ao total
                }

                highlightVertex(vertex, Color.YELLOW); // Destaca o vértice atual

                for (model.entities.Aresta aresta : grafo.getAdjacencias().get(vertex)) {
                    if (!inMST.get(aresta.getDestino())) {
                        pq.add(new PrimEdge(vertex, aresta.getDestino(), aresta.getPeso()));
                    }
                }
            }
        }

        System.out.println("Árvore Geradora Mínima (Prim) concluída.");
        System.out.println("Peso total da Árvore Geradora Mínima: " + totalWeight); // Exibe o peso total
    }

    private void highlightVertex(int vertexId, Color color) {
        Object cell = vertexMap.get(vertexId);
        if (cell != null) {
            graph.getModel().beginUpdate();
            try {
                graph.setCellStyle("fillColor=" + getColorString(color) + ";strokeColor=black", new Object[]{cell});
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
                    graph.setCellStyle("strokeColor=" + getColorString(color) + ";strokeWidth=3", new Object[]{edge});
                }
            } finally {
                graph.getModel().endUpdate();
            }
        }
    }

    private void resetHighlight() {
        graph.getModel().beginUpdate();
        try {
            for (Object vertex : vertexMap.values()) {
                graph.setCellStyle("fillColor=lightblue;strokeColor=black", new Object[]{vertex});
            }
            for (Object edge : graph.getChildCells(graph.getDefaultParent())) {
                if (graph.getModel().isEdge(edge)) {
                    graph.setCellStyle("strokeColor=black;strokeWidth=1", new Object[]{edge});
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

        grafo.inserirArestaNaoDirecionada(7, 2, 14);
        grafo.inserirArestaNaoDirecionada(7, 4, 18);
        grafo.inserirArestaNaoDirecionada(7, 5, 24);
        grafo.inserirArestaNaoDirecionada(4 , 3, 12);
        grafo.inserirArestaNaoDirecionada(3, 2, 16);
        grafo.inserirArestaNaoDirecionada(4, 5, 22);
        grafo.inserirArestaNaoDirecionada(2, 1, 28);
        grafo.inserirArestaNaoDirecionada(1, 6, 10);
        grafo.inserirArestaNaoDirecionada(6, 5, 25);

        PrimVisualizer visualizer = new PrimVisualizer(grafo);
        System.out.println("==================================================");
        visualizer.prim(7);
    }
}

class PrimEdge {
    int origem, destino, peso;

    public PrimEdge(int origem, int destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }
}
