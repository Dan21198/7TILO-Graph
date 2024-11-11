package osu;

public class Main {
    public static void main(String[] args) {
        // Create the graph
        Graph graph = new Graph();

        // Add nodes with resources
        graph.addNode(1, 5);  // Node 1 with 5 resources
        graph.addNode(2, 3);  // Node 2 with 3 resources
        graph.addNode(3, 7);  // Node 3 with 7 resources
        graph.addNode(4, 2);  // Node 4 with 2 resources

        // Add edges with costs (from, to, cost)
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 2);

        // Get the starting node (node 1 in this case)
        Node startNode = graph.getNode(1);

        // Call the method to maximize resources with initial budget
        GraphTraversal.maximizeResources(graph, startNode, 485);
    }
}
