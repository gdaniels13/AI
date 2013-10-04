/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gregor
 */
public class Edge implements Comparable {
    City a,b;
    double distance;

    public Edge(City a, City b) {
        this.a = a;
        this.b = b;
        this.distance = a.distance(b);
    }    
    
    public int compareTo(Object t) {
        if(this.distance < ((Edge) t).distance)
            return -1;
        else if(this.distance == ((Edge) t).distance)
        {
            return 0;
        }        
        else 
        {
            return 1;
        }
    }
}
