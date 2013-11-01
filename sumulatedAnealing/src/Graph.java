
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class Graph {
    int numNodes;
    ArrayList<City> nodes;
    double[][] matrix;
	int iteration;
	private int cost;

	public Graph(Graph g){
		this.numNodes = g.numNodes;
		this.nodes = new ArrayList<>(g.nodes);
		this.cost = g.cost;
	}
    

    public Graph()
    {
        numNodes = 0;
          nodes = new ArrayList<>();
    }
    public Graph(String fileName)
    {
        numNodes = 0;
        nodes = new ArrayList<>();
        readList(fileName);
//        calculateClosestCities();
    }
    
    
    public void readList(String fileName)
    {
      try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String[] str;
            String temp;
            str = in.readLine().split(" ");
            numNodes = Integer.parseInt(str[0]);
            for(int i =0; i<numNodes; ++i)
            {
                str = in.readLine().split(" ");
                double x = Double.parseDouble(str[1]);
                double y = Double.parseDouble(str[2]);
                nodes.add(new City(Integer.parseInt(str[0]), new Point2D.Double(x,y)));
            }
      } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addCity(City city)
    {
        numNodes++;
        nodes.add(city);
    }
    
    public void createAdjacencyMatrixAndEdgeLIst()
    {
        matrix = new double[numNodes][numNodes];
        for(int i =0; i<numNodes; ++i)
        {
            City curCity = nodes.get(i);
            ArrayList<Edge> edge = new ArrayList<>();
            for(int j = 0; j<numNodes;++j)
            {
                if(curCity.name != nodes.get(j).name)
                {
                    edge.add(new Edge(curCity,nodes.get(j)));
                }
                matrix[i][j] = curCity.distance(nodes.get(j));
            }
            curCity.setEdges(edge);
        }
    }
    
 public double getMSTCost()
    {
        double cost = 0;
        createAdjacencyMatrixAndEdgeLIst();
        
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
            cost +=e.distance;
            curCity = e.b;
            unvisited.remove(e.b);
        }
        return cost;
    }
    

    
    public static double FindCost(ArrayList<City> paths)
    {
        if(paths.size()<=1)
        {
            return 0;
        }
        ArrayList<City> path;
        path = new ArrayList<>(paths);
        
        double cost = 0;
        
        for(int i = 0; i<path.size()-1; ++i)
        {
            cost += path.get(i).distance(path.get(i+1));
        }
        
        cost += path.get(0).distance(path.get(path.size()-1));
        
        return cost;
    }    
	
	public double findCost()
    {
        if(nodes.size()<=1)
        {
            return 0;
        }
      
         cost = 0;
        
        for(int i = 0; i<nodes.size()-1; ++i)
        {
            cost += nodes.get(i).distance(nodes.get(i+1));
        }
        
        cost += nodes.get(0).distance(nodes.get(nodes.size()-1));
        
        return cost;
    }    

	public String toString()
	{
		return null;
	}
	
    private void calculateClosestCities() {
        for (City city : nodes) {
            City closest = city;
            for(int i = 0; i<nodes.size(); ++i)
            {
                if(!city.equals(closest) && city.distance(nodes.get(i))< city.distance(closest))
                {
                    closest = nodes.get(i);
                }
            }
            city.setClosestCity(closest);
        }
        
    }
}
