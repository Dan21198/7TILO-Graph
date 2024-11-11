package osu;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        // Create an array of node resources
        int[] resources = {5, 1, 5, 12, 43, 36, 14, 10, 15, 40, 1, 3, 4, 19, 20};

        // Create and add nodes to the graph
        Node[] nodes = new Node[resources.length];
        for (int i = 0; i < resources.length; i++) {
            nodes[i] = new Node(i + 1, resources[i]);
            graph.addNode(nodes[i]);
        }

        // Add edges to the graph
        graph.addEdge(nodes[0], nodes[1], 193);
        graph.addEdge(nodes[1], nodes[2], 12);
        graph.addEdge(nodes[1], nodes[3], 150);
        graph.addEdge(nodes[3], nodes[4], 23);
        graph.addEdge(nodes[3], nodes[5], 27);
        graph.addEdge(nodes[0], nodes[6], 132);
        graph.addEdge(nodes[6], nodes[7], 48);
        graph.addEdge(nodes[6], nodes[8], 21);
        graph.addEdge(nodes[0], nodes[9], 145);
        graph.addEdge(nodes[9], nodes[10], 4);
        graph.addEdge(nodes[9], nodes[11], 30);
        graph.addEdge(nodes[9], nodes[12], 78);
        graph.addEdge(nodes[12], nodes[13], 57);
        graph.addEdge(nodes[9], nodes[14], 30);

        int initialBudget = 485;
        int initialResources = 0;

        GraphTraversal.maximizeResources(graph, nodes[0], initialBudget, initialResources);
    }
}
