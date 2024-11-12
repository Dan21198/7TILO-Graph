package osu;

public class Edge {
    private int id;
    private Node source;
    private Node target;
    private int cost;

    public Edge(int id, Node source, Node target, int cost) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.cost = cost;
    }

    public int getId() { return id; }
    public Node getTarget() { return target; }
    public int getCost() { return cost; }
}

