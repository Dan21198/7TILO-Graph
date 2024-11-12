package osu;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int id;
    private int resource;

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
}
