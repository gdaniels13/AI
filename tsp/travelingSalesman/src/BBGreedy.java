
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gregor
 */
public class BBGreedy {
     
    
    Graph graph;
    double bestCost;
    ArrayList<City> bestPath;
    
    
    public BBGreedy(Graph graph) {
        this.graph = graph;
        bestCost = Double.MAX_VALUE;
    }
    
    public ArrayList<City> getPath()
    {
        ArrayDeque p = new ArrayDeque<City>();
        p.add(graph.nodes.get(0));
        graph.nodes.remove(0);
        generatePaths(p,graph.nodes);
        return bestPath;
        
    }
    
    public void generatePaths(ArrayDeque<City> path, ArrayList<City> nodesLeft)
    {
        if(path.size()==graph.numNodes)
        {
            double cost = Graph.FindCost(path);
            if(cost < bestCost)
            {
                bestCost = cost;
                bestPath = new ArrayList<>(path);
            }
            //determine if best so far, if so save it.
            return;
        }
        else if(Graph.FindCost(path)>bestCost)
        {
            return;
        }
        

        
        //first time through, nothing to sort by
        ArrayList<City> newNodesLeft = sortByDistance(nodesLeft, path.peekLast());
//        System.out.println(path.peekLast().name);
//        for (City city : newNodesLeft) {
//            System.out.print("" + city.name + "::" + city.distanceToPoint + " ");
//        }
//        System.out.println("");
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
