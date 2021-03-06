
import java.util.ArrayDeque;
import java.util.ArrayList;


public class Exhaustive implements TSPSolver{
    Graph graph;
    double bestCost;
    ArrayList<City> bestPath;
    private long timeout;
    
    
    public Exhaustive(Graph graph) {
        this.graph = graph;
        bestCost = Double.MAX_VALUE;
    }
    
    public ArrayList<City> getPath()
    {
        generatePaths(new ArrayDeque<City>());
        
        
        return bestPath;
    }
  public ArrayList<City> getPath( long timeout)
    {
        this.timeout = ((long)timeout)*((long)1000000000) + System.nanoTime();
        generatePaths(new ArrayDeque<City>());
        
        
        return bestPath;
    }
    
    
    public void generatePaths(ArrayDeque<City> path)
    {
        if(System.nanoTime()>this.timeout)
            return;
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
        
        ArrayDeque<City> newPath = new ArrayDeque<>(path);
        
        for(int i = 0; i<graph.nodes.size(); ++i)
        {
            if(graph.nodes.get(i).used != true)
            {
                newPath.addLast(new City(graph.nodes.get(i)));
                graph.nodes.get(i).used = true;
                generatePaths(newPath);
                graph.nodes.get(i).used = false;
                newPath.removeLast();
            }
            else
            {
                continue;
            }
        }
        
        
    }
    
}
