import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Main {
    public static void main(String[] args) {
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
        }
    }
}