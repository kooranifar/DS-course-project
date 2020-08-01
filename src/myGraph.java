// Java code to demonstrate Graph representation
// using ArrayList in Java

import java.lang.reflect.Array;
import java.util.*;

public class myGraph {

    LinkedList<Node> adj;

    Boolean visited[];
    ArrayList<Edge> Edges; // stores edges. useful for weighted graphs.
    ArrayList<ArrayList<Node>> Sets; // moallefe haye hambandi e graph injaan

    myGraph (){

        this.adj = new LinkedList<>();
        this.Edges = new ArrayList<>();
        this.Sets = new ArrayList<>();

    }

    void inputGraph(){
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int e = scanner.nextInt();

        /*inja chiz hayi ke too constructor boodan vali
        * niaaz be int v baraye saxte shodan dashtan ro misazim.
        * baqiasho hamoonja too constructor negah dashtam
        * va constructor az Graph(int v) be shek e Graph() dar oomad.*/

        this.visited = new Boolean[v];
        for (int i = 0; i < v; i++) {
            this.visited[i] = false;
        }
        for (int i=0; i<v; i++){
            Node node = new Node(i);
            this.adj.add(node);
        }
        // at first every Node is a moallefe ye hambandi
        for (int i =0; i<v; i++){
            ArrayList<Node> this_set = new ArrayList<>();
            this_set.add(this.adj.get(i));
            this.Sets.add(this_set);
        }

        /*ta inja oon dastan e balast.
        * */

        for (int i=0; i<e; i++){
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            int weight = scanner.nextInt();
            this.addEdge(v1, v2, weight);
        }
    }

    // merge mikone in 2 moallefe ye hambandi ro
    void mergeSets(ArrayList<Node> moallefe1, ArrayList<Node> moallefe2){
        System.out.println("first index is: " + this.Sets.indexOf(moallefe1));
        System.out.println("second index is: " + this.Sets.indexOf(moallefe2));
        ArrayList<Node> result = moallefe1;
        for (int i=0; i<moallefe2.size();i++){
            result.add(moallefe2.get(i));
        }
        System.out.print("result is: ");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i).value + " ");
        }
        System.out.println();
        this.Sets.remove(this.Sets.indexOf(moallefe1));
        this.Sets.remove(this.Sets.indexOf(moallefe2));
        this.Sets.add(result);
    }

    // hame ye chizayi ke vasl an be node ro barmigardoone
    int moallefeOf(Node node){
        for (int i=0; i<this.Sets.size();i++){
            if (this.Sets.get(i).contains(node)){
                return i;
            }
        }
        return -1; // return NULL (this line is never reached!)
    }

    void showSets(){
        System.out.println("sets have size " + this.Sets.size() + " and they are: ");
        for (int i = 0; i < this.Sets.size(); i++) {
            for (int j = 0; j < this.Sets.get(i).size(); j++) {
                System.out.print(this.Sets.get(i).get(j).value + " ");
            }
            System.out.println();
        }
    }

    void sortEdges(){ // sorts Edges by weight.
        for (int i=0; i<this.Edges.size(); i++){
            for (int j=0; j<this.Edges.size()-i-1; j++){
                if (Edges.get(j).weight > Edges.get(j+1).weight){
                    Edge tmp = this.Edges.get(j+1);
                    this.Edges.set(j + 1, this.Edges.get(j));
                    this.Edges.set(j, tmp);
                }
            }
        }
    }


    void addEdge(int u, int v, int weight) {
        this.adj.get(u).addNeighbour(this.adj.get(v));
        this.adj.get(v).addNeighbour(this.adj.get(u));

        this.getNode(u).incDegree();
        this.getNode(v).incDegree();

        Edge this_edge = new Edge(this.getNode(u), this.getNode(v), weight);
        this.Edges.add(this_edge);

        this.getNode(u).addEdge(this_edge);
        this.getNode(v).addEdge(this_edge);
    }

    // A utility function to print the adjacency list
    // representation of graph

    void printGraph()
    {
        for (int i = 0; i < this.adj.size(); i++) {
            System.out.println("\nAdjacency list of vertex" + i);
            for (int j = 0; j < this.adj.get(i).getNeighbours().size(); j++) {
                System.out.print(" -> "+ this.adj.get(i).getNeighbours().get(j).getValue());
            }
            System.out.println();
        }
    }

    Edge getEdge(Node n, Node m){
        Edge result = this.Edges.get(0); // xatarnaak!
        for (int i = 0; i < this.Edges.size(); i++) {
            Edge this_edge = this.Edges.get(i);
            if (this_edge.src.value == n.value && this_edge.dest.value == m.value)
                result = this_edge;
            if (this_edge.dest.value == n.value && this_edge.src.value == m.value)
                result = this_edge;
        }
        return result;
    }

    Node getNode(int v){
        return this.adj.get(v);
    }

    void recreateVisited(){
        this.visited = new Boolean[this.adj.size()];
    }

    public Boolean[] getVisited() {
        return visited;
    }
}