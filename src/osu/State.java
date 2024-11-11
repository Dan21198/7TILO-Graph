package osu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class State {
    private int budget;
    private int resources;
    private Set<Node> visitedNodes;
    private Set<Edge> usedEdges;
    private List<String> steps;
    private List<StateStep> stateSteps;

    public State(int initialBudget) {
        this.budget = initialBudget;
        this.resources = 0;
        this.visitedNodes = new HashSet<>();
        this.usedEdges = new HashSet<>();
        this.steps = new ArrayList<>();
        this.stateSteps = new ArrayList<>();
    }

    public int getBudget() { return budget; }
    public void setBudget(int budget) { this.budget = budget; }
    public int getResources() { return resources; }
    public void setResources(int resources) { this.resources = resources; }
    public Set<Node> getVisitedNodes() { return visitedNodes; }
    public Set<Edge> getUsedEdges() { return usedEdges; }
    public List<String> getSteps() { return steps; }

    public List<StateStep> getStateSteps() {
        return stateSteps;
    }

    public void addStep(int time, Edge edge, Node node) {
        StateStep step = new StateStep(time, edge.getId(), edge.getCost(),
                node.getId(), node.getResources(),
                budget, resources);
        stateSteps.add(step);
    }
}