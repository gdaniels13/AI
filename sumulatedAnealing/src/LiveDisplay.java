
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gregor
 */
public class LiveDisplay extends JPanel {
    
    private int size = 500;
    private ArrayList<City> graph;
    private StringBuilder title;
    public LiveDisplay(int size, ArrayList<City> graph, StringBuilder title)
    {
        this.title = title;
        this.size = size;
        setLayout(null);
        setPreferredSize(new Dimension(size*2,size));
        this.graph = graph;
    }
    
    @Override
    public void paintComponent(Graphics g){
//        g.setColor(Color.red);
        double scale = 1;
        g.drawString(title.toString(), 15, 15);
        
        for(int i = 0; i<graph.size()-1; ++i){
            City a = graph.get(i);
            City b = graph.get(i+1);
			g.drawOval((int)(a.location.x*scale), (int)(a.location.y*scale), 2, 2);
//            g.drawString(a.toString(), (int)(a.location.x*scale), (int)(a.location.y*scale));
            g.drawLine((int)(a.location.x*scale), (int)(a.location.y*scale), (int)(b.location.x*scale), (int)(b.location.y*scale));
        }
        City a = graph.get(0);
        City b = graph.get(graph.size()-1);
//        g.drawString(b.toString(), (int)(scale*b.location.x), (int)(scale*b.location.y));
        g.drawLine((int)(a.location.x*scale), (int)(a.location.y*scale), (int)(b.location.x*scale), (int)(b.location.y*scale));
    }
}

