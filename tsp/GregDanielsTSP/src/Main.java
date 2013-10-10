
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

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
    Heuristical hs;

    public static void main(String[] args) {

        Main t = new Main();
        //t.testBranchBound(19, 20, "graphs/test");
////          t.testExhaustive(5, 13, "graphs/test");
//          t.testTSPSolver(13,14,"graphs/test");
        //t.createPicture(null);
        t.testGivenGraph();
    }

    public void testGivenGraph() {
        String name = "tsp225.txt";
//        String name = "graphs/test5";
        long timeout = 1800;

//        BranchBound branch = new BranchBound(new Graph(name));
//        BBGreedy greedy = new BBGreedy(new Graph(name));
        Heuristical heu = new Heuristical(new Graph(name));
//        Exhaustive ex = new Exhaustive(new Graph(name));
        
//        ex.getPath(timeout);
//        branch.getPath(timeout);
//        greedy.getPath(timeout);
        heu.getPath(timeout);
        
        
//        createPicture(ex.bestPath, "Exhaustive with Timeout: " + timeout + " seconds. Cost: " + ex.bestCost);
//        createPicture(greedy.bestPath, "Branch and Bound With Greedy First expansion\n Timeout: " + timeout + " seconds.\n Cost: " + greedy.bestCost);
//        createPicture(branch.bestPath, "Branch and Bound Timeout: " + timeout + " seconds. \n Cost: " + branch.bestCost);
        createPicture(heu.bestPath, "Branch and Bound With Greedy First expansion \n with aditional heuristics Timeout: " + timeout + " seconds.\n Cost: " + heu.bestCost);
    }

    public void createPicture(ArrayList<City> path, String title) {
        ShowGraph g = new ShowGraph(500, path, title);
        JFrame myFrame = new JFrame(title);
        myFrame.getContentPane();
        myFrame.add(g);
        myFrame.pack();
        myFrame.setVisible(true);
    }

    public void testTSPSolver(int min, int max, String name) {
        PrintWriter fout = null;
        try {
            fout = new PrintWriter(name + min + "to" + max + "Heuristic");
            fout.println("problem size time in seconds");
            for (int i = min; i < max; ++i) {
                Graph g = new Graph(name + i);
                long result = testTSPSolver(g);
                double time = result / 1000000000.0;
                fout.print(i + "\t" + time + " cost:" + hs.bestCost + " [");
                for (City city : hs.bestPath) {
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

    private long testTSPSolver(Graph g) {
        hs = new Heuristical(g);
        long begin = System.nanoTime();
        hs.getPath();
        long end = System.nanoTime();
        return end - begin;
    }

    public void testGreedyBranchBound(int min, int max, String name) {
        PrintWriter fout = null;
        try {
            fout = new PrintWriter(name + min + "to" + max + "GreedybranchBound");
            fout.println("problem size time in seconds");
            for (int i = min; i < max; ++i) {
                Graph g = new Graph(name + i);
                long result = testGreedyBranchBound(g);
                double time = result / 1000000000.0;
                System.out.println("" + i + " " + time);
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

    private long testGreedyBranchBound(Graph g) {
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

    private long testBranchBound(Graph g) {
        bb = new BranchBound(g);
        long begin = System.nanoTime();
        bb.getPath(50);
        long end = System.nanoTime();
        return end - begin;
    }

    public void testExhaustive(int min, int max, String name) {
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

    public long testExhaustive(Graph p) {
        ex = new Exhaustive(p);
        long begin = System.nanoTime();
        ex.getPath();
        long end = System.nanoTime();
        return end - begin;

    }

    public void generate(int min, int max, String name, int RANGE) {
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
