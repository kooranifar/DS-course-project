import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.LinkedList;
import java.util.Scanner;

public class Project4 {

    public void displayGraph(myGraph graph, LinkedList<Node> S){
        Graph g = new SingleGraph("Project 4");

        g.setStrict(false);
        g.setAutoCreate(true);
        g.display();

        for (int i = 0; i < graph.adj.size(); i++) {
            g.addNode(String.valueOf(graph.getNode(i).value));
        }
        for (Edge edge : graph.Edges) {
            g.addEdge(String.valueOf(edge.src.value)+String.valueOf(edge.dest.value), String.valueOf(edge.src.value), String.valueOf(edge.dest.value));
        }

        for (org.graphstream.graph.Node node : g) {
            node.addAttribute("ui.label", node.getId());
        }

        for (int i = 0; i < graph.adj.size(); i++) {
            org.graphstream.graph.Node node = g.getNode(i);
            if (S.contains(graph.getNode(Integer.parseInt(node.getId())))) {
                node.addAttribute("ui.style", "fill-color: rgb(255,0,0);");
            }
        }
    }

    public static void main(String[] args) {

        // input the graph
        myGraph graph = new myGraph();
        graph.inputUnweightedGraph();

        LinkedList<Node> S = new LinkedList<>();

        /*maa az degree dar in soal baraye
        * shemordan e yaal hayi ke hanooz unvisited hastan estefade mikonim*/
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
            System.out.println("highest degree node = " + highest_degree_node.value);

            // add it to S
            highest_degree_node.setVisited(true);
            S.add(highest_degree_node);
            for (Edge edge : highest_degree_node.connectedEdges) { edge.setVisited(true); }
            for (Node node : highest_degree_node.getNeighbours()){ node.degree--; }

            // tracer
            for (Edge edge : graph.Edges){
                System.out.println(edge.src.value + " - " + edge.dest.value + " : " + edge.visited);
            }
        }

        System.out.print("Set S is: { ");
        for (Node node : S) {
            System.out.print(node.value + ", ");
        }
        System.out.println("}");

        // visualization: nodes in S are shown with red color
        Project4 o = new Project4(); o.displayGraph(graph, S);

    }
}
