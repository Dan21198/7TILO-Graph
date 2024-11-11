package osu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphOptimizer {
    private List<Node> nodes;
    private int maxResources;
    private State bestState;
    private int time;

    public GraphOptimizer() {
        this.nodes = new ArrayList<>();
        this.maxResources = 0;
        this.time = 1;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public State optimize(int initialBudget) {
        Node startNode = nodes.stream()
                .filter(Node::isInitial)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No initial node found"));

        State initialState = new State(initialBudget);
        bestState = initialState;

        exploreNode(startNode, initialState, null, new HashSet<>());
        return bestState;
    }

    private void exploreNode(Node currentNode, State currentState, Edge incomingEdge, Set<Node> currentPath) {
        // Add step for the current node if there's an incoming edge
        if (incomingEdge != null) {
            currentState.addStep(time++, incomingEdge, currentNode);
        }

        // Skip if node is in current path (avoid cycles)
        if (currentPath.contains(currentNode)) {
            return;
        }

        // Add current node to path
        currentPath.add(currentNode);

        // Collect resources from current node
        int nodeResources = currentNode.getResources();
        currentState.setResources(currentState.getResources() + nodeResources);
        currentNode.setResources(0); // Resources are collected

        // Update best state if we found better solution
        if (currentState.getResources() > maxResources) {
            maxResources = currentState.getResources();
            bestState = new State(currentState.getBudget());
            bestState.setResources(currentState.getResources());
            bestState.getStateSteps().clear();
            for (StateStep step : currentState.getStateSteps()) {
                bestState.getStateSteps().add(step);
            }
        }

        // Try all possible edges
        for (Edge edge : currentNode.getEdges()) {
            Node nextNode = edge.getTarget();
            int edgeCost = edge.getCost();

            // Strategy 1: Keep current resources as z
            if (currentState.getBudget() >= edgeCost) {
                State newState = new State(currentState.getBudget());
                newState.setBudget(currentState.getBudget() - edgeCost);
                newState.setResources(currentState.getResources());
                newState.getUsedEdges().addAll(currentState.getUsedEdges());
                for (StateStep step : currentState.getStateSteps()) {
                    newState.getStateSteps().add(step);
                }
                if (!edge.isUsed()) {
                    edge.setUsed(true);  // Mark edge as used
                    newState.getUsedEdges().add(edge);
                }
                exploreNode(nextNode, newState, edge, new HashSet<>(currentPath));
            }

            // Strategy 2: Convert current resources to budget
            int potentialBudget = currentState.getBudget() + currentState.getResources();
            if (potentialBudget >= edgeCost && currentState.getResources() > 0) {
                State newState = new State(potentialBudget);
                newState.setBudget(potentialBudget - edgeCost);
                newState.setResources(0); // Reset resources as we used them for budget
                newState.getUsedEdges().addAll(currentState.getUsedEdges());
                for (StateStep step : currentState.getStateSteps()) {
                    newState.getStateSteps().add(step);
                }
                if (!edge.isUsed()) {
                    edge.setUsed(true); // Mark edge as used
                    newState.getUsedEdges().add(edge);
                }
                exploreNode(nextNode, newState, edge, new HashSet<>(currentPath));
            }
        }

        // Remove current node from path before backtracking
        currentPath.remove(currentNode);
    }
}