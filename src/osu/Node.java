package osu;


import java.util.HashMap;
import java.util.Map;

class Node {
    private final int id;
    public int resources;  // The resources available at this node
    public final Map<Node, Integer> edges = new HashMap<>();  // Outgoing edges and their costs

    public Node(int id, int resources) {
        this.id = id;
        this.resources = resources;
    }

    public void addEdge(Node neighbor, int cost) {
        edges.put(neighbor, cost);
    }

    public int getId() {
        return id;
    }
}