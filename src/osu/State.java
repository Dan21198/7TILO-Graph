
class State {
    Node node;
    int time;
    int budget;
    int resources;

    public State(Node node, int time, int budget, int resources) {
        this.node = node;
        this.time = time;
        this.budget = budget;
        this.resources = resources;
    }
}