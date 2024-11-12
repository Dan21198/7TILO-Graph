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
        Node startNode = findStartNode();
        System.out.println("Initial budget: " + initialBudget);
        State initialState = new State(initialBudget);
        bestState = new State(initialBudget);
        bestState.setResources(0);  // Initialize with zero resources

        exploreNode(startNode, initialState, null, new HashSet<>(), new HashSet<>());

        return bestState;
    }

    private Node findStartNode() {
        return nodes.stream()
                .filter(Node::isInitial)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No initial node found"));
    }

    private void exploreNode(Node currentNode, State currentState, Edge incomingEdge, Set<Node> currentPath, Set<Edge> traversedEdges) {
        // Skip if node is in current path (to avoid cycles)
        if (currentPath.contains(currentNode)) {
            return;
        }

        // Add current node to path to avoid cycles
        currentPath.add(currentNode);

        // Clone the state to avoid modifying the same instance in recursive calls
        State updatedState = new State(currentState.getBudget());
        updatedState.setResources(currentState.getResources()); // Carry over resources from previous state
        updatedState.getStateSteps().addAll(currentState.getStateSteps());

        // Only collect resources if they haven't been collected on this path
        int nodeResources = currentNode.getResources();
        if (nodeResources > 0) {
            updatedState.setResources(updatedState.getResources() + nodeResources);
            currentNode.setResources(0);  // Set node resources to 0 for this path
        }

        // Update the best state if the new state has higher resources
        if (updatedState.getResources() > maxResources && updatedState.getBudget() >= 0) {
            maxResources = updatedState.getResources();
            bestState = new State(updatedState.getBudget());
            bestState.setResources(updatedState.getResources());
            bestState.getStateSteps().clear();
            bestState.getStateSteps().addAll(updatedState.getStateSteps());
        }

        // Log the current step information without affecting the budget by resources
        logStepInfo(incomingEdge, currentNode, currentState.getResources(), nodeResources, updatedState);

        // Generate unique key based on current state and visited nodes
        String stateKey = generateStateKey(currentNode, updatedState);

        // If we haven't visited this exact state, add to visited
        if (!visitedStates.containsKey(stateKey)) {
            visitedStates.put(stateKey, new State(updatedState.getBudget(), updatedState.getResources()));
        }

        // Explore each edge from the current node
        for (Edge edge : currentNode.getEdges()) {
            if (traversedEdges.contains(edge)) {
                continue;  // Skip already traversed edges
            }

            Node nextNode = edge.getTarget();
            int edgeCost = edge.getCost();

            // Check if budget is sufficient for this edge cost
            if (updatedState.getBudget() >= edgeCost) {
                // Update the budget first (subtract edge cost)
                updatedState.setBudget(updatedState.getBudget() - edgeCost);

                // Clone the updated state for this path with deducted edge cost
                State newState = new State(updatedState.getBudget());
                newState.setResources(updatedState.getResources());
                newState.getStateSteps().addAll(updatedState.getStateSteps());
                newState.addStep(time++, edge, nextNode);

                // Mark this edge as used
                traversedEdges.add(edge);

                // Recursively explore the next node
                exploreNode(nextNode, newState, edge, new HashSet<>(currentPath), traversedEdges);
            }
        }

        // Backtracking: restore node resources for future paths
        currentNode.setResources(nodeResources);  // Restore original resources value
        currentPath.remove(currentNode);
    }


    private void logStepInfo(Edge incomingEdge, Node currentNode, int resourcesBeforeUpdate, int nodeResources, State currentState) {
        System.out.printf("[t_%d] h_%d (%d), u_%d (%d) -> r=%d, z=%d%n",
                time,
                incomingEdge != null ? incomingEdge.getId() : -1,
                incomingEdge != null ? incomingEdge.getCost() : 0,
                currentNode.getId(),
                nodeResources,
                currentState.getBudget(),
                currentState.getResources()
        );
    }

    // Generates a unique key to track visited states based on the node and the current state's resources and budget.
    private String generateStateKey(Node currentNode, State currentState) {
        return "Node:" + currentNode.getId() +
                "_Budget:" + currentState.getBudget() +
                "_Resources:" + currentState.getResources();
    }

}
