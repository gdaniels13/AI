
import java.awt.geom.Point2D;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gregor
 */
public class City implements Comparable
{

    Point2D.Double location;
    int name;
    double distanceToPoint;
    double closestCityDistance;
    City closestCity;
    
    ArrayList<Edge> edges;
    
    boolean used;
    public City(int name, Point2D.Double location) {
        this.location = location;
        this.name = name;
        used = false;
    }

    public void setClosestCity(City closestCity) {
        this.closestCity = closestCity;
        this.closestCityDistance = distance(closestCity);
    }
    
    
    public void setDistancetoPoint(City c)
    {
        distanceToPoint = this.distance(c);
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
    
    public City(City c)
    {
        this.location = c.location;
        this.name = c.name;
        this.used = c.used;
        this.distanceToPoint = c.distanceToPoint;
    }

    public String toString()
    {
        return "" + name;// +" " + location.x + " " + location.y;
    }

    public double distance(City c)
    {
        return location.distance(c.location);
    }

    @Override
    public int compareTo(Object t) {
        if(this.distanceToPoint < ((City) t).distanceToPoint)
            return -1;
        else if(this.distanceToPoint == ((City) t).distanceToPoint)
        {
            return 0;
        }        
        else 
        {
            return 1;
        }
    }
    
    
    @Override
    public boolean equals(Object o){
        City temp = (City) o;
        if( temp.location == this .location && temp.name == this.name)
            return true;
        return false;
    }
}