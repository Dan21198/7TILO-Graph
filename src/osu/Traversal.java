package osu;

import java.util.*;

public class Traversal {

    private Graph graph;
    private TraversalState state; // State to track progress
    private int stepCount;

    public Traversal(Graph graph, int budget) {
        this.graph = graph;
        this.state = new TraversalState(budget); // Initialize with the given budget
        this.stepCount = 1; // Start at t_1
    }

    public void startTraversal() {
        Node startNode = graph.getNodes().getFirst();
        state.visitedNodes.add(startNode);

// Initial resources from the start node
        state.maxResourcesCollected += startNode.getResource();

        System.out.printf("[t_%d] h_0 (0), u_%d (%d) -> r=%d, z=%d%n",
                stepCount++, startNode.getId(), startNode.getResource(), state.budget, state.maxResourcesCollected);

// Start DFS traversal to explore all paths
        dfs(startNode);
    }

    private void dfs(Node currentNode) {
        // Loop through all edges starting from the current node
        List<Edge> sortedEdges = getSortedEdges(currentNode);

        // Debugging: Print edges being considered
//        System.out.printf("Processing node h_%d with edges: %n", currentNode.getId());
//        for (Edge edge : sortedEdges) {
//            System.out.printf("  Edge to h_%d with cost %d%n", edge.getToNode().getId(), edge.getCost());
//        }

        for (Edge edge : sortedEdges) {
            Node fromNode = edge.getFromNode();
            Node toNode = edge.getToNode();

            // Only proceed if the edge is from the current node and leads to a node that hasn't been visited
            if (fromNode == currentNode && !state.visitedNodes.contains(toNode) && state.budget >= edge.getCost()) {
                // Debugging: Log edge decision
//                System.out.printf("  Moving from h_%d to h_%d with edge cost %d, remaining budget %d%n",
//                        fromNode.getId(), toNode.getId(), edge.getCost(), state.budget);

                // Collect resources from the node if not already collected
                if (!toNode.isResourceCollected()) {
                    state.budget -= edge.getCost(); // Decrease the budget by the edge cost (spend to tunnel)
                    state.maxResourcesCollected += toNode.getResource(); // Collect resources from the new node
                    toNode.setResourceCollected(true); // Mark the node as visited for resource collection
                }

                state.visitedNodes.add(toNode);

                // Print the current state of traversal
                printNodeTraversal(fromNode, toNode, edge);

                // Recur for the next node
                dfs(toNode);

                // Backtrack: undo the visited node but do not undo the resource spending
                state.visitedNodes.remove(toNode); // Remove the node from the visited list
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

        // Debugging: Log the sorted edges
//        System.out.printf("Sorted edges from h_%d: %n", currentNode.getId());
//        for (Edge edge : sortedEdges) {
//            System.out.printf("  Edge to h_%d with cost %d, resource %d%n", edge.getToNode().getId(), edge.getCost(), edge.getToNode().getResource());
//        }

        return sortedEdges;
    }

    private void printNodeTraversal(Node fromNode, Node toNode, Edge edge) {
        // Print each traversal step with the correct step count, node info, resources collected, and budget
        System.out.printf("[t_%d] h_%d (%d), u_%d (%d) -> r=%d, z=%d%n",
                stepCount++, fromNode.getId(), edge.getCost(), // h_1 is now edge ID and cost
                toNode.getId(), toNode.getResource(), state.budget, state.maxResourcesCollected); // u_1 is node resource
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