
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
//        Graph t = new Graph();
//        t.readList("graphs/test8.graph");
//        ArrayList<City> cities = t.getList();
//        t.createAdjacencyMatrix();
//        
//        Exhaustive e = new Exhaustive(t);
//        
//        e.getPath();
//        
//        for (City city : e.bestPath) {
//            System.out.println(city);
//        }
//        
//        System.out.println("bestCost = " +e.bestCost);
        
        generate(4,12,"graphs/test",150);

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
                
                fout.println(min + "\t" + result/1000000000);
                
                
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
