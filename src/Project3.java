import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.LinkedList;

public class Project3 {

    public void displayGraph(myGraph graph, myGraph networkFlow){
        Graph g = new SingleGraph("Project 3");

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

        for (int i = 0; i < graph.Edges.size(); i++) {
            Edge this_edge = graph.Edges.get(i);
            org.graphstream.graph.Edge edge = g.getEdge(String.valueOf(graph.Edges.get(i).src.value) + String.valueOf(graph.Edges.get(i).dest.value));
            if (networkFlow.getEdge(networkFlow.getNode(this_edge.src.value+1), networkFlow.getNode(this_edge.dest.value+1)).visited){
                edge.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
            }
        }
    }

    public static void main(String[] args) {

        myGraph graph = new myGraph();
        graph.inputUnweightedGraph();

        if (! graph.isBipartite() ) {
            /* in tabe' e .isBipartite() Node haa ro be komak e
            * BFS ham rang mikone, ta betoonim baxsh haaye geraaf ro az ham joda konim.  */
            System.out.println("graph is not bipartite");
        }
        else {
            graph.improveDirections();
            myGraph NF = graph.networkFlow();

            NF.fordFulkerson();

            for (Edge edge : NF.Edges) {
                if (edge.visited && edge.src.value!=0 && edge.dest.value!=NF.adj.size()-1){
                    graph.getEdge(edge.src, edge.dest).setVisited(true);
                    System.out.println((edge.src.value - 1)  + " " + (edge.dest.value - 1));
                }
            }

            Project3 o = new Project3(); o.displayGraph(graph, NF);

        }
    }
}
