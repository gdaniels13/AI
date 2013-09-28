
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;


public class Graph {
    int numNodes;
    ArrayList<City> nodes;
    double[][] matrix;
    
    public Graph()
    {
          nodes = new ArrayList<>();
    }
    public Graph(String fileName)
    {
        readList(fileName);
        createAdjacencyMatrix();
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
                
                nodes.add(new City(str[0], new Point2D.Double(x,y)));
            }
      } 
        catch (IOException e) {
        }
    }
    
    public void createAdjacencyMatrix()
    {
        matrix = new double[numNodes][numNodes];
        for(int i =0; i<numNodes; ++i)
        {
            City curCity = nodes.get(i);
            for(int j = 0; j<numNodes;++j)
            {
                matrix[i][j] = curCity.distance(nodes.get(j));
            }
        }
    }
    
    public ArrayList<City> getList()
    {
        return nodes;
    }
    
    public static double FindCost(ArrayDeque<City> paths)
    {
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
    
}
