/**
 * Created with IntelliJ IDEA.
 * User: gregor
 * Date: 11/11/13
 * Time: 6:10 PM
 * To change this template use File | Settings | File Templates.
 */
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;


public class test {

	static int sud[][] = new int[9][9];
	private static int rcount;
	public static void main(String args[]) {

		String str1 = "[123,123,123]";
		str1 = str1.replaceAll(",|\\[|\\]","");
		System.out.println(str1);

	}

	private static void print()
	{
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(sud[i][j]);
			}
			System.out.print("\n");
		}
		System.out.println(rcount);
	}

	public static boolean solve(int[][] sud) {
		rcount ++;
		print();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sud[i][j] != 0) {
					continue;
				}
				for (int x = 1; x < 10; x++) {
					if (!used(i, j, x)) {
						sud[i][j] = x;
						if (solve(sud)) {
							return true;
						}
					}
				}
				return false;
			}
		}
		return true;

	}

	public static boolean isinrow(int i, int j, int x) {
		for (int t = 0; t < 9; t++) {
			if (sud[i][t] == x) {
				return true;
			}
		}
		return false;
	}

	public static boolean isincol(int i, int j, int x) {
		for (int t = 0; t < 9; t++) {
			if (sud[t][j] == x) {
				return true;
			}
		}
		return false;

	}

	public static boolean isinsq(int xcorner, int ycorner, int x) {
		for (int sr = xcorner; sr < 3; sr++) {
			for ( int sc = ycorner; sc < 3; sc++) {
				if (sud[sr][sc] == x) {
					return true;
				}
			}
		}
		return false;
	}

	static boolean used(int i, int j, int x) {
		if (!isinrow(i, j, x)) {
			if (!isincol(i, j, x)) {
				if (!isinsq(i - (i % 3), j - (j % 3), x)) {
					return false;
				}
			}
		}
		return true;
	}
}

