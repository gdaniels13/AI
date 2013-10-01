
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
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
    BranchBound bb;
    Exhaustive ex;
    BBGreedy bbg;
    
    public static void main(String[] args) {
        Main t = new Main();
        t.testBranchBound(5, 25, "graphs/test");
//        t.testExhaustive(5, 13, "graphs/test");
        //t.testGreedyBranchBound(5,25,"graphs/test");
    }

    public  void testGreedyBranchBound(int min, int max, String name) {
        PrintWriter fout = null;
        try {
            fout = new PrintWriter(name + min + "to" + max + "GreedybranchBound");
            fout.println("problem size time in seconds");
            for (int i = min; i < max; ++i) {
                Graph g = new Graph(name + i);
                long result = testGreedyBranchBound(g);
                double time = result / 1000000000.0;
                fout.print(i + "\t" + time + " cost:" + bbg.bestCost + " [");
                for (City city : bbg.bestPath) {
                    fout.print(city.name + ", ");
                }
                                fout.print("]\n");

                fout.flush();

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fout.close();
        }
    }

    private  long testGreedyBranchBound(Graph g) {
        bbg = new BBGreedy(g);
        long begin = System.nanoTime();
        bbg.getPath();
        long end = System.nanoTime();
        return end - begin;
    }
    
    public void testBranchBound(int min, int max, String name) {
        PrintWriter fout = null;
        try {
            fout = new PrintWriter(name + min + "to" + max + "branchBound");
            fout.println("problem size time in seconds");
            for (int i = min; i < max; ++i) {

                Graph g = new Graph(name + i);
                long result = testBranchBound(g);
                double time = result / 1000000000.0;
                fout.print(i + "\t" + time + " cost:" + bb.bestCost + " [");
                for (City city : bb.bestPath) {
                    fout.print(city.name + ", ");
                }
                fout.print("]\n");
                fout.flush();

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fout.close();
        }
    }

    private  long testBranchBound(Graph g) {
        bb = new BranchBound(g);
        long begin = System.nanoTime();
        bb.getPath();
        long end = System.nanoTime();
        return end - begin;
    }
    
    public  void testExhaustive(int min, int max, String name) {
        PrintWriter fout = null;
        try {
            fout = new PrintWriter(name + min + "to" + max + "testrun");
            fout.println("problem size time in seconds");
            for (int i = min; i < max; ++i) {
                Graph g = new Graph(name + i);
                long result = testExhaustive(g);
                double time = result / 1000000000.0;
                fout.println(i + "\t" + time + " cost:" + ex.bestCost + " [");
                for (City city : ex.bestPath) {
                    fout.print(city.name + ", ");
                }
                fout.print("]\n");
                fout.flush();

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fout.close();
        }
    }

    public  long testExhaustive(Graph p) {
        ex = new Exhaustive(p);
        long begin = System.nanoTime();
        ex.getPath();
        long end = System.nanoTime();
        return end - begin;

    }

    public  void generate(int min, int max, String name, int RANGE) {
        PrintWriter fout = null;
        BufferedWriter out;
        for (int i = min; i <= max; ++i) {
            try {
                fout = new PrintWriter(name + i);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            fout.println(i);
            for (int j = 0; j < i; ++j) {
                double x = Math.random() * RANGE;
                double y = Math.random() * RANGE;
                fout.println("" + j + " " + x + " " + y);
            }
            fout.close();
        }


    }

}
