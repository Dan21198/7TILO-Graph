package osu;

import java.util.*;

class Graph {
    private final Map<Integer, Node> nodes = new HashMap<>();

    public void addNode(int id, int resources) {
        nodes.put(id, new Node(id, resources));
    }

    public void addEdge(int from, int to, int cost) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        fromNode.addEdge(toNode, cost);
    }

    public Node getNode(int id) {
        return nodes.get(id);
    }
}