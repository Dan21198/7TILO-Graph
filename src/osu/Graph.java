package osu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Graph {
    private Map<Node, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add node to the graph
    public void addNode(Node node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    // Add an edge between two nodes
    public void addEdge(Node from, Node to, int cost) {
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, cost));
    }

    // Get edges for a given node
    public List<Edge> getEdges(Node node) {
        return adjacencyList.getOrDefault(node, new ArrayList<>());
    }
}