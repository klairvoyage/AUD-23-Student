package p3.solver;

import p3.graph.Edge;
import p3.graph.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<Edge<N>> edges = new ArrayList<>(graph.getEdges()); edges.sort(Edge::compareTo); //sort
        //iterates over each edge of the graph and add it to the MST if it meets the requirements
        for (Edge<N> edge : edges) if (acceptEdge(edge)) mstEdges.add(edge);
        return Graph.of(graph.getNodes(), mstEdges); //creates the MST
    }

    /**
     * Initializes the {@link #mstEdges} and {@link #mstGroups} with their default values.
     * <p> Initially, {@link #mstEdges} is empty and {@link #mstGroups} contains a set for each node in the graph.
     */
    protected void init() {
        // TODO H2 b): remove if implemented
        mstEdges.clear(); mstGroups.clear();
        //iterates over each node creating a group for each of them
        for (N node : graph.getNodes()) {
            Set<N> group = new HashSet<>(); group.add(node); mstGroups.add(group);
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
        int groupIndexA = -1; int groupIndexB = -1;
        for (int i=0;i<mstGroups.size();i++) {
            Set<N> group = mstGroups.get(i);
            //saves the index when both nodes of the edge are in the same group
            if (group.contains(edge.a())) groupIndexA = i; if (group.contains(edge.b())) groupIndexB = i;
        }
        if (groupIndexA==groupIndexB) return false;
        else joinGroups(groupIndexA, groupIndexB);
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
        Set<N> a = mstGroups.get(aIndex);
        Set<N> b = mstGroups.get(bIndex);
        //smaller group gets moved into the bigger one
        if (a.size()<b.size()) {
            b.addAll(a); mstGroups.remove(aIndex);
        } else {
            a.addAll(b); mstGroups.remove(bIndex);
        }
    }
}
