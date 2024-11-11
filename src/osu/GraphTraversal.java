package osu;

import java.util.*;

class GraphTraversal {
    public static void maximizeResources(Graph graph, Node startNode, int initialBudget) {
        int budget = initialBudget;  // Initial budget (r)
        int resourcesCollected = 0;  // Total resources collected (z)
        Set<Node> visited = new HashSet<>();  // Set to track visited nodes

        // Queue to manage BFS-like traversal
        Queue<Node> toVisit = new LinkedList<>();
        toVisit.add(startNode);

        int timeStep = 1;
        visited.add(startNode);  // Mark the start node as visited

        while (!toVisit.isEmpty()) {
            Node currentNode = toVisit.poll();

            // Collect resources at the current node
            int resourcesAtNode = currentNode.resources;
            resourcesCollected += resourcesAtNode;  // Add resources to the total collected
            currentNode.resources = 0;  // Mark the node as visited and its resources as collected

            // Print the state after collecting resources from the current node
            System.out.println(String.format("[t_%d] h_%d, u_%d -> r=%d, z=%d",
                    timeStep, currentNode.getId(), currentNode.getId(), budget, resourcesCollected));

            // Flag to track if any valid traversal happens
            boolean moved = false;

            // Traverse to the neighboring nodes if there is enough budget
            for (Map.Entry<Node, Integer> entry : currentNode.edges.entrySet()) {
                Node neighbor = entry.getKey();
                int edgeCost = entry.getValue();

                // Can we afford to traverse this edge?
                if (budget >= edgeCost && !visited.contains(neighbor)) {
                    toVisit.add(neighbor);
                    visited.add(neighbor);  // Mark the neighbor as visited
                    moved = true;  // Mark that we moved to a neighbor

                    // Print the state after traversing to the neighbor
                    System.out.println(String.format("[t_%d] h_%d, u_%d -> r=%d, z=%d",
                            timeStep + 1, currentNode.getId(), neighbor.getId(),
                            budget - edgeCost, resourcesCollected));

                    // Spend the resources on traversing the edge
                    budget -= edgeCost;
                }
            }

            // Only increment the time step if we actually moved to a new node
            if (moved) {
                timeStep++;
            }
        }
    }
}