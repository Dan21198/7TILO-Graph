package osu;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Node node1 = new Node(1, 5);
        Node node2 = new Node(2, 1);
        Node node3 = new Node(3, 5);
        Node node4 = new Node(4, 12);
        Node node5 = new Node(5, 43);
        Node node6 = new Node(6, 36);
        Node node7 = new Node(7, 14);
        Node node8 = new Node(8, 10);
        Node node9 = new Node(9, 15);
        Node node10 = new Node(10, 40);
        Node node11 = new Node(11, 1);
        Node node12 = new Node(12, 3);
        Node node13 = new Node(13, 4);
        Node node14 = new Node(14, 19);
        Node node15 = new Node(15, 20);

        Edge edge1 = new Edge(node1, node2, 193);
        Edge edge2 = new Edge(node2, node3, 12);
        Edge edge3 = new Edge(node2, node4, 150);
        Edge edge4 = new Edge(node4, node5, 23);
        Edge edge5 = new Edge(node4, node6, 27);
        Edge edge6 = new Edge(node1, node7, 132);
        Edge edge7 = new Edge(node7, node8, 48);
        Edge edge8 = new Edge(node7, node9, 21);
        Edge edge9 = new Edge(node1, node10, 145);
        Edge edge10 = new Edge(node10, node11, 4);
        Edge edge11 = new Edge(node10, node12, 30);
        Edge edge12 = new Edge(node10, node13, 78);
        Edge edge13 = new Edge(node13, node14, 57);
        Edge edge14 = new Edge(node13, node15, 30);

        int initialBudget = 485;

        List<Node> nodes = Arrays.asList(node1, node2, node3, node4, node5, node6, node7, node8, node9, node10, node11, node12, node13, node14, node15);
        List<Edge> edges = Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10, edge11, edge12, edge13, edge14);
        Graph graph = new Graph(nodes, edges);

        Traversal traversal = new Traversal(graph, initialBudget);
        traversal.startTraversal();
    }
}

