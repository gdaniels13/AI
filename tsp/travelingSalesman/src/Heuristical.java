
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gregor
 */
public class Heuristical implements TSPSolver {
     
    
    Graph graph;
    double bestCost;
    ArrayList<City> bestPath;
    
    public Heuristical(Graph graph) {
        this.graph = graph;
        bestCost = Double.MAX_VALUE;
    }
    
    @Override
    public ArrayList<City> getPath()
    {
        ArrayDeque p = new ArrayDeque<>();
        p.add(graph.nodes.get(0));
        graph.nodes.remove(0);
        generatePaths(p,graph.nodes);
        return bestPath;  
    }
    
    public double estimateMinimumLeft(ArrayList<City> nodesLeft) {
        double toReturn = 0;
        for (City city : nodesLeft) {
            toReturn += city.closestCityDistance;
        }
       return toReturn; 
    }
    
    
    //oops
     public double getMSTCost(ArrayList<City> nodes)
    {
        double cost = 0;
              
        if(nodes.isEmpty()) return 0;
        ArrayList<City> unvisited = new ArrayList<>(nodes);
        
        unvisited.remove(nodes.get(0));
        PriorityQueue<Edge> availableEdges = new PriorityQueue<>();
        City curCity = nodes.get(0);
        while(!unvisited.isEmpty())
        {
            for(Edge e : curCity.edges)
            {
                if(unvisited.contains(e.b))
                {
                    availableEdges.add(e);
                }
            }
            
            Edge e = availableEdges.remove();
            cost += e.distance;
            curCity = e.b;
            unvisited.remove(e.b);
        }
        return cost;
    }
     
     
    
     
    
    private ArrayList<Edge> getEdges(City cur, ArrayList<City> p) {
        ArrayList<Edge> toReturn = new ArrayList<>();
        
        for (City city  : p) {
            toReturn.add(new Edge(cur, city));
        }
        
        return toReturn;
    }
    
    
    public void generatePaths(ArrayDeque<City> path, ArrayList<City> nodesLeft)
    {
        double curCost = Graph.FindCost(path);
        if(path.size()==graph.numNodes)
        {
            double cost = Graph.FindCost(path);
            if(cost < bestCost)
            {
                bestCost = cost;
                bestPath = new ArrayList<>(path);
            }
            return;
        }
        else if(curCost>bestCost)
        {
            return;
        }
        else if((curCost + estimateMinimumLeft(nodesLeft) )> bestCost )
        {

            return;
        }
        

        
        ArrayList<City> newNodesLeft = sortByDistance(nodesLeft, path.peekLast());
        for(int i = 0; i<newNodesLeft.size(); ++i)
        {
            City t = newNodesLeft.get(i);
            path.addLast(new City(t));
            newNodesLeft.remove(i);
            generatePaths(path,newNodesLeft);
            newNodesLeft.add(i,t);
            path.removeLast();
        }      
    }
    
   
    
    public ArrayList<City> sortByDistance(ArrayList<City> path, City from)
    {
        if(from == null)
        {
            return path;
        }
        
        ArrayList<City>p = new ArrayList<>();
        for(int i =0; i<path.size(); ++i)
        {
           path.get(i).setDistancetoPoint(from); 
           if(path.get(i).distanceToPoint==0)
           {
               //do nothing
           }
           else
           {
               p.add(new City(path.get(i)));
           }
        }
        Collections.sort(p);
        return p;
    }    

}
