package avi.hackerrank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ShortestPathWithWeight {

    static class Graph {
        private final int vertices;
        
        /** Maps adjacent vertices to edge weight */
        private Map<Integer, Integer>[] edgeWeights;

        public Graph(int vertices) {
            this.vertices = vertices;
            
            @SuppressWarnings("unchecked")
            Map<Integer, Integer>[] a = (Map<Integer, Integer>[]) new Map[vertices];
            edgeWeights = a;
            for (int v = 0; v < vertices; v++) {
                edgeWeights[v] = new HashMap<Integer, Integer>();
            }
        }
        
        /**
         * Create a weighted graph from an input stream.
         */
        public Graph(Scanner scanner) {
            this(scanner.nextInt());
            int m = scanner.nextInt();
            for (int i = 0; i < m; i++) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                int d = scanner.nextInt();
                addEdge(v, w, d);
                addEdge(w, v, d);
            }
        }

        public int vertices() {
            return vertices;
        }
        
        public void addEdge(int from, int to, int weight) {
            edgeWeights[from].put(to, weight);
        }
        
        public int removeEdge(int from, int to) {
            edgeWeights[from].remove(to);
            return edgeWeights[to].remove(from);
        }
        
        public Map<Integer, Integer> adjacent(int v) {
            return edgeWeights[v];
        }
        
        public int weight(int from, int to) {
            return edgeWeights[from].get(to);
        }
        
        /**
         * Return a string representation of the digraph.
         */
        public String toString() {
            StringBuilder s = new StringBuilder();
            String NEWLINE = System.getProperty("line.separator");
            s.append(vertices + " " + NEWLINE);
            for (int v = 0; v < vertices; v++) {
                s.append(String.format("%d: ", v));
                for (Map.Entry<Integer, Integer> e : adjacent(v).entrySet()) {
                    s.append(String.format("%d (%d) ", e.getKey(), e.getValue()));
                }
                s.append(NEWLINE);
            }
            return s.toString();
        }
    }
    
    static class WeightedVertex {
        private int vertex;
        private int weight;
        
        public WeightedVertex(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
        public int vertex() { return vertex; }
        public int weight() { return weight; }

        @Override
        public String toString() {
            return "Vertex [vertex=" + vertex + ", weight=" + weight + "]";
        }
        
        public static final Comparator<WeightedVertex> MIN_WEIGHT_ORDER = new Comparator<WeightedVertex>() {
            public int compare(WeightedVertex v1, WeightedVertex v2) {
                return v1.weight < v2.weight ? -1 : 1;
            }
        };
    }
    
    static class ShortestPath {
        private int [] edgeFrom;
        private int [] minWeights;
        private int source;
        private boolean [] marked;
        
        public ShortestPath(Graph g, int source, int destination) {
            this.source = source;
            edgeFrom = new int [g.vertices()];
            minWeights = new int [g.vertices()];
            marked = new boolean [g.vertices()];
            
            for (int i = 0; i < g.vertices(); ++i) {
                minWeights[i] = Integer.MAX_VALUE;
            }
            minWeights[source] = 0;
            
            PriorityQueue<WeightedVertex> minWeightQueue = new PriorityQueue<WeightedVertex>(g.vertices(),
                    WeightedVertex.MIN_WEIGHT_ORDER);
            
            minWeightQueue.add(new WeightedVertex(source, 0));
            
            while (!minWeightQueue.isEmpty()) {
                WeightedVertex minVertex = minWeightQueue.remove();
                if (minVertex.vertex() == destination) break;
                
                int w = minWeights[minVertex.vertex()];
                
                for (Map.Entry<Integer, Integer> e : g.adjacent(minVertex.vertex()).entrySet()) {
                    int adjacentVertex = e.getKey();
                    int edgeWeight = e.getValue();
                    
                    if (marked[adjacentVertex]) continue;
                    
                    if (minWeights[adjacentVertex] > w + edgeWeight) {
                        WeightedVertex newWeightedVertex = new WeightedVertex(adjacentVertex, w + edgeWeight);
                        minWeights[adjacentVertex] = newWeightedVertex.weight();
                        edgeFrom[adjacentVertex] = minVertex.vertex();
                        minWeightQueue.add(newWeightedVertex);
                    }
                }
                
                marked[minVertex.vertex()] = true;
            }
        }
        
        public int minWeightTo(int v) {
            return minWeights[v];
        }
        
        public Iterable<Integer> pathTo(int v) {
            Deque<Integer> path = new LinkedList<Integer>();
            while (v != source) {
                path.addFirst(v);
                v = edgeFrom[v];
            }
            path.addFirst(source);
            return path;
        }
    }
    
    private static final int BOARD_SIZE = 100;
    private static final int FIRST_POSITION = 1;
    private static final int LAST_POSITION = 100;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner;
        if (args.length == 0) {
            scanner = new Scanner(new BufferedInputStream(System.in));
        } else {
            scanner = new Scanner(new BufferedInputStream(new FileInputStream(args[0])));
        }
        int t = scanner.nextInt();
        
        for (int i = 0; i < t; ++i) {
            Graph g = new Graph(BOARD_SIZE + 1);
            String line = scanner.next();
            String [] parts = line.split(",");
            int ladders = Integer.parseInt(parts[0]);
            int snakes = Integer.parseInt(parts[1]);
            for (int j = 0; j < ladders; ++j) {
                line = scanner.next();
                parts = line.split(",");
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                g.addEdge(from, to, 1);
            }
            for (int j = 0; j < snakes; ++j) {
                line = scanner.next();
                parts = line.split(",");
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                g.addEdge(from, to, 1);
            }
            for (int j = 1; j < BOARD_SIZE; ++j) {
                if (!g.adjacent(j).isEmpty()) {
                    continue;
                }
                for (int k = 1; k <= 6; ++k) {
                    if (j + k > BOARD_SIZE) {
                        break;
                    }
                    g.addEdge(j, j + k, 1);
                }
            }
            ShortestPath sp = new ShortestPath(g, FIRST_POSITION, LAST_POSITION);
            System.out.println(sp.minWeightTo(LAST_POSITION) - 1);
        }
    }
}