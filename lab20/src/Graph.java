import java.util.*;

/* A mutable and finite Graph object. Edge labels are stored via a HashMap
   where labels are mapped to a key calculated by the following. The graph is
   undirected (whenever an Edge is added, the dual Edge is also added). Vertices
   are numbered starting from 0. */
public class Graph {

    /* Maps vertices to a list of its neighboring vertices. */
    private HashMap<Integer, Set<Integer>> neighbors = new HashMap<>();
    /* Maps vertices to a list of its connected edges. */
    private HashMap<Integer, Set<Edge>> edges = new HashMap<>();
    /* A sorted set of all edges. */
    private TreeSet<Edge> allEdges = new TreeSet<>();

    /* Returns the vertices that neighbor V. */
    public TreeSet<Integer> getNeighbors(int v) {
        return new TreeSet<Integer>(neighbors.get(v));
    }

    /* Returns all edges adjacent to V. */
    public TreeSet<Edge> getEdges(int v) {
        return new TreeSet<Edge>(edges.get(v));
    }

    /* Returns a sorted list of all vertices. */
    public TreeSet<Integer> getAllVertices() {
        return new TreeSet<Integer>(neighbors.keySet());
    }

    /* Returns a sorted list of all edges. */
    public TreeSet<Edge> getAllEdges() {
        return new TreeSet<Edge>(allEdges);
    }

    /* Adds vertex V to the graph. */
    public void addVertex(Integer v) {
        if (neighbors.get(v) == null) {
            neighbors.put(v, new HashSet<Integer>());
            edges.put(v, new HashSet<Edge>());
        }
    }

    /* Adds Edge E to the graph. */
    public void addEdge(Edge e) {
        addEdgeHelper(e.getSource(), e.getDest(), e.getWeight());
    }

    /* Creates an Edge between V1 and V2 with no weight. */
    public void addEdge(int v1, int v2) {
        addEdgeHelper(v1, v2, 0);
    }

    /* Creates an Edge between V1 and V2 with weight WEIGHT. */
    public void addEdge(int v1, int v2, int weight) {
        addEdgeHelper(v1, v2, weight);
    }

    /* Returns true if V1 and V2 are connected by an edge. */
    public boolean isNeighbor(int v1, int v2) {
        return neighbors.get(v1).contains(v2) && neighbors.get(v2).contains(v1);
    }

    /* Returns true if the graph contains V as a vertex. */
    public boolean containsVertex(int v) {
        return neighbors.get(v) != null;
    }

    /* Returns true if the graph contains the edge E. */
    public boolean containsEdge(Edge e) {
        return allEdges.contains(e);
    }

