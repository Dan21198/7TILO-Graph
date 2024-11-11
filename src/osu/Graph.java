//package osu;
//
//import java.util.*;
//
//class Graph {
//    private Map<Integer, Node> nodes;
//    private List<Edge> edges;
//
//    public Graph() {
//        nodes = new HashMap<>();
//        edges = new ArrayList<>();
//    }
//
//    // Add a node with a given ID and resource value
////    public void addNode(int id, int resource) {
////        Node node = new Node(id, resource);
////        nodes.put(id, node);
////    }
//
//    // Get a node by its ID
//    public Node getNode(int id) {
//        return nodes.get(id);
//    }
//
//    // Add an edge from one node to another with a given cost
//    public void addEdge(int fromId, int toId, int cost) {
//        Node from = nodes.get(fromId);
//        Node to = nodes.get(toId);
//        if (from != null && to != null) {
//            Edge edge = new Edge(from, to, cost);
//            edges.add(edge);
//        }
//    }
//
//    // Get a list of edges from a particular node
//    public List<Edge> getEdges(Node node) {
//        List<Edge> nodeEdges = new ArrayList<>();
//        for (Edge edge : edges) {
//            if (edge.from == node) {
//                nodeEdges.add(edge);
//            }
//        }
//        return nodeEdges;
//    }
//}