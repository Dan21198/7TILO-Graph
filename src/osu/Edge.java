package osu;

public class Edge {
    private static int idCounter = 0;  // Static counter to generate unique IDs for edges
    private int id;
    private Node fromNode;
    private Node toNode;
    private int cost;

    public Edge(Node fromNode, Node toNode, int cost) {
        this.id = ++idCounter;
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.cost = cost;
    }
    public int getId() {
        return id;
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
