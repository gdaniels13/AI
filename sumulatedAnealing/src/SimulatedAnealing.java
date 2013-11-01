
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Greg Daniels gdaniels13@gmail.com A00798340
 */
public class SimulatedAnealing {

	Graph g;
	double lastCost;
	int iterations;
	int lastOp;
	int op1, op2;
	int opCount;
	int maxTemp;
	double temperature;
	double MAX_TEMP = 28;
	int bestIteration;
	private JFrame myFrame;
	double TSCALE;
	boolean liveDisplay;
	double bestCost;
	boolean random;
	Graph savedSolution;
	double savedCost;
	int savedIteration;
	boolean restart;
	private int stagnantCount;
	boolean hillClimber;

	public SimulatedAnealing(Graph g) {
		TSCALE = 0.8;
		this.g = g;
		lastCost = g.findCost();
		bestCost = Double.MAX_VALUE;
		random = false;
		lastOp = 3;
		restart = true;
		savedSolution = new Graph(g);
		liveDisplay = true;
		hillClimber = false;
	}

	public void findPath(long iterations) {
		this.iterations = (int) iterations;
		temperature = MAX_TEMP;
		boolean go = true;
		opCount = 0;
		StringBuilder display = new StringBuilder(" ");
		if (liveDisplay) {
			createPicture(g.nodes, display);
		}

		double curCost = 0;
		while (go) {
			if (opCount == iterations) {
				go = false;
				if (lastCost < g.findCost()) {
					undo();
				}
			}

			//adjust the temperature;
			if ((opCount % (iterations / 1000)) == 0) {
				temperature *= TSCALE;
			}

			if (restart && stagnantCount > 50000) {
				stagnantCount = 0;
				restart();
			}


			if (go) {
				curCost = g.findCost();


				if (lastCost <= curCost) {//no improvement
					if (!hillClimber && Math.pow(Math.E, (curCost - lastCost) / temperature) > Math.random()) {
						undo();
					}
					stagnantCount++;
				} else {
					bestIteration = opCount;
					stagnantCount = 0;
				}

				if (opCount % 100 == 0 && liveDisplay) {
					display.replace(0, display.length(), "Iterations: " + opCount + "Cost: " + lastCost + " Temperature:" + temperature + " sCount" + stagnantCount);
					upDateDisplay();
				}


				lastCost = g.findCost();
				performOp();

				opCount++;
			}
		}
	}

	public Graph bestSolution() {
		if (g.findCost() < savedSolution.findCost()) {
			return g;
		}
		return savedSolution;
	}

	public void restart() {
		temperature = MAX_TEMP;
		if (g.findCost() < savedSolution.findCost()) {
			savedSolution = new Graph(g);
			savedIteration = bestIteration;
			savedCost = lastCost;
			savedSolution.iteration = bestIteration;
		}
		Collections.shuffle(g.nodes);
	}

	public double getTemperature() {


		return temperature;
	}

	public void upDateDisplay() {
		myFrame.repaint();
	}

	public void createPicture(ArrayList<City> path, StringBuilder title) {
		LiveDisplay g = new LiveDisplay(500, path, title);
		myFrame = new JFrame();
		myFrame.getContentPane();
		myFrame.add(g);
		myFrame.pack();
		myFrame.setVisible(true);
	}

	public void performOp() {
		if (random) {

			lastOp = (int) (Math.random() * 3);
		}

		op1 = (int) (Math.random() * g.numNodes);
		op2 = (int) (Math.random() * g.numNodes);
		switch (lastOp) {
			case 0:
				swap(op1, op2);
				break;
			case 1:
				--op1;
				if (op1 == -1) {
					op1 = 0;
				}
				op2 = op1 + 1;
				swap(op1, op2);
				break;
			default:
				reverseSubset(op1, op2);
				break;
		}
	}

	private void swap(int op1, int op2) {
		op1 = (int) Math.random() * (g.numNodes) - 1;
		if (op1 == -1) {
			op1 = 0;
		}
		op2 = op1 + 1;


		Collections.swap(g.nodes, op1, op2);
	}

	private void reverseSubset(int op1, int op2) {

		if (op1 > op2) {
			int t = op1;
			op1 = op2;
			op2 = t;
		}

		while (op1 < op2) {
			Collections.swap(g.nodes, op1, op2);
			++op1;
			--op2;
		}


	}

	private void undo() {
		switch (lastOp) {
			case 0:
				swap(op1, op2);
				break;
			case 1:
				--op1;
				if (op1 == -1) {
					op1 = 0;
				}
				op2 = op1 + 1;
				swap(op1, op2);
				break;
			default:
				reverseSubset(op1, op2);
				break;
		}
	}
}
