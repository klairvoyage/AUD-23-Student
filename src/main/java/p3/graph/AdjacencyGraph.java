package p3.graph;

import p3.SetUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of an immutable {@link Graph} that uses an {@link AdjacencyMatrix} to store the graph.
 * @param <N> the type of the nodes in this graph.
 */
public class AdjacencyGraph<N> implements Graph<N> {

    /**
     * The adjacency matrix that stores the graph.
     */
    private final AdjacencyMatrix matrix;

    /**
     * A map from nodes to their indices in the adjacency matrix.
     * Every node in the graph is mapped to a distinct index in the range [0, {@link #matrix}.size() -1].
     * This map is the inverse of {@link #indexNodes}.
     */
    private final Map<N, Integer> nodeIndices = new HashMap<>();

    /**
     * A map from indices in the adjacency matrix to the nodes they represent.
     * Every index in the range [0, {@link #matrix}.size() -1] is mapped to a distinct node in the graph.
     * This map is the inverse of {@link #nodeIndices}.
     */
    private final Map<Integer, N> indexNodes = new HashMap<>();

    /**
     * The nodes in this graph.
     */
    private final Set<N> nodes;

    /**
     * The edges in this graph.
     */
    private final Set<Edge<N>> edges;

    /**
     * Constructs a new {@link AdjacencyGraph} with the given nodes and edges.
     * @param nodes the nodes in the graph.
     * @param edges the edges in the graph.
     */
    public AdjacencyGraph(Set<N> nodes, Set<Edge<N>> edges) {
        matrix = new AdjacencyMatrix(nodes.size());
        this.nodes = SetUtils.immutableCopyOf(nodes);
        this.edges = SetUtils.immutableCopyOf(edges);

        // TODO H1 c): remove if implemented
        int index = 0;
        //iterates over each node and assigns it a distinct value
        for (N node : nodes) {
            nodeIndices.put(node, index); indexNodes.put(index, node); index++;
        }
        //iterates over each edge, pulling the indices of its two nodes & weight to then create the edge in the matrix
        for (Edge<N> edge : edges) matrix.addEdge(nodeIndices.get(edge.a()), nodeIndices.get(edge.b()), edge.weight());
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
        // TODO H1 c): remove if implemented
        Set<Edge<N>> adjacentEdges = new HashSet<>();
        //creates array with the weights of the edges adjacent to the given node
        int[] weights = matrix.getAdjacent(nodeIndices.get(node));
        for (int i=0;i<weights.length;i++) {
            if (weights[i]!=0) {
                N adjacentNode = indexNodes.get(i); int weight = weights[i];
                Edge<N> edge = new Edge<>() {
                    @Override
                    public N a() {
                        return node;
                    }

                    @Override
                    public N b() {
                        return adjacentNode;
                    }

                    @Override
                    public int weight() {
                        return weight;
                    }
                };
                //initialized edge is added to the set of adjacent edges
                adjacentEdges.add(edge);
            }
        }
        return adjacentEdges;
    }

    @Override
    public MutableGraph<N> toMutableGraph() {
        return MutableGraph.of(nodes, edges);
    }

    @Override
    public Graph<N> toGraph() {
        return this;
    }
}
