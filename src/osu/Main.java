package osu;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Sample nodes and edges
        Node node1 = new Node(1, 10);
        Node node2 = new Node(2, 20);
        Node node3 = new Node(3, 30);

        Edge edge1 = new Edge(node1, node2, 5);
        Edge edge2 = new Edge(node2, node3, 10);

        // Initial budget
        int initialBudget = 50;

        // Create a graph with nodes and edges
        Graph graph = new Graph(Arrays.asList(node1, node2, node3), Arrays.asList(edge1, edge2));

        // Start traversal with the given initial budget
        Traversal traversal = new Traversal(graph, initialBudget);
        traversal.startTraversal();
    }
}

