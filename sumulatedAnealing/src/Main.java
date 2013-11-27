
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

	public static void main(String[] args) {

		Main t = new Main();
		t.hillClimberTest("tsp225.txt", true, false, true, true);
        t.testGraph("tsp225.txt");
//		t.testGraph("graphs/test19");
//		t.TemperatureTest("graphs/test99",.9);
//		t.TemperatureTest("graphs/test99",.8);
//		t.TemperatureTest("graphs/test99",.7);
//		t.TemperatureTest("graphs/test99",.6);
		t.operatorTest("tsp225.txt");
//		t.scaleTest("graphs/test");
//		t.testCPU();
	}
	

	public void testGraph(String name) {
		long timeout = 10000000;
		Graph g = new Graph(name);
//		createPicture(g.nodes, "Before Hill Climber: " + timeout + " seconds.\n Cost: " + g.findCost());
		try {
			Thread.sleep(250);
		} catch (InterruptedException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		SimulatedAnealing t = new SimulatedAnealing(g);
		t.random = false; 
		t.restart = true;
		t.liveDisplay = true;
		
		t.findPath(timeout);
		g = t.bestSolution();
		createPicture(g.nodes, "After Hill Climber: " + timeout + " seconds.\n Cost: " + g.findCost() + " Operations: " + g.iteration);
	}
	
	public void testCPU()
	{
		int iterations = 10000000;
		Graph g = new Graph("tsp225.txt");
		PrintWriter fout = null;
		try {
			fout = new PrintWriter("restartTest");
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		SimulatedAnealing sa= new SimulatedAnealing(g);
		sa.hillClimber = false;
		sa.random = false;
		sa.restart = true;
		sa.liveDisplay = false;
		sa.findPath(iterations);
		
		 fout.println("restarting" + sa.bestSolution().findCost() + " iterations: " + sa.bestSolution().iteration);
		
		sa= new SimulatedAnealing(g);
		sa.hillClimber = false;
		sa.random = false;
		sa.restart = false;
		sa.liveDisplay = false;
		sa.findPath(iterations);
		fout.println("runningTime" + sa.g.findCost() + " iterations: " + sa.bestIteration);
		
		fout.flush();
		fout.close();
		
		
	}
	

	public void createPicture(ArrayList<City> path, String title) {
		ShowGraph g = new ShowGraph(500, path, title);
		JFrame myFrame = new JFrame(title);
		myFrame.getContentPane();
		myFrame.add(g);
		myFrame.pack();
		myFrame.setVisible(true);
	}

	public void scaleTest(String name) {
		int iterations = 500000;
		PrintWriter fout = null;
		try {
			fout = new PrintWriter(name + 5 + "to" + 99 + "testrun");
			fout.println("problem size\t cost\t iterations\t");

			for (int i = 5; i < 100; ++i) {
				double costAcc = 0;
				double itAcc = 0;
				for (int j = 0; j < 5; ++j) {
					Graph g = new Graph(name + i);
					SimulatedAnealing sa = new SimulatedAnealing(g);
					sa.random = false;
					sa.hillClimber = false;
					sa.liveDisplay = false;
					sa.restart = false;
					sa.findPath(iterations);
					costAcc += sa.lastCost;
					itAcc += sa.bestIteration;
				}
				costAcc /=5;
				itAcc/=5;
				fout.printf("%d\t%f\t%f\n ", i, costAcc, itAcc);
				fout.flush();

			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			fout.close();
		}

	}

public void hillClimberTest(String name, boolean random, boolean restart, boolean hillClimber, boolean liveDisplay) {
		long iterationTimeout = 5;

		double costAcc = 0;
		double itAcc = 0;

			Graph g = new Graph(name);
//			SimulatedAnealing sa = new SimulatedAnealing(g);
//			sa.random = random;
//			sa.restart = restart;
//			sa.hillClimber = hillClimber;
//			sa.liveDisplay = liveDisplay;
			
			HillClimber sa = new HillClimber(g);
			
			sa.findPath(iterationTimeout);
			sa.createPicture(g.nodes, "Hill CLimber " + g.findCost());
}
	
	
	public void operatorTest(String name) {
		long iterationTimeout = 500000;
		PrintWriter fout = null;
		try {
			fout = new PrintWriter(name + "operatorTest1");
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

		double costAcc = 0;
		double itAcc = 0;
		fout.println("operator\t avgCost\t avgIteration");
		for (int j = 0; j < 3; ++j) {
			//costAcc = 0;
			//itAcc = 0;
			//for (int i = 0; i < 5; ++i) {
				Graph g = new Graph(name);
				SimulatedAnealing sa = new SimulatedAnealing(g);
				sa.random = false;
				sa.opCount = j;
				sa.hillClimber = false;
				sa.restart = false;
				sa.liveDisplay = true;
				sa.findPath(iterationTimeout);
			//	costAcc += sa.lastCost;
			//	itAcc += sa.bestIteration;
			//}
			//costAcc /= 5;
			//itAcc /= 5;
			//fout.printf("%d\t%f\t%f\n ", j, costAcc, itAcc);
			//fout.flush();
		}
//		costAcc = 0;
//		itAcc = 0;
//		for (int i = 0; i < 5; ++i) {
//			Graph g = new Graph(name);
//			SimulatedAnealing sa = new SimulatedAnealing(g);
//			sa.random = true;
//			sa.restart = false;
//			sa.hillClimber = false;
//			sa.liveDisplay = false;
//			sa.findPath(iterationTimeout);
//			costAcc += sa.lastCost;
//			itAcc += sa.bestIteration;
//		}
		costAcc /= 5;
		itAcc /= 5;
		fout.printf("%d\t%f\t%f\n ", 5, costAcc, itAcc);
		fout.flush();
		fout.close();
	}

	
	
	public void TemperatureTest(String name,double temScalar) {
		long iterationTimeout = 500000;
		PrintWriter fout = null;
		try {
			fout = new PrintWriter(name + "temperaturetest" + temScalar);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

		double costAcc = 0;
		double itAcc = 0;
		fout.println("maxTemp\t avgCost\t avgIteration");
		for (int j = 40; j > 8; --j) {
			costAcc = 0;
			itAcc = 0;
			for (int i = 0; i < 5; ++i) {
				Graph g = new Graph(name);
				SimulatedAnealing sa = new SimulatedAnealing(g);
				sa.random = false;
				sa.TSCALE = temScalar;
				sa.hillClimber = false;
				sa.liveDisplay = false;
				sa.restart = false;

				sa.MAX_TEMP = j;
				sa.findPath(iterationTimeout);
				costAcc += sa.lastCost;
				itAcc += sa.bestIteration;
			}
			costAcc /= 5;
			itAcc /= 5;

			fout.printf("%d\t%f\t%f\n ", j, costAcc, itAcc);
			fout.flush();
		}
		fout.close();
	}

	public void generate(int min, int max, String name, int RANGE) {
		PrintWriter fout = null;
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
