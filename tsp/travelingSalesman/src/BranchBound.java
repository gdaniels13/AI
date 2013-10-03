
import java.util.ArrayDeque;
import java.util.ArrayList;


public class BranchBound {
    
    
    Graph graph;
    double bestCost;
    ArrayList<City> bestPath;
    
    
    public BranchBound(Graph graph) {
        this.graph = graph;
        bestCost = Double.MAX_VALUE;
    }
    
    public ArrayList<City> getPath()
    {
        generatePaths(new ArrayDeque<City>());
        
        
        return bestPath;
    }
    
    
    public void generatePaths(ArrayDeque<City> path)
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
