package p3.solver;

import p3.graph.Edge;
import p3.graph.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

/**
 * Implementation of Dijkstra's algorithm, a single-source shortest path algorithm.
 *
 * @param <N> The type of the nodes in the graph.
 */
public class DijkstraPathCalculator<N> implements PathCalculator<N> {

    /**
     * Factory for creating new instances of {@link DijkstraPathCalculator}.
     */
    public static PathCalculator.Factory FACTORY = DijkstraPathCalculator::new;

    /**
     * Tje graph to calculate paths in.
     */
    protected final Graph<N> graph;

    /**
     * The distance from the start node to each node in the graph.
     */
    protected final Map<N, Integer> distances = new HashMap<>();

    /**
     * The predecessor of each node in the graph along the shortest path to the start node.
     */
    protected final Map<N, N> predecessors = new HashMap<>();

    /**
     * The set of nodes that have not yet been visited.
     */
    protected final Set<N> remainingNodes = new HashSet<>();

    /**
     * Construct a new {@link DijkstraPathCalculator} for the given graph.
     * @param graph the graph to calculate paths in.
     */
    public DijkstraPathCalculator(Graph<N> graph) {
        this.graph = graph;
    }

    /**
     * Calculate the shortest path between two given nodes, {@code start} and {@code end}, using Dijkstra's algorithm.
     *
     * <p>
     * This method calculates the shortest path from {@code start} to all other nodes and saves the results
     * to {@link #distances} and {@link #predecessors}.
     * </p>
     *
     * @param start the start node, first node in the returned list
     * @param end   the end node, last node in the returned list
     * @return a list of nodes, from {@code start} to {@code end}, in the order they need to be traversed to get the
     * shortest path between those two nodes
     */
    @Override
    public List<N> calculatePath(final N start, final N end) {
        // TODO H3 e): remove if implemented //
        init(start);
        while (!remainingNodes.isEmpty()) {
            N current = extractMin();
            if (current.equals(end)) break;
            for (Edge<N> edge : graph.getAdjacentEdges(current)) {
                N adjacent = current.equals(edge.a()) ? edge.b() : edge.a();
                relax(current, adjacent, edge);
            }
        }
        return reconstructPath(start, end);
    }

    /**
     * Initializes the {@link #distances} and {@link #predecessors} maps as well as the {@link #remainingNodes} set, i.e., resets and repopulates them with
     * default values. The default value for {@link #distances} is {@code 0} for the start node and {@link Integer#MAX_VALUE} for every other node
     * and the default value for {@link #predecessors} is {@code null} for every node.
     *
     * @param start the start node
     */
    protected void init(N start) {
        // TODO H3 a): remove if implemented
        distances.clear();
        predecessors.clear();
        remainingNodes.clear();
        for (N node : graph.getNodes()) {
            if (node.equals(start)) distances.put(node, 0);
            else distances.put(node, Integer.MAX_VALUE);
            predecessors.put(node, null);
            remainingNodes.add(node);
        }
    }

    /**
     * Determines the next node from the set of remaining nodes that should be visited.
     * <p> This implementation returns the node with the minimal weight in the {@link #remainingNodes} set.
     *
     * @return the next unprocessed node with minimal weight
     */
    protected N extractMin() {
        // TODO H3 b): remove if implemented
        N minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (N node : remainingNodes) {
            int distance = distances.get(node);
            if (distance<minDistance) {
                minDistance = distance;
                minNode = node;
            }
        }
        remainingNodes.remove(minNode);
        return minNode;
    }

    /**
     * Updates the {@link #distances} and {@link #predecessors} maps if a shorter path between {@code from} and
     * {@code to} is found. If no shorter path is found, the maps remain unchanged.
     *
     * @param from the node that is used to reach {@code to}
     * @param to   the target node for this update
     * @param edge the edge between {@code from} and {@code to}.
     */
    protected void relax(N from, N to, Edge<N> edge) {
        // TODO H3 c): remove if implemented //
        int weight = edge.weight();
        int distance = distances.get(from) + weight;
        if (distance<distances.get(to)) {
            distances.put(to, distance);
            predecessors.put(to, from);
        }
    }

    /**
     * Reconstructs the shortest path from {@code start} to {@code end} by using the {@link #predecessors} map.
     * <p> The returned path contains {@code start} as the first element and {@code end} as the last element.
     *
     * @param start  the start node
     * @param end the end node
     * @return a list of nodes in the order they need to be traversed to get the shortest path from the start node to the end node.
     */
    protected List<N> reconstructPath(N start, N end) {
        // TODO H3 d): remove if implemented //
        List<N> path = new ArrayList<>();
        N current = end;
        while (current!=null) {
            path.add(0, current);
            current = predecessors.get(current);
        }
        return path;
    }
}
