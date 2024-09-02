package view;

import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import model.entities.Grafo;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFSVisualizer extends JFrame {
    private Grafo grafo;
    private mxGraph graph;
    private Object parent;
    private Map<Integer, Object> vertexMap;

    public BFSVisualizer(Grafo grafo) {
        this.grafo = grafo;
        this.graph = new mxGraph();
        this.parent = graph.getDefaultParent();
        this.vertexMap = new HashMap<>();
        initialize();
    }

    private void initialize() {
        setTitle("Visualização do BFS");
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
                        graph.insertEdge(parent, null, "", origemVertex, destinoVertex);
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

    public void bfs(int startVertex) {
        resetHighlight();

        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Boolean> visited = new HashMap<>();
        Map<Integer, Integer> distances = new HashMap<>();
        List<Integer> visitOrder = new LinkedList<>();

        // Initialize distances and visited map
        grafo.getVertices().keySet().forEach(v -> {
            visited.put(v, false);
            distances.put(v, Integer.MAX_VALUE); // Use Integer.MAX_VALUE for unvisited nodes
        });

        visited.put(startVertex, true);
        distances.put(startVertex, 0);
        queue.add(startVertex);
        visitOrder.add(startVertex);

        System.out.println("Iniciando BFS a partir do vértice: " + startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.println("Visitando vértice: " + currentVertex);
            highlightVertex(currentVertex, Color.GREEN);

            for (model.entities.Aresta aresta : grafo.getAdjacencias().get(currentVertex)) {
                int destino = aresta.getDestino();
                if (!visited.get(destino)) {
                    System.out.println("Explorando aresta de " + currentVertex + " para " + destino);
                    visited.put(destino, true);
                    distances.put(destino, distances.get(currentVertex) + 1);
                    queue.add(destino);
                    visitOrder.add(destino);
                    highlightEdge(currentVertex, destino, Color.RED);
                }
            }
        }

        // Print the order of visit and distances
        System.out.print("Ordem de visita dos vértices: ");
        visitOrder.forEach(v -> System.out.print(v + " -> "));
        System.out.println();

        System.out.println("Distâncias a partir do nó " + startVertex + ":");
        distances.forEach((nodo, distancia) -> System.out.println("Nó " + nodo + ": " + (distancia == Integer.MAX_VALUE ? "Inacessível" : distancia)));

        System.out.println("BFS concluído.");
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

        grafo.inserirArestaDirecionada(1, 2, 0);
        grafo.inserirArestaDirecionada(1, 3, 0);
        grafo.inserirArestaDirecionada(2, 6, 0);
        grafo.inserirArestaDirecionada(5, 4, 0);
        grafo.inserirArestaDirecionada(6, 5, 0);
        grafo.inserirArestaDirecionada(5, 7, 0);

        BFSVisualizer visualizer = new BFSVisualizer(grafo);
        System.out.println("==================================================");
        visualizer.bfs(1);
    }
}
