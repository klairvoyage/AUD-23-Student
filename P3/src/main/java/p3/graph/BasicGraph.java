package p3.graph;

import p3.SetUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * A basic implementation of an immutable {@link Graph} that uses a {@link Map} to store the edges that are adjacent to each node.
 * @param <N>
 */
public class BasicGraph<N> implements Graph<N> {

    /**
     * A map from nodes to the edges that are adjacent to them.
     * If a node has no adjacent edges, it is mapped to an empty set.
     */
    protected final Map<N, Set<Edge<N>>> backing;

    /**
     * The nodes in this graph.
     */
    protected final Set<N> nodes;

    /**
     * The edges in this graph.
     */
    protected final Set<Edge<N>> edges;

    /**
     * Constructs a new empty {@link BasicGraph}.
     */
    public BasicGraph() {
        this(Set.of(), Set.of());
    }

    /**
     * Constructs a new {@link BasicGraph} with the given nodes and edges.
     * @param nodes the nodes.
     * @param edges the edges.
     */
    public BasicGraph(Set<N> nodes, Set<Edge<N>> edges) {
        this.nodes = SetUtils.immutableCopyOf(nodes);
        this.edges = SetUtils.immutableCopyOf(edges);

        // TODO H1 a): remove if implemented
        backing = new HashMap<>();
        //iterates over each node which then receives an empty set of edges
        for (N node : nodes) backing.put(node, new HashSet<>());
        //iterates over each edge which is then added to the set of edges of its two respective nodes
        for (Edge<N> edge : edges) {
           backing.get(edge.a()).add(edge); backing.get(edge.b()).add(edge);
        }
    }

    @Override
    public Set<N> getNodes() {
        return nodes;
    }

    @Override
    public Set<Edge<N>> getEdges() {
        return edges;
    }

    @Override
    public Set<Edge<N>> getAdjacentEdges(N node) {
        Set<Edge<N>> result = backing.get(node);
        if (result == null) {
            throw new IllegalArgumentException("Node not found: " + node);
        }
        return result;
    }

    @Override
    public MutableGraph<N> toMutableGraph() {
        return MutableGraph.of(nodes, edges);
    }

    @Override
    public Graph<N> toGraph() {
        return this;
    }

    /**
     * An empty immutable {@link Graph}.
     */
    static Supplier<Graph<Object>> EMPTY = () -> new BasicGraph<>(Set.of(), Set.of());
}
