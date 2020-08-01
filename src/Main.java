import org.graphstream.graph.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        // input the graph
//        Scanner scanner = new Scanner(System.in);
//        int v = scanner.nextInt();
//        int e = scanner.nextInt();
//
//        myGraph g = new myGraph(v);
//        for (int i=0; i<e; i++){
//            int v1 = scanner.nextInt();
//            int v2 = scanner.nextInt();
//            int weight = scanner.nextInt();
//            g.addEdge(v1, v2, 1);
//        }
//        g.printGraph();

        Graph graph = new SingleGraph("Tutorial 1");

        graph.setStrict(false);
        graph.setAutoCreate(true);
        graph.display();

        graph.addNode("A" );
        graph.addNode("B" );
        graph.addNode("C" );
        graph.addNode("D" );
        graph.addNode("E" );
        graph.addEdge("AC", "A", "C");
        graph.addEdge("AD", "A", "D");
        graph.addEdge("AE", "A", "E");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("BD", "B", "D");
        graph.addEdge("BE", "B", "E");
        graph.addEdge("ED", "E", "D");
        graph.addEdge("CD", "C", "D");
        graph.addEdge("AB", "A", "B");

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
            node.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
        }
    }
}