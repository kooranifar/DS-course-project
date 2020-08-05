public class Edge {
    Node src, dest;
    int weight;
    int flow; // or also capacity (in project 3)
    boolean visited;
    Edge(Node src, Node dest, int weight){
        this.dest = dest;
        this.src = src;
        this.weight = weight;
        this.flow = 0;
        this.visited = false;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void alterVisited() {
        this.visited = !this.visited;
    }

    public void setDest(Node dest) {
        this.dest = dest;
    }

    public void setSrc(Node src) {
        this.src = src;
    }


}
