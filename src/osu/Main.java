
public class Main {
    public static void main(String[] args) {

        Node startNode = new Node(1, 5);
        Node node2 = new Node(2, 10);
        Node node3 = new Node(3, 15);
        Node node4 = new Node(4, 20);

        startNode.edges.add(new Edge(node2, 50));
        startNode.edges.add(new Edge(node3, 30));
        node2.edges.add(new Edge(node4, 60));
        node3.edges.add(new Edge(node4, 40));

        int initialBudget = 485;
        int initialResources = 0;

        traverseGraph(startNode, initialBudget, initialResources);
    }
}