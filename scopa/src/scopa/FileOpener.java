//Greg Daniels
//A00798340
/*
 * this program is based on an example by Malik/Burton
 * most functonality of their program has been romoved 
 * all it does is display "README.txt"
 */

package scopa;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FileOpener extends JFrame
{
   
   private JTextArea pageTA = new JTextArea();


   public FileOpener(String filename, String title) throws FileNotFoundException
   {
       setTitle(title);

       Container pane = getContentPane();
       
       pane.setLayout(new BorderLayout());
       pane.add(pageTA, BorderLayout.CENTER);
       JScrollPane scroll = new JScrollPane(pageTA);
       JScrollBar scrollBar = scroll.getVerticalScrollBar();
       pane.add(scroll);
       
       pageTA.setLineWrap(true);
       pageTA.setEditable(false);
       
       addFile(filename);

       setSize(500, 500);

       scrollBar.setValue(scrollBar.getMinimum());
       setVisible(true);
       
       setDefaultCloseOperation(HIDE_ON_CLOSE);
   }

    private void addFile(String filename) throws FileNotFoundException
    {
        Scanner in = new Scanner( new FileReader(filename));
        String readme = "";
    
        while( in.hasNextLine())
        {
        	readme = in.nextLine();
        	pageTA.append(readme + "\n");
        }
       
    }    
}

