import java.util.*;

class Edge implements Comparable<Edge>{
    int from; // vertex start
    int to;   // vertex end
    double weight;

    Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    // comparing the eddges based on the weights
    @Override
    public int compareTo(Edge edge) {
        if (weight != edge.weight) {
            // sorting the weight in the ascending order
            if (weight > edge.weight) {
                return 1;
            } else {
                return -1;
            }
        }
        // this will return if the weights are equal making the edges equal as well
        return 0;
    }
}

public class MST {
    // this is a method to calculate the total edge weight of the graph
    public static double totalEdgeWeight(Graph g) {
        double TotalWeight = 0;

        // all vertices iterated through
        for (int i = 0; i < g.numVertices(); i++) {
            // get all out neighbors of vertex i
            int[] nbs = g.outNeighbours(i);

            // iterate through all neighbors of vertex i
            for (int nb : nbs) {
                // to avoid double counting the weight is added only when i is less than neighbor
                if (i < nb) {
                    // weight of edge added
                    TotalWeight += g.weight(i, nb);
                }
            }
        }
        return TotalWeight;
    }

    // this is a method to count the number of connected components in the graph
    public static int numberOfComponents(Graph g) {
        int numComponents = 0;
        // visited vertices is tracked
        boolean[] visiting = new boolean[g.numVertices()];

        // each vertex is being checked
        for (int j = 0; j < g.numVertices(); j++) {
            // if the vertex is not visited then it means its a new component
            if (!visiting[j]) {
                numComponents++;
                // depth first search (dfs) performs the search to mark the component
                dfs(g, j, visiting);
            }
        }
        return numComponents;
    }

    // helper method to explore the graph
    private static void dfs(Graph g, int j, boolean[] visiting) {
        // marks it as the vertex is visited
        visiting[j] = true;

        // visits all out-neighbours of vertex j
        for (int nb : g.outNeighbours(j)) {
            if (!visiting[nb]) {
                // recursion for unvisited neighbors
                dfs(g, nb, visiting);
            }
        }
    }

    // this is a method to generate a random undirected graph with grid structure
    public static Graph getRandomGraph(int x, int y, double p) {
        // calculate total number of vertices
        int vertices = x * y;
        Graph g = new MatrixGraph(vertices, Graph.UNDIRECTED_GRAPH);

        Random random = new Random();

        // iterates over the grid structure to create edges
        for (int k = 0; k < x; k++) {
            for (int l = 0; l < y; l++) {
                // current vertex index
                int current = k * y + l;

                // adding edge to the right neighbor with probability p
                if (l < y - 1) {
                    // right neighbor index
                    int right = current + 1;
                    if (random.nextDouble() < p) {
                        double weight = random.nextDouble();
                        g.addEdge(current, right, weight);
                    }
                }

                // addign edge to the below neighbor with the probability p
                if (k < x - 1) {
                    // below neighbor index
                    int below = current + y;
                    if (random.nextDouble() < p) {
                        double weight = random.nextDouble();
                        g.addEdge(current, below, weight);
                    }
                }
            }
        }
        return g;
    }

    // this is a method to preprocess the graph and remove degree-1 edges
    private static List<Edge> preprocess(Graph g) {
        // list to store removed edges
        List<Edge> edges = new ArrayList<>();
        // queue to process degree-1 vertices
        Queue<Integer> queue = new LinkedList<>();

        // all degree-1 vertices are added to the queue
        for (int m = 0; m < g.numVertices(); m++) {
            if (g.degree(m) == 1) {
                // adding vertex to queue if its degree is 1
                queue.add(m);
            }
        }

        // processing the queue
        while (!queue.isEmpty()) {
            // get the next vertex to process
            int vertex = queue.poll();

            // checking again to ensure degree is still 1
            if (g.degree(vertex) == 1) {
                int nb = g.outNeighbours(vertex)[0];
                edges.add(new Edge(vertex, nb, g.weight(vertex, nb)));
                g.deleteEdge(vertex, nb);

                // if the neighbor becomes degree-1, it adds to the queue
                if (g.degree(nb) == 1) {
                    queue.add(nb);
                }
            }
        }
        return edges;
    }

