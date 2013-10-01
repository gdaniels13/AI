
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

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
        generatePaths(new ArrayDeque<City>(), graph.nodes.get(0) );
        return bestPath;
        
    }
    
    
    public void generatePaths(ArrayDeque<City> path, City lastCity)    {
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
        
        ArrayDeque<City> newPath = new ArrayDeque<>(path);
        
        ArrayList nodesLeft = sortByDistance(new ArrayList(graph.nodes), lastCity);
        
        for(int i = 0; i<graph.nodes.size(); ++i)
        {
            if(graph.nodes.get(i).used != true)
            {
                newPath.addLast(new City(graph.nodes.get(i)));
                graph.nodes.get(i).used = true;
                generatePaths(newPath, graph.nodes.get(i));
                graph.nodes.get(i).used = false;
                newPath.removeLast();
            }
            else
            {
                continue;
            }
        }
    }    
     
    
    public ArrayList<City> sortByDistance(ArrayList<City> path, City from)
    {
        
        for (City city : path) {
            city.setDistancetoPoint(from);            
        }
        Collections.sort(path);
//        for (City city : path) {
//            System.out.println(city.distanceToPoint);
//            
//        }
        return path;
    }

    
}
