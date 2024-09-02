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

public class GraphVisualizer extends JFrame {
    private Grafo grafo;
    private mxGraph graph;
    private Object parent;
    private Map<Integer, Object> vertexMap;

    public GraphVisualizer(Grafo grafo) {
        this.grafo = grafo;
        this.graph = new mxGraph();
        this.parent = graph.getDefaultParent();
        this.vertexMap = new HashMap<>();
        initialize();
    }

    private void initialize() {
        setTitle("Visualização do Grafo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        add(graphComponent, BorderLayout.CENTER);

        graph.getModel().beginUpdate();
        try {
            // Adiciona vértices ao gráfico
            for (Integer id : grafo.getVertices().keySet()) {
                Object vertex = graph.insertVertex(parent, null, id.toString(), 20, 20, 50, 50, "shape=ellipse;fillColor=lightblue");
                vertexMap.put(id, vertex);
            }

            // Adiciona arestas ao gráfico
            grafo.getAdjacencias().forEach((origem, arestas) -> {
                Object origemVertex = vertexMap.get(origem);
                for (model.entities.Aresta aresta : arestas) {
                    Object destinoVertex = vertexMap.get(aresta.getDestino());
                    if (destinoVertex != null) {
                        graph.insertEdge(parent, null, String.valueOf(aresta.getPeso()), origemVertex, destinoVertex, "strokeColor=black;strokeWidth=2");
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

}
