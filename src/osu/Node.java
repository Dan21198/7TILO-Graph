package osu;

class Node {
    int id;
    int resource;
    private boolean visited; // Flag to track if the node has been visited

    Node(int id, int resource) {
        this.id = id;
        this.resource = resource;
        this.visited = false; // Initially, the node is not visited
    }

    // Getter for resource
    public int getResource() {
        return resource;
    }

    // Set the resource to 0 after it's used
    public void useResource() {
        resource = 0;
    }

    // Mark the node as visited
    public void visit() {
        this.visited = true;
    }

    // Check if the node has been visited
    public boolean isVisited() {
        return this.visited;
    }
}

