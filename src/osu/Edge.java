package osu;

public class Edge {
    private int id;
    private Node source;
    private Node target;
    private int cost;
    private boolean used;

    public Edge(int id, Node source, Node target, int cost) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.cost = cost;
        this.used = false;
    }

    public int getId() { return id; }
    public Node getTarget() { return target; }
    public int getCost() { return cost; }

    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }
}
