
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gregor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       testExhaustive(5, 25, "graphs/test");
//       generate(5,100,"graphs/test",500);
       
    }
    
    public static void testExhaustive(int min, int max,String name)
    {
        PrintWriter fout = null;
        try {
            fout = new PrintWriter(name+min+"to"+max+"testrun");
            fout.println("problem size time in seconds");
            for(int i = min; i<max; ++i)
            {
                
                Graph g = new Graph(name+i);
                long result = testGraph(g);
                double time = result / 1000000000.0;
                fout.println(i + "\t" + time);
                fout.flush();
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fout.close();
        }
    }
    
    public static long testGraph(Graph p)
    {
        Exhaustive t = new Exhaustive(p);
        long begin = System.nanoTime();
        t.getPath();
        long end = System.nanoTime();
        return end - begin;
        
    }
    
    
    
    
    public static void generate(int min, int max, String name, int RANGE)
    {
        PrintWriter fout = null;
        BufferedWriter out ;
        for(int i = min; i<=max; ++i)
        {
            try {
                fout = new PrintWriter(name+i);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
             fout.println(i);
            for(int j = 0; j<i; ++j)
            {
                double x = Math.random()*RANGE;
                double y = Math.random()*RANGE;
               fout.println("" + j + " " + x + " " + y);
            }
            fout.close();
        }
        
    }
}
