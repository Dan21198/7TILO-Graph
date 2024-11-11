import java.util.ArrayList;
import java.util.List;

class Node {
    int id;
    int resource;
    List<Edge> edges;

    public Node(int id, int resource) {
        this.id = id;
        this.resource = resource;
        this.edges = new ArrayList<>();
    }
}