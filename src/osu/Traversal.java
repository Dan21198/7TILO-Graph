package osu;

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
        // Get the first node (starting point)
        Node startNode = graph.getNodes().get(0);  // Start at the first node

        // Collect resources of the first node at the beginning (t_1)
        currentResources += startNode.getResource();
        maxResourcesCollected = currentResources;  // Update max resources collected

        // Print the starting position as t_1 (no cost to enter the first node, h_0)
        System.out.printf("[t_1] h_0 (0), u_%d (%d) -> r=%d, z=%d%n",
                startNode.getId(), startNode.getResource(), budget, currentResources);

        // Start traversing edges and increment stepCount for each new traversal
        stepCount++;

        // Loop over edges for traversal
        for (Edge edge : graph.getEdges()) {
            Node fromNode = edge.getFromNode();
            Node toNode = edge.getToNode();

            // Check if budget allows to tunnel to the next node (edge cost)
            if (budget >= edge.getCost()) {
                budget -= edge.getCost();  // Deduct the edge traversal cost from the budget
                currentResources += toNode.getResource();  // Collect resources from the destination node
                maxResourcesCollected = Math.max(maxResourcesCollected, currentResources); // Update max resources collected

                // Output the current traversal step
                printNodeTraversal(fromNode, toNode, edge);
            }
        }
    }

    private void printNodeTraversal(Node fromNode, Node toNode, Edge edge) {
        // Print the traversal with step count, edge cost (h_), and node resources (u_)
        System.out.printf("[t_%d] h_%d (%d), u_%d (%d) -> r=%d, z=%d%n",
                stepCount++, edge.getCost(), fromNode.getId(),  // h_1 is now edge cost
                toNode.getId(), toNode.getResource(), budget, currentResources);  // u_1 is node resource
    }
}
