package osu;

import java.util.ArrayList;
import java.util.List;

class Node {
    private int id;
    private int resources;
    private boolean isInitial;
    private List<Edge> edges;

    public Node(int id, int resources, boolean isInitial) {
        this.id = id;
        this.resources = resources;
        this.isInitial = isInitial;
        this.edges = new ArrayList<>();
    }

    public int getId() { return id; }
    public int getResources() { return resources; }    // Changed from getResource
    public void setResources(int resources) { this.resources = resources; }
    public boolean isInitial() { return isInitial; }
    public List<Edge> getEdges() { return edges; }
    public void addEdge(Edge edge) { edges.add(edge); }

}
