package osu;

import java.util.*;

public class GraphOptimizer {
    private List<Node> nodes;
    private int maxResources;
    private State bestState;
    private int time;
    private Map<String, State> visitedStates;

    public GraphOptimizer() {
        this.nodes = new ArrayList<>();
        this.maxResources = 0;
        this.time = 1;
        this.visitedStates = new HashMap<>();
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

        PriorityQueue<State> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(this::getHeuristic).reversed());
        priorityQueue.add(initialState);

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

        // Output the step information with r and z
        System.out.printf("[t_%d] h_%d (%d), u_%d (%d) -> r=%d, z=%d%n",
                time,
                incomingEdge != null ? incomingEdge.getId() : -1,  // h_i
                incomingEdge != null ? incomingEdge.getCost() : 0, // Edge cost
                currentNode.getId(),  // u_i
                currentNode.getResources(), // Node resources
                currentState.getResources(),  // r - current resources
                Math.min(currentState.getResources(), currentNode.getResources()) // z - min resources
        );

        // Explore all possible edges from the current node
        for (Edge edge : currentNode.getEdges()) {
            Node nextNode = edge.getTarget();
            int edgeCost = currentState.getUsedEdges().contains(edge) ? 0 : edge.getCost();

            // Strategy 1: Keep current resources as z
            if (currentState.getBudget() >= edgeCost) {
                State newState = new State(currentState.getBudget());
                newState.setBudget(currentState.getBudget() - edgeCost);
                newState.setResources(currentState.getResources());
                newState.getUsedEdges().addAll(currentState.getUsedEdges());
                newState.getUsedEdges().add(edge);

                exploreNode(nextNode, newState, edge, new HashSet<>(currentPath));
            }

            // Strategy 2: Convert current resources to budget
            int potentialBudget = currentState.getBudget() + currentState.getResources();
            if (potentialBudget >= edgeCost && currentState.getResources() > 0) {
                State newState = new State(potentialBudget);
                newState.setBudget(potentialBudget - edgeCost);
                newState.setResources(0); // Reset resources as we used them for budget
                newState.getUsedEdges().addAll(currentState.getUsedEdges());
                newState.getUsedEdges().add(edge);

                exploreNode(nextNode, newState, edge, new HashSet<>(currentPath));
            }
        }

        // Remove current node from path before backtracking
        currentPath.remove(currentNode);
    }

    private int getHeuristic(State state) {
        return state.getBudget() + state.getResources();
    }
}