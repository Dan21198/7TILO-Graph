package osu;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Create nodes (id, resource value)
        graph.addNode(1, 5); // Initial node with resource 5
        graph.addNode(2, 3);
        graph.addNode(3, 4);
        graph.addNode(4, 2);

        // Add edges (fromNode, toNode, cost)
        graph.addEdge(1, 2, 100);
        graph.addEdge(1, 3, 150);
        graph.addEdge(2, 4, 50);
        graph.addEdge(3, 4, 100);

        // Initialize the graph traversal with a budget of 485 and initial resources of 0
        GraphTraversal traversal = new GraphTraversal(graph, 485, 0);
        traversal.traverse();
    }
}
