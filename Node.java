import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

public class Node {
    private String name;
    private List<Node> shortestPath = new LinkedList<>();
    private  Integer distance = Integer.MAX_VALUE;
    Map<Node,Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance){
        adjacentNodes.put(destination,distance);
    }
    public Node(String name){
        this.name =name;
    }
    public void setDistance(int distance){
        this.distance=distance;
    }
    public int getDistance(){
        return distance;
    }

    public Map<Node,Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath=shortestPath;
    }
    //lazim olursa getter setter
}
