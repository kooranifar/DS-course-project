import org.graphstream.graph.Graph;

public class Project3 {
    public static void main(String[] args) {

        myGraph graph = new myGraph();
        graph.inputUnweightedGraph();

        if (! graph.isBipartite() ) {
            System.out.println("graph is not bipartite");
        }
        else {
            
        }
    }
}