    /* Returns if this graph spans G. */
    public boolean spans(Graph g) {
        TreeSet<Integer> all = getAllVertices();
        if (all.size() != g.getAllVertices().size()) {
            return false;
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> vertices = new ArrayDeque<>();
        Integer curr;

        vertices.add(all.first());
        while ((curr = vertices.poll()) != null) {
            if (!visited.contains(curr)) {
                visited.add(curr);
                for (int n : getNeighbors(curr)) {
                    vertices.add(n);
                }
            }
        }
        return visited.size() == g.getAllVertices().size();
    }

    /* Overrides objects equals method. */
    public boolean equals(Object o) {
        if (!(o instanceof Graph)) {
            return false;
        }
        Graph other = (Graph) o;
        return neighbors.equals(other.neighbors) && edges.equals(other.edges);
    }

    /* A helper function that adds a new edge from V1 to V2 with WEIGHT as the
       label. */
    private void addEdgeHelper(int v1, int v2, int weight) {
        addVertex(v1);
        addVertex(v2);

        neighbors.get(v1).add(v2);
        neighbors.get(v2).add(v1);

        Edge e1 = new Edge(v1, v2, weight);
        Edge e2 = new Edge(v2, v1, weight);
        edges.get(v1).add(e1);
        edges.get(v2).add(e2);
        allEdges.add(e1);
    }

    public Graph prims(int start) {
        // 检查起始顶点是否存在
        if (!containsVertex(start)) {
            return null;
        }
        
        Graph result = new Graph();
        Set<Integer> inMST = new HashSet<>();
        
        // 存储每个顶点到MST的最小距离边
        HashMap<Integer, Edge> minEdgeToMST = new HashMap<>();
        
        // 初始化：将起始顶点加入MST
        result.addVertex(start);
        inMST.add(start);
        
        // 初始化其他顶点到MST的最小边
        for (Integer vertex : getAllVertices()) {
            if (!vertex.equals(start)) {
                minEdgeToMST.put(vertex, null);
            }
        }
        
        // 更新起始顶点的邻居
        for (Edge edge : getEdges(start)) {
            int neighbor = edge.getDest();
            if (!inMST.contains(neighbor)) {
                if (minEdgeToMST.get(neighbor) == null || 
                    edge.getWeight() < minEdgeToMST.get(neighbor).getWeight()) {
                    minEdgeToMST.put(neighbor, edge);
                }
            }
        }
        
        // 主循环：每次添加一个顶点到MST
        while (inMST.size() < getAllVertices().size()) {
            // 找到权重最小的边
            Edge minEdge = null;
            Integer nextVertex = null;
            
            for (Integer vertex : minEdgeToMST.keySet()) {
                if (!inMST.contains(vertex) && minEdgeToMST.get(vertex) != null) {
                    if (minEdge == null || 
                        minEdgeToMST.get(vertex).getWeight() < minEdge.getWeight()) {
                        minEdge = minEdgeToMST.get(vertex);
                        nextVertex = vertex;
                    }
                }
            }
            
            // 如果找不到边，说明图不连通
            if (minEdge == null) {
                return null;
            }
            
            // 将新顶点和边加入MST
            result.addVertex(nextVertex);
            result.addEdge(minEdge);
            inMST.add(nextVertex);
            
            // 更新其他顶点到MST的最小边
            for (Edge edge : getEdges(nextVertex)) {
                int neighbor = edge.getDest();
                if (!inMST.contains(neighbor)) {
                    if (minEdgeToMST.get(neighbor) == null || 
                        edge.getWeight() < minEdgeToMST.get(neighbor).getWeight()) {
                        minEdgeToMST.put(neighbor, edge);
                    }
                }
            }
        }
        
        return result;
    }
    
    public Graph kruskals() {
        Graph result = new Graph();
        
        // 添加所有顶点
        for (Integer vertex : getAllVertices()) {
            result.addVertex(vertex);
        }
        
        // 获取所有边并按权重排序
        List<Edge> sortedEdges = new ArrayList<>(getAllEdges());
        sortedEdges.sort(Comparator.comparingInt(Edge::getWeight));
        
        // 并查集实现
        UnionFind uf = new UnionFind(getAllVertices());
        
        // Kruskal算法主循环
        for (Edge edge : sortedEdges) {
            int src = edge.getSource();
            int dest = edge.getDest();
            
            // 如果两个顶点不在同一连通分量中，添加这条边
            if (!uf.connected(src, dest)) {
                uf.union(src, dest);
                result.addEdge(edge);
                
                // 如果MST已完成（有n-1条边），提前退出
                if (result.getAllEdges().size() == getAllVertices().size() - 1) {
                    break;
                }
            }
        }
        
        // 检查是否形成了完整的MST
        if (result.getAllEdges().size() != getAllVertices().size() - 1) {
            return null; // 图不连通
        }
        
        return result;
    }
    
    // 并查集辅助类
    private class UnionFind {
        private HashMap<Integer, Integer> parent;
        private HashMap<Integer, Integer> rank;
        
        public UnionFind(Set<Integer> vertices) {
            parent = new HashMap<>();
            rank = new HashMap<>();
            for (Integer vertex : vertices) {
                parent.put(vertex, vertex);
                rank.put(vertex, 0);
            }
        }
        
        public int find(int x) {
            if (!parent.get(x).equals(x)) {
                parent.put(x, find(parent.get(x))); // 路径压缩
            }
            return parent.get(x);
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                // 按秩合并
                if (rank.get(rootX) < rank.get(rootY)) {
                    parent.put(rootX, rootY);
                } else if (rank.get(rootX) > rank.get(rootY)) {
                    parent.put(rootY, rootX);
                } else {
                    parent.put(rootY, rootX);
                    rank.put(rootX, rank.get(rootX) + 1);
                }
            }
        }
        
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }
}
