
import java.awt.geom.Point2D;

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

    boolean used;
    public City(int name, Point2D.Double location) {
        this.location = location;
        this.name = name;
        used = false;
    }
    public void setDistancetoPoint(City c)
    {
        distanceToPoint = this.distance(c);
    }
    
    public City(City c)
    {
        this.location = c.location;
        this.name = c.name;
        this.used = c.used;
    }

    public String toString()
    {
        return "" + name +" " + location.x + " " + location.y;
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
}