package osu;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        GraphOptimizer optimizer = new GraphOptimizer();

        // Create nodes
        Node node1 = new Node(1, 5, true);   // Initial node
        Node node2 = new Node(2, 10, false);
        Node node3 = new Node(3, 15, false);
        Node node4 = new Node(4, 20, false);

        // Create edges
        Edge edge1 = new Edge(1, node1, node2, 100);
        Edge edge2 = new Edge(2, node1, node3, 150);
        Edge edge3 = new Edge(3, node2, node4, 200);
        Edge edge4 = new Edge(4, node3, node4, 180);

        // Connect nodes with edges
        node1.addEdge(edge1);
        node1.addEdge(edge2);
        node2.addEdge(edge3);
        node3.addEdge(edge4);

        // Add nodes to optimizer
        optimizer.addNode(node1);
        optimizer.addNode(node2);
        optimizer.addNode(node3);
        optimizer.addNode(node4);

        // Run optimization with initial budget
        State result = optimizer.optimize(485);
    }
}

