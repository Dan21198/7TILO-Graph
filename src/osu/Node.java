package osu;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int id;
    private int resource;
    private boolean resourceCollected;

    public Node(int id, int resource) {
        this.id = id;
        this.resource = resource;
    }

    public int getId() {
        return id;
    }

    public int getResource() {
        return resource;
    }

    public boolean isResourceCollected() {
        return resourceCollected;
    }

    public void setResourceCollected(boolean resourceCollected) {
        this.resourceCollected = resourceCollected;
    }
}
