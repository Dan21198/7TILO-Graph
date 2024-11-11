package osu;

class Edge {
    Node from;
    Node to;
    int cost; // The cost of traversing this edge

    Edge(Node from, Node to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    // Get the cost to traverse this edge
    public int getCost() {
        return cost;
    }

    // Method to traverse the edge and return whether it is traversable (based on available budget)
    public boolean traverse(int availableBudget) {
        return availableBudget >= cost; // Return whether the budget allows this edge to be traversed
    }
}

