import javax.swing.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB,10);
        nodeA.addDestination(nodeC,15);
        nodeB.addDestination(nodeD,12);
        nodeB.addDestination(nodeF,15);
        nodeC.addDestination(nodeE,10);
        nodeD.addDestination(nodeE,2);
        nodeD.addDestination(nodeF,1);
        nodeF.addDestination(nodeE,5);

        Graph graph =new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph = calculateShortestPathFromSource(graph,nodeA);





    }
    public static Graph calculateShortestPathFromSource(Graph graph, Node source){
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size()!=0){
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            //napiyo anlamadım
            for(Map.Entry<Node,Integer> adjacencyPair: currentNode.getAdjacentNodes().entrySet()){
                Node adjecentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if(!settledNodes.contains(adjecentNode)){
                    calculateMinimumDistance(adjecentNode,edgeWeight,currentNode);
                    unsettledNodes.add(adjecentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static void calculateMinimumDistance(Node evalationNode, Integer edgeWeight, Node sourceNode) {
        Integer sourceDistance= sourceNode.getDistance();
        if(sourceDistance+edgeWeight<evalationNode.getDistance()){
            evalationNode.setDistance(sourceDistance+edgeWeight);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evalationNode.setShortestPath(shortestPath);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance= Integer.MAX_VALUE;
        for(Node node: unsettledNodes){
            int nodeDistance=node.getDistance();
            if(nodeDistance<lowestDistance){
                lowestDistance=nodeDistance;
                lowestDistanceNode=node;
            }
        }
        return lowestDistanceNode;
    }
}