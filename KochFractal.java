import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
public class KochFractal extends JPanel{
    
    public KochFractal(){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(512, 512));
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawPolygon(genFractal(1, 256, 256));
    }

    /**
     * Generates a polygon object of a koch snowflake
     * @param depth how many iterations of the fractal to draw
     * @param width how wide the fractal is
     * @param center where the center of the fractal is located
     * @return a polygon object representing the Koch snowflake
     */
    private Polygon genFractal(int depth, int width, int center){
        int numSides = 3 * (int)Math.pow(4, depth);
        
        ArrayList<Point> pts = new ArrayList<>();
        Point[] pair;

        //initial triangle (depth 0)
        pts.add(new Point(center, (int)(center + (width/4)*Math.sqrt(3))));
        pts.add(new Point((center + (width/2)), (int)(center - (width/4)*Math.sqrt(3))));
        pts.add(new Point((center - (width/2)), (int)(center - (width/4)*Math.sqrt(3))));
        
        pair = thirds(pts.get(0), pts.get(1));

        pts.add(1, pair[0]);
        pts.add(2, completeTriangle(pair[0], pair[1]));
        pts.add(3, pair[1]);

        System.out.print(pair[1] + "pair[1]");
        System.out.println(new Point(center, (int)(center + (width/4)*Math.sqrt(3))) + "v1");
        System.out.println(new Point((center + (width/2)), (int)(center - (width/4)*Math.sqrt(3))) + "v2" );

        
        //generate the polygon
        Polygon output = new Polygon();
        for (Point p : pts){
            output.addPoint((int)p.getX(), (int)p.getY());
        }
        return output;
    }

    /**
     * Returns the two points that are 1/3 and 2/3 of the way between point a and point b
     * @param a the first point
     * @param b the second point
     * @return a pair of points that are 1/3 and 2/3 of the way between point A and point B
     */
    private Point[] thirds(Point a, Point b){
        Point[] pair = new Point[2];

        pair[0] = new Point((int)(a.getX() + (b.getX() - a.getX())/3), (int)(a.getY() + (b.getY() - a.getY())/3));
        pair[1] = new Point((int)(a.getX() + ((b.getX() - a.getX()) * (2.0/3.0))), (int)(a.getY() + ((b.getY() - a.getY()) * (2.0/3.0))));

        return pair;
    }

    /**
     * Given two vertices, return the third vertex that would form an equilateral triangle
     * @param a First point
     * @param b Second point
     * @return Third point, that would form an equilateral triangle with the first two
     */
    private Point completeTriangle(Point a, Point b){
        Point midpt = new Point((int)(a.getX() + b.getX())/2, (int)(a.getY() + b.getY())/2);
        double slp = (b.getY() - a.getY())/(b.getX() - a.getX()) * -0.5;
        double len = (Math.sqrt(3)/2) * Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));

        double x = midpt.getX() + len * Math.sqrt((1/(1 + slp*slp)));
        double y = midpt.getY() + len * slp * Math.sqrt((1/(1 + slp*slp)));

        return new Point((int)x, (int)y);
    }

    //TEMP
    public static void main(String[] args) {
        KochFractal kch = new KochFractal();

        JFrame frame = new JFrame("Koch Snowflake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(512,512);

        frame.getContentPane().add(kch);
        frame.pack();
        frame.setVisible(true);
    }

}
