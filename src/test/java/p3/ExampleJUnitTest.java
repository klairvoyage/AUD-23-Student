package p3;

import org.junit.jupiter.api.Test;
import p3.graph.BasicGraph;
import p3.graph.Edge;
import p3.graph.Graph;
import p3.solver.DijkstraPathCalculator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * An example JUnit test class.
 */
public class ExampleJUnitTest {

    @Test
    public void testAddition() {
        assertEquals(2, 1 + 1);
    }

    /*@Test
    public void testDijkstraReconstructPath() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
        int[] nodes = new int[]{1, 2, 4, 6, 5, 8, 3, 9, 11};
        DijkstraPathCalculator<Integer> dijkstraPathCalculator = new DijkstraPathCalculator<>(createGraph(nodes));

        Method method = null;

        Method[] methods = dijkstraPathCalculator.getClass().getDeclaredMethods();

        Field field = dijkstraPathCalculator.getClass().getDeclaredField("predecessors");
        field.setAccessible(true);

        Map<Integer, Integer> predecessors = new HashMap<>();
        for (int i = 0; i < nodes.length - 1; i++) {
            predecessors.put(nodes[i+1], nodes[i]);
        }

        field.set(dijkstraPathCalculator, predecessors);

        for (Method method1 : methods) {
            if (method1.getName().equals("reconstructPath")) method = method1;
            System.out.println(method1.getName());
        }
        System.out.println(1);

        method.setAccessible(true);
        System.out.println(1);
        //method.invoke(dijkstraPathCalculator, 2, 3);
        System.out.println(1);
        System.out.println(method.invoke(dijkstraPathCalculator, 2, 3));

        List<Integer> list = (List<Integer>) method.invoke(dijkstraPathCalculator, 2, 3);

        assertArrayEquals(list.toArray(), new Object[]{2, 4, 6, 5, 8, 3});
    }

    private Graph<Integer> createGraph(int[] nodesArray) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> edgeImplClass = Class.forName("p3.graph.EdgeImpl");
        Constructor<?> constructor = edgeImplClass.getDeclaredConstructor(Object.class, Object.class, Integer.TYPE);
        constructor.setAccessible(true);

        Set<Integer> nodes = new HashSet<>();
        for (int j : nodesArray) {
            nodes.add(j);
        }

        Set<Edge<Integer>> edges = new HashSet<>();
        for (int i = 0; i < nodesArray.length-1; i++) {
            edges.add((Edge<Integer>) constructor.newInstance(nodesArray[i], nodesArray[i+1], (int) (Math.random()*10)));
        }

        return new BasicGraph<>(nodes, edges);
    }*/
}
