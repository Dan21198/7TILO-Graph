package osu;

import java.util.*;

public class State {
    private int budget;
    private int resources;
    private List<StateStep> stateSteps;
    private Set<Edge> usedEdges;

    public State(int budget) {
        this.budget = budget;
        this.resources = 0; // Initial resources are zero
        this.stateSteps = new ArrayList<>();
        this.usedEdges = new HashSet<>();
    }

    public int getBudget() { return budget; }
    public void setBudget(int budget) { this.budget = budget; }
    public int getResources() { return resources; }
    public void setResources(int resources) { this.resources = resources; }
    public List<StateStep> getStateSteps() { return stateSteps; }
    public Set<Edge> getUsedEdges() { return usedEdges; }

    public void addStep(int time, Edge edge, Node node) {
        stateSteps.add(new StateStep(time, edge, node));
    }
}