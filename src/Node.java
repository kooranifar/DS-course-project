import java.util.ArrayList;
import java.util.LinkedList;

public class Node implements Comparable<Node> {
    boolean visited;
    int value;
    int degree;
    Node precedent; // used in shortest Path
    int pathLengthFromSource; // used in shortest Path
    LinkedList<Node> neighbours;
    LinkedList<Node> connectedNodes; // stores all connected nodes to the node
    LinkedList<Edge> connectedEdges; // stores all connected edges to the node

    public Node(int v) {
        this.visited = false;
        this.degree = 0;
        this.neighbours = new LinkedList<>();
        this.value = v;
        this.connectedNodes = new LinkedList<>();
        this.connectedEdges = new LinkedList<>();
        this.connectedNodes.add(this);
        this.pathLengthFromSource = 1000000000; // ye adad e xeili bozorg!
    }

    void addNeighbour(Node v) {
        this.neighbours.add(v);
    }

    void addEdge(Edge edge){ this.connectedEdges.add(edge); }

    public LinkedList<Node> getNeighbours() {
        return neighbours;
    }

    public int getValue() {
        return value;
    }

//    @Override
//    public int compareTo(Node node) {
//        if (this.pathLengthFromSource < node.pathLengthFromSource){
//            return -1;
//        }
//        else if (this.pathLengthFromSource > node.pathLengthFromSource){
//            return 1;
//        }
//        return 0;
//    }

    @Override
    public int compareTo(Node node) {
        if (this.degree < node.degree){
            return -1;
        }
        else if (this.degree > node.degree){
            return 1;
        }
        return 0;
    }

    public void setPathLengthFromSource(int pathLengthFromSource) {
        this.pathLengthFromSource = pathLengthFromSource;
    }

    public void setPrecedent(Node precedent) {
        this.precedent = precedent;
    }

    void incDegree (){
        this.degree += 1;
    }
}