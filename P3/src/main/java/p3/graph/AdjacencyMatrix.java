package p3.graph;

/**
 * A light wrapper around a 2D array of integers that represents an adjacency matrix.
 *
 * <p>Invariants:</p>
 * <ul>
 *     <li><code>matrix.length == matrix[0].length</code> (square matrix)</li>
 *     <li><code>matrix[i][j] == matrix[j][i]</code> (symmetric matrix)</li>
 * </ul>
 */
public class AdjacencyMatrix {

    /**
     * The underlying array that stores the adjacency matrix.
     */
    private final int[][] matrix;

    /**
     * Constructs a new adjacency matrix with the given size.
     * @param size the size of the matrix.
     */
    public AdjacencyMatrix(int size) {
        matrix = new int[size][size];
    }

    /**
     * Adds an edge between the given indices with the given weight.
     *
     * @param a the index of the first node
     * @param b the index of the second node
     * @param weight the weight of the edge
     */
    public void addEdge(int a, int b, int weight) {
        // TODO H1 b): remove if implemented
        matrix[a][b] = weight;
        matrix[b][a] = weight;
    }

    /**
     * Returns the weight of the edge between the given indices.
     *
     * @param a the index of the first node
     * @param b the index of the second node
     * @return the weight of the edge between the given indices
     */
    public int getWeight(int a, int b) {
        // TODO H1 b): remove if implemented
        return matrix[a][b];
    }

    /**
     * Returns an array of the weights of the edges adjacent to the given index.
     *
     * @param index the index of the node to get the adjacent edges of
     * @return an array of the weights of the edges adjacent to the given index
     */
    public int[] getAdjacent(int index) {
        // TODO H1 b): remove if implemented
        return matrix[index];
    }
}