    public static void spanningTree(Graph g) {
        // preprocess and removal of degree-1 edges
        List<Edge> edgeList = preprocess(g);
        List<Edge> edges = getAllEdges(g);

        // this is done to get all edges in the graph
        List<Edge> allEdges = getAllEdges(g);

        // Sort edges in descending order of weight using Comparator
        allEdges.sort(Collections.reverseOrder());

        int numberOfComponents = numberOfComponents(g);

        // this is an attempt to remove edges while maintaining graph connectivity
        for (Edge edge : allEdges) {
            g.deleteEdge(edge.from, edge.to);
            int current = numberOfComponents(g);

            if (current != numberOfComponents) {
                g.addEdge(edge.from, edge.to, edge.weight);
            }
        }

        // re adding the preprocessed edges to the graph
        for (Edge edge : edgeList) {
            g.addEdge(edge.from, edge.to, edge.weight);
        }
    }

    // this is a helper method to collect all edges from the graph
    private static List<Edge> getAllEdges(Graph g) {
        // list to store edges
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < g.numVertices(); i++) {
            for (int nb : g.outNeighbours(i)) {
                if (i < nb) {
                    edges.add(new Edge(i, nb, g.weight(i, nb)));
                }
            }
        }
        return edges;
    }

    public static void main(String[] args) {
        System.out.println("Part (a): Graph of Essex");
        // loading the graph of essex
        Graph essexGraph = GraphOfEssex.getGraph();

        // total edge weight of the original graph
        double totalWeight = totalEdgeWeight(essexGraph);
        System.out.println("Total edge weight of Graph of Essex: " + totalWeight);

        // validate preprocessing
        List<Edge> preprocessedEdges = preprocess(essexGraph);
        System.out.println("Preprocessed Edges:");
        for (Edge edge : preprocessedEdges) {
            System.out.println("Edge from " + edge.from + " to " + edge.to + " with weight " + edge.weight);
        }

        // compute and print MST
        spanningTree(essexGraph);
        double mstWeight = totalEdgeWeight(essexGraph);
        System.out.println("Total weight of MST of Graph of Essex: " + mstWeight);

        System.out.println("\nPart (b): Random Graphs Statistics");
        // number of random graphs
        int numGraphs = 1000;
        // dimensions of the grid
        int x = 10, y = 10;
        // edge probability
        double p = 2.0 / 3;

        double totalGraphWeight = 0;
        double totalPreprocessedWeight = 0;
        double totalComponents = 0;

        for (int i = 0; i < numGraphs; i++) {
            Graph randomGraph = getRandomGraph(x, y, p);

            // calculating total weight before preprocessing
            double graphWeight = totalEdgeWeight(randomGraph);
            totalGraphWeight += graphWeight;

            // counting components before preprocessing
            int components = numberOfComponents(randomGraph);
            totalComponents += components;

            // preprocessing the graph and computing total weight after preprocessing
            preprocessedEdges = preprocess(randomGraph);
            double preprocessedWeight = totalEdgeWeight(randomGraph);
            totalPreprocessedWeight += preprocessedWeight;
        }

        double avgGraphWeight = totalGraphWeight / numGraphs;
        double avgPreprocessedWeight = totalPreprocessedWeight / numGraphs;
        double avgComponents = totalComponents / numGraphs;

        System.out.println("Average total edge weight before preprocessing: " + avgGraphWeight);
        System.out.println("Average weight after preprocessing: " + avgPreprocessedWeight);
        System.out.println("Average number of components: " + avgComponents);

        System.out.println("\nPart (c): Random Graph MST Weights");
        double totalMstWeight = 0;

        for (int i = 0; i < numGraphs; i++) {
            Graph randomGraph = getRandomGraph(x, y, p);
            spanningTree(randomGraph);
            totalMstWeight += totalEdgeWeight(randomGraph);
        }

        double avgMstWeight = totalMstWeight / numGraphs;
        System.out.println("Average total edge weight of MSTs: " + avgMstWeight);
    }
}
