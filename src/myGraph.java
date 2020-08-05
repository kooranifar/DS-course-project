// Java code to demonstrate Graph representation
// using ArrayList in Java

import java.util.*;

public class myGraph {

    LinkedList<Node> redNodes;
    LinkedList<Node> blueNodes;
    int[][] adjMat;
    LinkedList<Node> adj;
    Boolean visited[];
    ArrayList<Edge> Edges; // stores edges. useful for weighted graphs.
    ArrayList<ArrayList<Node>> Sets; // moallefe haye hambandi e graph injaan


    myGraph (){
        this.adj = new LinkedList<>();
        this.Edges = new ArrayList<>();
        this.Sets = new ArrayList<>();
        this.redNodes = new LinkedList<>();
        this.blueNodes = new LinkedList<>();
    }

    myGraph (int v){
        this.adjMat = new int[v][v];
        this.visited = new Boolean[v];
        for (int i = 0; i < v; i++) {
            this.visited[i] = false;
        }
        this.adj = new LinkedList<>();
        for (int i=0; i<v; i++){
            Node node = new Node(i);
            this.adj.add(node);
        }
        this.Edges = new ArrayList<>();
        this.Sets = new ArrayList<>();

        // at first every Node is a moallefe ye hambandi
        for (int i =0; i<v; i++){
            ArrayList<Node> this_set = new ArrayList<>();
            this_set.add(this.adj.get(i));
            this.Sets.add(this_set);
        }
    }

