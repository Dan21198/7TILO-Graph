package osu;

public class StateStep {
    private int time;
    private int edgeId;
    private int edgeCost;
    private int nodeId;
    private int nodeResources;
    private int budget;
    private int resources;

    public StateStep(int time, int edgeId, int edgeCost, int nodeId,
                     int nodeResources, int budget, int resources) {
        this.time = time;
        this.edgeId = edgeId;
        this.edgeCost = edgeCost;
        this.nodeId = nodeId;
        this.nodeResources = nodeResources;
        this.budget = budget;
        this.resources = resources;
    }

    // Getters
    public int getTime() { return time; }
    public int getEdgeId() { return edgeId; }
    public int getEdgeCost() { return edgeCost; }
    public int getNodeId() { return nodeId; }
    public int getNodeResources() { return nodeResources; }
    public int getBudget() { return budget; }
    public int getResources() { return resources; }
}
