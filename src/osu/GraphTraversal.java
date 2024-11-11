import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public static void traverseGraph(Node startNode, int budget, int resources) {
    PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingInt(s -> -s.resources));
    queue.add(new State(startNode, 0, budget, resources));
    Set<Node> visited = new HashSet<>();

    while (!queue.isEmpty()) {
        State current = queue.poll();

        // Pokud je dosažen nejlepší možný stav (všechny dostupné zdroje získány), ukončíme průchod
        if (visited.contains(current.node) || current.node.resource == 0) {
            continue;
        }

        // Výpis aktuálního stavu
        System.out.printf("[t_%d] uzel %d -> r=%d, z=%d\n", current.time, current.node.id, current.budget, current.resources);

        // Přičteme zdroje z aktuálního uzlu
        current.resources += current.node.resource;
        current.node.resource = 0; // Zdroje uzlu byly získány
        visited.add(current.node);

        // Průchod přes dostupné hrany, pouze pokud je dostatečný rozpočet
        for (Edge edge : current.node.edges) {
            if (current.budget >= edge.cost) {
                int newBudget = current.budget - edge.cost + edge.target.resource;
                queue.add(new State(edge.target, current.time + 1, newBudget, current.resources));
            }
        }
    }
}