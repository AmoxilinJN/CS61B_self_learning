import java.util.*;

public class Graph {

    private LinkedList<Edge>[] adjLists;
    private int vertexCount;
    private static int[] distTo;
    private static int[] edgeTo;

    /* Initializes a graph with NUMVERTICES vertices and no Edges. */
    public Graph(int numVertices) {
        adjLists = (LinkedList<Edge>[]) new LinkedList[numVertices];
        for (int k = 0; k < numVertices; k++) {
            adjLists[k] = new LinkedList<Edge>();
        }
        vertexCount = numVertices;
    }

    /* Adds a directed Edge (V1, V2) to the graph. That is, adds an edge
       in ONE directions, from v1 to v2. */
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, 0);
    }

    /* Adds an undirected Edge (V1, V2) to the graph. That is, adds an edge
       in BOTH directions, from v1 to v2 and from v2 to v1. */
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, 0);
    }

    /* Adds a directed Edge (V1, V2) to the graph with weight WEIGHT. If the
       Edge already exists, replaces the current Edge with a new Edge with
       weight WEIGHT. */
    public void addEdge(int v1, int v2, int weight) {
        // TODO: YOUR CODE HERE
        Edge temp = new Edge(v1,v2,weight);
        adjLists[v1].add(temp);
    }

    /* Adds an undirected Edge (V1, V2) to the graph with weight WEIGHT. If the
       Edge already exists, replaces the current Edge with a new Edge with
       weight WEIGHT. */
    public void addUndirectedEdge(int v1, int v2, int weight) {
        // TODO: YOUR CODE HERE
        Edge temp1 = new Edge(v1,v2,weight);
        Edge temp2 = new Edge(v2,v1,weight);
        adjLists[v1].add(temp1);
        adjLists[v2].add(temp2);
    }

    /* Returns true if there exists an Edge from vertex FROM to vertex TO.
       Returns false otherwise. */
    public boolean isAdjacent(int from, int to) {
        // TODO: YOUR CODE HERE
        for (int i = 0; i < vertexCount; i++) {
            for (Edge temp : adjLists[i]) {
                if (temp.from == from && temp.to == to) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns a list of all the vertices u such that the Edge (V, u)
       exists in the graph. */
    public List<Integer> neighbors(int v) {
        // TODO: YOUR CODE HERE
        ArrayList<Integer> sum = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            if (i == v) {
                continue;
            }
            if (isAdjacent(v,i)) {
                sum.add(i);
            }
        }
        return sum;
    }
    /* Returns the number of incoming Edges for vertex V. */
    public int inDegree(int v) {
        // TODO: YOUR CODE HERE
        int count = 0;
        for (int i = 0; i < vertexCount; i++) {
            if (i == v) {
                continue;
            }
            if (isAdjacent(i,v)) {
                count++;
            }
        }
        return count;
    }

    /* Returns a list of the vertices that lie on the shortest path from start to stop. 
    If no such path exists, you should return an empty list. If START == STOP, returns a List with START. */
    public ArrayList<Integer> shortestPath(int start, int stop) {
        // TODO: YOUR CODE HERE
        ArrayList<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new customComparator());
        distTo = new int[vertexCount];
        edgeTo = new int[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            edgeTo[i] = -1;
            distTo[i] = -1;
        }
        if (start == stop) {
            return new ArrayList<>(Arrays.asList(start));
        }
        distTo[start] = 0;
        shortPathHelper(edgeTo,distTo,pq,start,start);
        shortPathAnswer(result,edgeTo,stop);
        return result;
    }

    private void shortPathHelper(int[] edgeTo, int[] distTo, PriorityQueue<Integer> pq, int node, int start) {
        //int temp = (int) pq.peek();
        if (neighbors(node) != null) {
            for (int i : neighbors(node)) {
                if (edgeTo[i] == -1) {
                    if (i == start) {
                        continue;
                    }
                    distTo[i] = getEdge(node, i).weight + distTo[node];
                    edgeTo[i] = node;
                    pq.add(i);
                } else {
                    int temp = distTo[node] + getEdge(node,i).weight;
                    if (temp < distTo[i]) {
                        distTo[i] = temp;
                        edgeTo[i] = node;
                        pq.add(i);
                    }
                }
            }
        }
        if (pq.isEmpty()) {
            return;
        }
        shortPathHelper(edgeTo,distTo,pq,(int)pq.poll(),start);
    }

    private void shortPathAnswer(ArrayList<Integer> result, int[] edgeTo, int index) {
        result.addFirst(index);
        if (edgeTo[index] == -1) {
            return;
        }
        shortPathAnswer(result,edgeTo,edgeTo[index]);
    }

    private Edge getEdge(int v1, int v2) {
        // TODO: YOUR CODE HERE
        for (int i = 0; i < vertexCount; i++) {
            for (Edge temp : adjLists[i]) {
                if (temp.from == v1 && temp.to == v2) {
                    return temp;
                }
            }
        }
        return null;
    }

    private class customComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return distTo[o1] - distTo[o2];
        }
    }

    private class Edge {

        private int from;
        private int to;
        private int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public String toString() {
            return "(" + from + ", " + to + ", weight = " + weight + ")";
        }

        public int to() {
            return this.to;
        }

    }

    
}