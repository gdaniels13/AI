
import java.awt.geom.Point2D;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gregor
 */
    public class City{
        
        Point2D.Double location;
        String name;
        boolean used;
        public City(String name, Point2D.Double location) {
            this.location = location;
            this.name = name;
            used = false;
        }
        
        public City(City c)
        {
            this.location = c.location;
            this.name = c.name;
            this.used = c.used;
        }
        
        public String toString()
        {
            return name +" " + location.x + " " + location.y;
        }
        
        public double distance(City c)
        {
            return location.distance(c.location);
        }
    }