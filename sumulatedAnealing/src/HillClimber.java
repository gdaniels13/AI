
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
public class HillClimber {

	Graph g;
	double lastCost;
	long endTime;
	int lastOp;
	int op1, op2;
	 int opCount;

	public HillClimber(Graph g) {
		this.g = g;
		lastCost = g.findCost();
	}

	public void findPath(long timeout) {
		endTime = ((long) timeout) * ((long) 1000000000) + System.nanoTime();

		boolean go = true;
		opCount = 0;


		while (go) {
			//only get the system time and check ending condition every 100 operations
			if (opCount % 100 == 0) {

				if (System.nanoTime() > endTime) {
					go = false;
					if (lastCost < g.findCost()) {
						undo();
					}
				}
//				createPicture(g.nodes, "intermediate " + opCount + "operations. Cost: " + g.findCost());
			}


			if (go) {
				if (lastCost < g.findCost()) {
					undo();
				} else {
					lastCost = g.findCost();
					performOp();
				}

				opCount++;
			}
		}
	}

	public void createPicture(ArrayList<City> path, String title) {
		ShowGraph g = new ShowGraph(500, path, title);
		JFrame myFrame = new JFrame(title);
		myFrame.getContentPane();
		myFrame.add(g);
		myFrame.pack();
		myFrame.setVisible(true);
	}

	public void performOp() {
		lastOp = (int) (Math.random() * 3);
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
			op2 = op1;
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
