package osu;

public class Traversal {
    private Graph graph;
    private int budget;
    private int maxResourcesCollected;
    private int currentResources;

    public Traversal(Graph graph, int budget) {
        this.graph = graph;
        this.budget = budget;
        this.maxResourcesCollected = 0;
        this.currentResources = 0;
    }

    public void startTraversal() {
        for (Node node : graph.getNodes()) {
            if (budget >= node.getId()) {
                budget -= node.getId();  // Tunnel cost
                currentResources += node.getResource();  // Collect resources
                maxResourcesCollected = Math.max(maxResourcesCollected, currentResources);
                printNodeTraversal(node);
            }
        }
    }

    private void printNodeTraversal(Node node) {
        System.out.printf("[t_%d] h_%d (%d), u_%d (%d) -> r=%d, z=%d%n",
                node.getId(), node.getId(), node.getResource(),
                node.getId(), node.getResource(), budget, maxResourcesCollected);
    }
}

