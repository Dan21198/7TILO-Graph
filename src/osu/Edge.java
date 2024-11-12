package osu;

public class Edge {
    private Node fromNode;
    private Node toNode;
    private int cost;

    public Edge(Node fromNode, Node toNode, int cost) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.cost = cost;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public int getCost() {
        return cost;
    }
}
