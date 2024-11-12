package osu;

import java.util.ArrayList;
import java.util.List;

public class Traversal {
    private Graph graph;
    private int budget;
    private int maxResourcesCollected;
    private int currentResources;
    private int stepCount;

    public Traversal(Graph graph, int budget) {
        this.graph = graph;
        this.budget = budget;
        this.maxResourcesCollected = 0;
        this.currentResources = 0;
        this.stepCount = 1;  // Start at t_1
    }

    public void startTraversal() {
        Node startNode = graph.getNodes().getFirst();

        maxResourcesCollected += startNode.getResource();

        System.out.printf("[t_1] h_0 (0), u_%d (%d) -> r=%d, z=%d%n",
                startNode.getId(), startNode.getResource(), budget, maxResourcesCollected);

        stepCount++;

        // A list of edges sorted by resource-to-cost ratio (greedy strategy)
        List<Edge> sortedEdges = sortEdgesByResourceCostRatio(graph.getEdges());

        // Loop over edges for traversal
        for (Edge edge : sortedEdges) {
            Node fromNode = edge.getFromNode();
            Node toNode = edge.getToNode();

            if (budget >= edge.getCost()) {
                budget -= edge.getCost();
                currentResources += toNode.getResource();
                maxResourcesCollected = Math.max(maxResourcesCollected, currentResources);

                printNodeTraversal(fromNode, toNode, edge);
            }
        }
    }

    private List<Edge> sortEdgesByResourceCostRatio(List<Edge> edges) {
        List<Edge> sortedEdges = new ArrayList<>(edges);
        sortedEdges.sort((e1, e2) -> {
            double ratio1 = (double) e1.getToNode().getResource() / e1.getCost();
            double ratio2 = (double) e2.getToNode().getResource() / e2.getCost();
            return Double.compare(ratio2, ratio1);
        });
        return sortedEdges;
    }

    private void printNodeTraversal(Node fromNode, Node toNode, Edge edge) {
        System.out.printf("[t_%d] h_%d (%d), u_%d (%d) -> r=%d, z=%d%n",
                stepCount++, edge.getId(), edge.getCost(),  // h_1 is now edge ID and cost
                toNode.getId(), toNode.getResource(), budget, currentResources);  // u_1 is node resource
    }
}
