package graphing;

public class Graph 
{
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    
    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * @param  V number of vertices
     */
    public Graph() 
    {
        this.V = 26;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
        //Hardcoded because nearby districts is based off of a district map of chicago
        addEdge(16, 17);
        addEdge(16, 25);
        addEdge(17, 24);
        addEdge(17, 19);
        addEdge(24, 20);
        addEdge(20, 19);
        addEdge(20, 23);
        addEdge(19, 23);
        addEdge(23, 18);
        addEdge(18, 14);
        addEdge(18, 1);
        addEdge(25, 14);
        addEdge(25, 15);
        addEdge(14, 13);
        addEdge(15, 11);
        addEdge(11, 13);
        addEdge(11, 10);
        addEdge(13, 12);
        addEdge(1, 12);
        addEdge(1, 21);
        addEdge(12, 10);
        addEdge(10, 8);
        addEdge(21, 9);
        addEdge(21, 2);
        addEdge(9, 8);
        addEdge(9, 7);
        addEdge(2, 3);
        addEdge(8, 7);
        addEdge(7, 3);
        addEdge(3, 4);
        addEdge(4, 5);
        addEdge(4, 6);
        addEdge(6, 5);
        addEdge(6, 22);
        addEdge(5, 22);
    }

    /**
     * Returns the number of vertices in this graph.
     * @return the number of vertices in this graph
     */
    public int V() 
    {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     * @return the number of edges in this graph
     */
    public int E() 
    {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) 
    {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the undirected edge v-w to this graph.
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) 
    {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }


    /**
     * Returns the vertices adjacent to vertex {@code v}.
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) 
    {
        validateVertex(v);
        return adj[v];
    }
}
