//package osu;
//
//import java.util.*;
//
//public class GraphOptimizer {
//    private List<Node> nodes;
//    private int maxResources;
//    private State bestState;
//    private int time;
//    private Map<String, State> visitedStates;
//
//    public GraphOptimizer() {
//        this.nodes = new ArrayList<>();
//        this.maxResources = 0;
//        this.time = 1;
//        this.visitedStates = new HashMap<>();
//    }
//
//    public void addNode(Node node) {
//        nodes.add(node);
//    }
//
//    public State optimize(int initialBudget) {
//        Node startNode = findStartNode();
//        System.out.println("Initial budget: " + initialBudget);
//        State initialState = new State(initialBudget);
//        bestState = new State(initialBudget);
//        bestState.setResources(0);  // Initialize with zero resources
//
//        exploreNode(startNode, initialState, null, new HashSet<>(), new HashSet<>());
//
//        return bestState;
//    }
//
//    private Node findStartNode() {
//        return nodes.stream()
//                .filter(Node::isInitial)
//                .findFirst()
//                .orElseThrow(() -> new IllegalStateException("No initial node found"));
//    }
//
//    private void exploreNode(Node currentNode, State currentState, Edge incomingEdge, Set<Node> currentPath, Set<Edge> traversedEdges, int time) {
//        // Collect resources from the current node
//        int resourcesBeforeUpdate = currentState.getResources();
//        int nodeResources = currentNode.getResources();
//        currentState.addResources(nodeResources);  // Update resources after collecting from the node
//
//        // Log the current step details
//        logStepInfo(incomingEdge, currentNode, resourcesBeforeUpdate, nodeResources, currentState, time);
//
//        // Record the node as visited
//        currentPath.add(currentNode);
//
//        // Check if we need to tunnel to the next node
//        for (Edge outgoingEdge : currentNode.getOutgoingEdges()) {
//            // Ensure the edge hasn't been traversed yet
//            if (!traversedEdges.contains(outgoingEdge)) {
//                // Deduct the cost of tunneling from the budget
//                int tunnelCost = outgoingEdge.getCost();
//                if (currentState.getBudget() >= tunnelCost) {
//                    // Update the budget after tunneling
//                    currentState.decreaseBudget(tunnelCost);
//
//                    // Move to the next node (recursion for the next step)
//                    Node nextNode = outgoingEdge.getToNode();
//                    traversedEdges.add(outgoingEdge);  // Mark the edge as traversed
//
//                    // Recursively explore the next node
//                    exploreNode(nextNode, currentState, outgoingEdge, currentPath, traversedEdges, time + 1);  // Increment time for the next step
//                }
//            }
//        }
//    }
//
//
//    private void logStepInfo(Edge incomingEdge, Node currentNode, int resourcesBeforeUpdate, int nodeResources, State currentState, int time) {
//        System.out.printf("[t_%d] h_%d (%d), u_%d (%d) -> r=%d, z=%d%n",
//                time,
//                incomingEdge != null ? incomingEdge.getId() : -1,  // Handle null for the first edge (i.e., starting point)
//                incomingEdge != null ? incomingEdge.getCost() : 0,  // If no incoming edge, cost is 0
//                currentNode.getId(),
//                nodeResources,
//                currentState.getBudget(),
//                currentState.getResources()
//        );
//    }
//
//    // Generates a unique key to track visited states based on the node and the current state's resources and budget.
//    private String generateStateKey(Node currentNode, State currentState) {
//        return "Node:" + currentNode.getId() +
//                "_Budget:" + currentState.getBudget() +
//                "_Resources:" + currentState.getResources();
//    }
//
//}
