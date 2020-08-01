public class Edge {
    Node src, dest;
    int weight;
    boolean visited;
    Edge(Node src, Node dest, int weight){
        this.dest = dest;
        this.src = src;
        this.weight = weight;
        this.visited = false;
    }
}
