package osu;

import java.util.ArrayList;
import java.util.List;

public class Node {
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

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public boolean isInitial() {
        return isInitial;
    }

    public int getId() {
        return id;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
