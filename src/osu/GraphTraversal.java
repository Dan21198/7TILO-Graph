package osu;

import java.util.*;

class GraphTraversal {
    private Graph graph;
    private int budget;
    private int resources;

    GraphTraversal(Graph graph, int initialBudget, int initialResources) {
        this.graph = graph;
        this.budget = initialBudget;
        this.resources = initialResources;
    }

    // Start the traversal from the initial node (node 1)
    public void traverse() {
        Node startNode = graph.getNode(1); // Assuming node 1 is the starting node
        resources += startNode.getResource(); // Add resources from the start node to the budget
        startNode.useResource(); // Node 1 starts with a resource of 5, now used
        // Log the initial state
        System.out.println("[t_1] h_1, u_1 -> r=" + budget + ", z=" + resources);

        // Traverse the graph (can be done with DFS or BFS)
        exploreNode(startNode, 1);
    }

    // Explore the node and traverse its neighbors
    private void exploreNode(Node currentNode, int time) {
        // Skip the node if it has already been visited
        if (currentNode.isVisited()) {
            System.out.println("Node u_" + currentNode.id + " already visited, skipping.");
            return;
        }

        // Mark the current node as visited
        currentNode.visit();

        // List of edges to explore from the current node
        List<Edge> edges = graph.getEdges(currentNode);

        // If no edges are found, we don't have any further nodes to visit
        if (edges.isEmpty()) {
            System.out.println("No edges from node u_" + currentNode.id);
        }

        // Explore each edge
        for (Edge edge : edges) {
            // Print information about each edge being explored
            System.out.println("Exploring edge h_" + edge.getCost() + " from u_" + currentNode.id + " to u_" + edge.to.id);

            // Skip the edge if the destination node has been visited
            if (edge.to.isVisited()) {
                System.out.println("Node u_" + edge.to.id + " already visited, skipping edge h_" + edge.getCost());
                continue;
            }

            // If there is enough budget, proceed with the traversal
            if (budget >= edge.getCost()) {
                // Deduct the cost from the budget
                budget -= edge.getCost();
                System.out.println("Traversing edge h_" + edge.getCost() + ", new r=" + budget);

                Node nextNode = edge.to;

                // If resources are available from the node, add them to the total resources
                if (nextNode.getResource() > 0) {
                    resources += nextNode.getResource();
                    nextNode.useResource(); // Reset the resource after it is used
                    System.out.println("Acquired resources from u_" + nextNode.id + ", new z=" + resources);
                }

                // Log the state after traversal
                System.out.println("[t_" + (time + 1) + "] h_" + edge.getCost() + ", u_" + nextNode.id +
                        " -> r=" + budget + ", z=" + resources);

                // Recursively explore the next node
                exploreNode(nextNode, time + 1);
            } else {
                System.out.println("Not enough budget to traverse edge h_" + edge.getCost() + " from u_" + currentNode.id + " to u_" + edge.to.id);
            }
        }
    }
}



