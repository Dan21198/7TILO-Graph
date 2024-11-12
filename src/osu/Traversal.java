package osu;

import java.util.*;

public class Traversal {

    private Graph graph;
    private TraversalState state;
    private int stepCount;

    public Traversal(Graph graph, int budget) {
        this.graph = graph;
        this.state = new TraversalState(budget);
        this.stepCount = 1;
    }

    public void startTraversal() {
        Node startNode = graph.getNodes().getFirst();
        state.visitedNodes.add(startNode);
        state.maxResourcesCollected += startNode.getResource();

        System.out.printf("[t_%d] h_0 (0), u_%d (%d) -> r=%d, z=%d%n",
                stepCount++, startNode.getId(), startNode.getResource(), state.budget, state.maxResourcesCollected);

        dfs(startNode);
    }

    private void dfs(Node currentNode) {
        List<Edge> sortedEdges = getSortedEdges(currentNode);

        for (Edge edge : sortedEdges) {
            Node fromNode = edge.getFromNode();
            Node toNode = edge.getToNode();

            // Only proceed if the edge is from the current node and leads to a node that hasn't been visited
            if (fromNode == currentNode && !state.visitedNodes.contains(toNode) && state.budget >= edge.getCost()) {

                // Collect resources from the node if not already collected
                if (!toNode.isResourceCollected()) {
                    state.budget -= edge.getCost();
                    state.maxResourcesCollected += toNode.getResource();
                    toNode.setResourceCollected(true);
                }

                state.visitedNodes.add(toNode);

                printNodeTraversal(fromNode, toNode, edge);

                dfs(toNode);

                state.visitedNodes.remove(toNode);
            }
        }
    }


    // Sort edges by the resource-to-cost ratio in descending order
    private List<Edge> getSortedEdges(Node currentNode) {
        List<Edge> sortedEdges = new ArrayList<>();

        // Collect all edges from the current node
        for (Edge edge : graph.getEdges()) {
            if (edge.getFromNode() == currentNode) {
                sortedEdges.add(edge);
            }
        }

        // Sort edges by the resource-to-cost ratio in descending order
        sortedEdges.sort((e1, e2) -> {
            double ratio1 = (double) e1.getToNode().getResource() / e1.getCost();
            double ratio2 = (double) e2.getToNode().getResource() / e2.getCost();
            return Double.compare(ratio2, ratio1);
        });

        return sortedEdges;
    }

    private void printNodeTraversal(Node fromNode, Node toNode, Edge edge) {
        System.out.printf("[t_%d] h_%d (%d), u_%d (%d) -> r=%d, z=%d%n",
                stepCount++, fromNode.getId(), edge.getCost(),
                toNode.getId(), toNode.getResource(), state.budget, state.maxResourcesCollected);
    }

    // Inner class for managing traversal state
    private static class TraversalState {
        int budget;
        int maxResourcesCollected;
        Set<Node> visitedNodes;

        public TraversalState(int initialBudget) {
            this.budget = initialBudget;
            this.maxResourcesCollected = 0;
            this.visitedNodes = new HashSet<>();
        }
    }
}