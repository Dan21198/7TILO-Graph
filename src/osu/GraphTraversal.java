//package osu;
//
//import java.util.*;
//
//class GraphTraversal {
//    private Graph graph;
//    private int budget;
//    private int resources;
//
//    // Constructor
//    GraphTraversal(Graph graph, int initialBudget, int initialResources) {
//        this.graph = graph;
//        this.budget = initialBudget;
//        this.resources = initialResources;
//    }
//
////    // Start the traversal from the initial node (node 1)
////    public void traverse() {
////        Node startNode = graph.getNode(1); // Assuming node 1 is the starting node
////        resources += startNode.getResource(); // Add resources from the start node to the budget
////        startNode.useResource(); // Node 1 starts with a resource of 5, now used
////
////        // Log the initial state
////        System.out.println("[t_1] h_1, u_1 -> r=" + budget + ", z=" + resources);
////
////        // Traverse the graph (can be done with DFS or BFS)
////        Set<Node> visitedNodes = new HashSet<>();
////        exploreNode(startNode, 1, visitedNodes);
////    }
//
//    // Explore the node and traverse its neighbors
//    private void exploreNode(Node currentNode, int time, Set<Node> visitedNodes) {
//        // If the node has already been visited, skip it
//        if (visitedNodes.contains(currentNode)) {
//            return;
//        }
//
//        // Mark the current node as visited for this traversal
//        visitedNodes.add(currentNode);
//
//        // List of edges to explore from the current node
//        List<Edge> edges = graph.getEdges(currentNode);
//
//        // If no edges are found, we don't have any further nodes to visit
//        if (edges.isEmpty()) {
//            return;
//        }
//
//        // Explore each edge
//        for (Edge edge : edges) {
//            // If there is enough budget, proceed with the traversal
//            if (budget >= edge.getCost()) {
//                // Deduct the cost from the budget
//                budget -= edge.getCost();
//
//                Node nextNode = edge.to;
//
//                // If resources are available from the node, add them to the total resources
//                if (nextNode.getResource() > 0) {
//                    resources += nextNode.getResource();
//                    nextNode.useResource(); // Reset the resource after it is used
//                }
//
//                // Log the state after traversal
//                System.out.println("[t_" + (time + 1) + "] h_" + edge.getCost() + ", u_" + nextNode.id +
//                        " -> r=" + budget + ", z=" + resources);
//
//                // Recursively explore the next node with an updated time step
//                exploreNode(nextNode, time + 1, new HashSet<>(visitedNodes));  // Ensure each call has a fresh set of visited nodes
//            }
//        }
//    }
//}
