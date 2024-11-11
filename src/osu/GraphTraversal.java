package osu;

import java.util.*;

public class GraphTraversal {

    public static void maximizeResources(Graph graph, Node startNode, int initialBudget, int initialResources) {
        Queue<State> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        State initialState = new State(startNode, 0, initialBudget, initialResources);
        queue.add(initialState);

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            Node currentNode = currentState.node;

            // Skip if this node was visited already
            if (visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);

            // Collect resources from the current node, if it's not already collected
            if (currentState.time == 0 && currentNode.resources > 0) {
                currentState.resources += currentNode.resources;
                currentNode.resources = 0; // Set resources to zero after collection
            }

            // Output the state at the current step
            System.out.println("[" + currentState.time + "] " + currentNode.nodeNumber + " -> r=" + currentState.budget + ", z=" + currentState.resources);

            // Get possible edges to traverse from current node
            for (Edge edge : graph.getEdges(currentNode)) {
                // Check if we have enough budget to traverse the edge
                if (currentState.budget >= edge.cost) {
                    // Calculate new resources after traversing the edge
                    int newResources = currentState.resources + edge.target.resources;

                    // Update state
                    State newState = new State(edge.target, currentState.time + 1,
                            currentState.budget - edge.cost, newResources);

                    // Add the new state to the queue for further processing
                    queue.add(newState);

                    // Zero the resources at the node after collection
                    edge.target.resources = 0;
                }
            }
        }
    }
}