    public void inputWeightedGraph(){
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int e = scanner.nextInt();

        /*inja chiz hayi ke too constructor boodan vali
         * niaaz be int v baraye saxte shodan dashtan ro misazim.
         * baqiasho hamoonja too constructor negah dashtam
         * va constructor az Graph(int v) be shek e Graph() dar oomad.*/

        this.adjMat = new int[v][v];

        this.visited = new Boolean[v];
        for (int i = 0; i < v; i++) {
            this.visited[i] = false;
        }
        this.adj = new LinkedList<>();
        for (int i=0; i<v; i++){
            Node node = new Node(i);
            this.adj.add(node);
        }
        this.Edges = new ArrayList<>();
        this.Sets = new ArrayList<>();

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

            this.adjMat[v1][v2] = weight;
            this.addEdge(v1, v2, weight);
        }
    }

    public void inputUnweightedGraph(){
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int e = scanner.nextInt();

        /*inja chiz hayi ke too constructor boodan vali
         * niaaz be int v baraye saxte shodan dashtan ro misazim.
         * baqiasho hamoonja too constructor negah dashtam
         * va constructor az Graph(int v) be shek e Graph() dar oomad.*/

        this.adjMat = new int[v][v];

        this.visited = new Boolean[v];
        for (int i = 0; i < v; i++) {
            this.visited[i] = false;
        }
        this.adj = new LinkedList<>();
        for (int i=0; i<v; i++){
            Node node = new Node(i);
            this.adj.add(node);
        }
        this.Edges = new ArrayList<>();
        this.Sets = new ArrayList<>();

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
            this.adjMat[v1][v2] = 1;
            this.addEdge(v1, v2, 1);
        }
    }

    public myGraph networkFlow(){
        myGraph g = new myGraph();

        int v = this.adj.size() + 2;
        int e = this.Edges.size() + this.blueNodes.size() + this.redNodes.size();

        /*inja chiz hayi ke too constructor boodan vali
         * niaaz be int v baraye saxte shodan dashtan ro misazim.
         * baqiasho hamoonja too constructor negah dashtam
         * va constructor az Graph(int v) be shek e Graph() dar oomad.*/

        g.adjMat = new int[v][v];

        g.visited = new Boolean[v];
        for (int i = 0; i < v; i++) {
            g.visited[i] = false;
        }
        g.adj = new LinkedList<>();
        for (int i=0; i<v; i++){
            Node node = new Node(i);
            g.adj.add(node);
        }
        g.Edges = new ArrayList<>();
        g.Sets = new ArrayList<>();

        // at first every Node is a moallefe ye hambandi
        for (int i =0; i<v; i++){
            ArrayList<Node> this_set = new ArrayList<>();
            this_set.add(g.adj.get(i));
            g.Sets.add(this_set);
        }

        /*ta inja oon dastan e balast.
         * */

        // add edge from source to first part of the graph
        for (Node redNode : this.redNodes) {
            g.adjMat[0][redNode.value + 1] = 1;
            g.addEdge(0, redNode.value + 1, 1);
        }

        // add edge from sink to second part of the graph
        for (Node blueNode : this.blueNodes) {
            g.adjMat[blueNode.value + 1][v-1] = 1;
            g.addEdge(blueNode.value + 1, v-1, 1);
        }

        // rest of the graph is the same as main graph
        for (int i = 0; i < v - 2; i++) {
            for (int j = 0; j < v - 2; j++) {
                g.adjMat[i+1][j+1] = this.adjMat[i][j];
            }
        }
        for (Edge edge : this.Edges) {
            g.addEdge(edge.src.value + 1, edge.dest.value + 1, 1);
        }

        return g;
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
            for (int j = 0; j < this.adj.size(); j++) {
                System.out.print(this.adjMat[i][j] + " ");
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

    public boolean isBipartite() {
        for (int i = 0; i < this.Sets.size(); i++) {

            Queue<Node> q = new LinkedList<>();
            q.add(this.Sets.get(i).get(0)); // add first node of each partition to the queue

            while (!q.isEmpty()) {
                Node this_node = q.remove();
                for (Node neighbour : this_node.getNeighbours()) {
                    if (neighbour.color == null) {
                        if (this_node.color == NodeColor.BLUE){
                            neighbour.color = NodeColor.RED;
                            this.redNodes.add(neighbour);
                        }
                        else {
                            neighbour.color = NodeColor.BLUE;
                            this.blueNodes.add(neighbour);
                        }
                        q.add(neighbour);
                    } else if (neighbour.color.equals(this_node.color)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // changes the direction of all edges from RED edges to BLUE edges.
    public void improveDirections() {

        for (Edge edge : this.Edges) {
            Node src = edge.src;
            Node dest = edge.dest;
            if ( this.blueNodes.contains(src) && this.redNodes.contains(dest)) {
                // Edge should be reversed
                edge.setSrc(dest);
                edge.setDest(src);

                this.adjMat[src.value][dest.value] = 0;
                this.adjMat[dest.value][src.value] = edge.weight;
            }
        }
    }

    boolean BFS(int[][] rGraph, int t, int[] parent)
    {
        boolean[] visited = new boolean[this.adj.size()];
        for(int i=0; i<this.adj.size(); ++i)
            visited[i]=false;

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        parent[0]=-1;

        while (queue.size()!=0)
        {
            int u = queue.poll();

            for (int v=0; v<this.adj.size(); v++)
            {
                if (!visited[v] && rGraph[u][v] > 0)
                {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return (visited[t]);
    }

    // for Project3
    public void fordFulkerson()
    {
        int u, v;

        // creating the residual graph and filling it
        int[][] rGraph = new int[this.adj.size()][this.adj.size()];

        for (u = 0; u < this.adj.size(); u++)
            for (v = 0; v < this.adj.size(); v++)
                rGraph[u][v] = this.adjMat[u][v];

        // masiri ke az source ta sink bedast miad ro inja negahdaari mikonim,
        int[] parent = new int[this.adj.size()];

        int max_flow = 0;

        while (BFS(rGraph, this.adj.size()-1 , parent))
        {

            int path_flow = Integer.MAX_VALUE;
            for (v=this.adj.size()-1; v!=0; v=parent[v])
            {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual graph
            for (v=this.adj.size()-1; v != 0; v=parent[v])
            {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;

                this.getEdge(this.getNode(u), this.getNode(v)).alterVisited();
            }

            // add path flow to overall flow
            max_flow += path_flow;
        }
    }

}