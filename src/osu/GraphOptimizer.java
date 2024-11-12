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

        // Start exploring from the initial node
        exploreNode(startNode, initialState, null, new HashSet<>());
        return bestState;
    }

    private void exploreNode(Node currentNode, State currentState, Edge incomingEdge, Set<Node> currentPath) {
        // Skip if node is in current path (avoid cycles)
        if (currentPath.contains(currentNode)) {
            return;
        }

        // Add current node to path
        currentPath.add(currentNode);

        // Collect resources from current node (keep node's resources intact for later)
        int nodeResources = currentNode.getResources();
        int resourcesBeforeUpdate = currentState.getResources();
        currentState.setResources(resourcesBeforeUpdate + nodeResources); // Add current node's resources to state

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
                Math.min(resourcesBeforeUpdate, nodeResources) // z - min resources before collecting
        );

        // Explore all possible edges from the current node
        for (Edge edge : currentNode.getEdges()) {
            Node nextNode = edge.getTarget();
            int edgeCost = edge.getCost();

            // Ensure the path remains within budget
            if (currentState.getBudget() >= edgeCost) {
                State newState = new State(currentState.getBudget() - edgeCost);
                newState.setResources(currentState.getResources());
                newState.addStep(time++, edge, nextNode);

                exploreNode(nextNode, newState, edge, new HashSet<>(currentPath));
            }
        }

        // Backtrack (remove node from current path)
        currentPath.remove(currentNode);
    }
}
