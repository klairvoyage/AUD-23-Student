package p3.solver;

import p3.graph.Edge;
import p3.graph.Graph;

import java.util.*;

/**
 * Implementation of Kruskal's algorithm, a minimum spanning tree algorithm.
 * @param <N> The type of the nodes in the graph.
 */
public class KruskalMSTCalculator<N> implements MSTCalculator<N> {

    /**
     * Factory for creating new instances of {@link KruskalMSTCalculator}.
     */
    public static MSTCalculator.Factory FACTORY = KruskalMSTCalculator::new;

    /**
     * The graph to calculate the MST for.
     */

    protected final Graph<N> graph;

    /**
     * The edges in the MST.
     */
    protected final Set<Edge<N>> mstEdges;

    /**
     * The groups of nodes in the MST.
     * <p> Each group is represented by a set of nodes. Initially, each node is in its own group. </p>
     * <p> When two nodes are in the same groups, they are in the same MST which is created by {@link #mstEdges}. </p>
     * <p> Every node is in exactly one group. </p>
     */
    protected final List<Set<N>> mstGroups;

    /**
     * Construct a new {@link KruskalMSTCalculator} for the given graph.
     * @param graph the graph to calculate the MST for.
     */
    public KruskalMSTCalculator(Graph<N> graph) {
        this.graph = graph;
        this.mstEdges = new HashSet<>();
        this.mstGroups = new ArrayList<>();
    }
    @Override
    public Graph<N> calculateMST() {
        // TODO H2 e): remove if implemented //
        init();
        List<Edge<N>> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);
        for (Edge<N> edge : edges) {
            if (acceptEdge(edge)) mstEdges.add(edge);
        }
        return Graph.of(graph.getNodes(), mstEdges);
    }

    /**
     * Initializes the {@link #mstEdges} and {@link #mstGroups} with their default values.
     * <p> Initially, {@link #mstEdges} is empty and {@link #mstGroups} contains a set for each node in the graph.
     */
    protected void init() {
        // TODO H2 b): remove if implemented
        mstEdges.clear();
        mstGroups.clear();
        for (N node : graph.getNodes()) {
            Set<N> group = new HashSet<>();
            group.add(node);
            mstGroups.add(group);
        }
    }

    /**
     * Processes an edge during Kruskal's algorithm.
     * <p> If the edge's nodes are in the same MST (group), the edge is skipped.
     * <p> If the edge's nodes are in different MSTs (groups), the groups are merged via the {@link #joinGroups(int, int)} method.
     *
     * @param edge The edge to process.
     * @return {@code true} if the edge was accepted and the two MST's were merged,
     * {@code false} if it was skipped.
     */
    protected boolean acceptEdge(Edge<N> edge) {
        // TODO H2 d): remove if implemented //
        N nodeA = edge.a();
        N nodeB = edge.b();
        int aIndex = -1;
        int bIndex = -1;
        for (int i=0;i<mstEdges.size();i++) {
            Set<N> group = mstGroups.get(i);
            if (group.contains(nodeA)) aIndex = i;
            if (group.contains(nodeB)) bIndex = i;
            if (aIndex!=-1 && bIndex==-1) break;
        }
        if (aIndex == bIndex) return false;
        else joinGroups(aIndex, bIndex);
        return true;
    }

    /**
     * Joins two sets in the list of all MST Groups.
     * <p> After joining the larger set will additionally contain all elements of the smaller set and
     * the smaller set will be removed from the list.
     *
     * @param aIndex The index of the first set to join.
     * @param bIndex The index of the second set to join.
     */
    protected void joinGroups(int aIndex, int bIndex) {
        // TODO H2 c): remove if implemented //
        Set<N> groupA = mstGroups.get(aIndex);
        Set<N> groupB = mstGroups.get(bIndex);
        if (groupA.size()<groupB.size()) {
            groupA.addAll(groupB);
            mstGroups.remove(bIndex);
        } else {
            groupB.addAll(groupA);
            mstGroups.remove(aIndex);
        }
    }
}
