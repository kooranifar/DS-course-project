import java.util.LinkedList;

public class Project4 {
    public static void main(String[] args) {

        myGraph graph = new myGraph();
        graph.inputGraph();
        LinkedList<Node> S = new LinkedList<>();

        while (! graph.Edges.stream().allMatch(edge -> edge.visited)){ // not all are visited.
            
            // find highest degree node that is still not visited.
            Node highest_degree_node = null;
            for (int i = 0; i < graph.adj.size(); i++) {
                Node this_node = graph.getNode(i);

                if (highest_degree_node == null){
                    if (! this_node.visited){
                        highest_degree_node = this_node;
                    }
                }
                else {
                    if (highest_degree_node.degree < this_node.degree){
                        if (! this_node.visited) { highest_degree_node = this_node; }
                    }
                }
            }

            // add it to S
            S.add(highest_degree_node);
            for (Edge edge :
                    highest_degree_node.connectedEdges) {
                edge.setVisited(true);
            }


        }

    }
}
