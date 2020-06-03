import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Generates a GUI window with a koch snowflake
 * Warning: Doesn't quite work for depth > 5
 */
public class KochFractal extends JPanel {
    int fractal_depth;

    /**
     * Making a new instance of a KochFractal will create a new window containing the fractal
     * @param f_depth the depth of the koch snowflake
     */
    public KochFractal(int f_depth){
        fractal_depth = f_depth;

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(512, 512));

        JFrame frame = new JFrame("Koch Snowflake, depth " + fractal_depth);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(512,512);

        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawPolygon(genFractal(fractal_depth, 256, 256));
    }

    /**
     * Generates a polygon object of a koch snowflake
     * 
     * Note: mostly works, some slightly odd edge cases still need fixing
     * @param depth how many iterations of the fractal to draw
     * @param width how wide the fractal is
     * @param center where the center of the fractal is located
     * @return a polygon object representing the Koch snowflake
     */
    private Polygon genFractal(int depth, int width, int center){
        
        ArrayList<Point> pts = new ArrayList<>();
        Point[] pair;

        //initial triangle (depth 0)
        pts.add(new Point(center, (int)(center + (width/4)*Math.sqrt(3))));
        pts.add(new Point((center + (width/2)), (int)(center - (width/4)*Math.sqrt(3))));
        pts.add(new Point((center - (width/2)), (int)(center - (width/4)*Math.sqrt(3))));
        
        int sgn = 1;
        for (int i = 1; i <= depth; i++){
            for (int s = 0; s < 3 * (int)Math.pow(4, i) - 3; s += 4){
                pair = thirds(pts.get(s), pts.get((s + 1) % (3 * (int)Math.pow(4, i) - 3)));

                //if (s == 0) sgn = 1;
                //else sgn = -1;

                pts.add(s + 1 , pair[0]);
                pts.add(s + 2, completeTriangle(pair[0], pair[1], sgn));
                pts.add(s + 3, pair[1]);
            }
        }
    
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
     * @param sgn Positive or negative
     * @return Third point, that would form an equilateral triangle with the first two
     */
    private Point completeTriangle(Point a, Point b, int sgn){
        Point midpt = new Point((int)(a.getX() + b.getX())/2, (int)(a.getY() + b.getY())/2);
        double slp = (b.getY() - a.getY())/(b.getX() - a.getX());
        double len = (Math.sqrt(3)/2) * Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));

        double x, y;
        if (slp != 0){
            slp = -1/slp;
            if (dist(midpt.getX() + (len * Math.sqrt((1/(1 + slp*slp)))), midpt.getY() + (len * slp * Math.sqrt((1/(1 + slp*slp)))), 256, 256) >
                dist(midpt.getX() - (len * Math.sqrt((1/(1 + slp*slp)))), midpt.getY() - (len * slp * Math.sqrt((1/(1 + slp*slp)))), 256, 256) ){
                    x = midpt.getX() + (len * Math.sqrt((1/(1 + slp*slp))));
                    y = midpt.getY() + (len * slp * Math.sqrt((1/(1 + slp*slp))));
                }
            else {
                x = midpt.getX() - (len * Math.sqrt((1/(1 + slp*slp))));
                y = midpt.getY() - (len * slp * Math.sqrt((1/(1 + slp*slp))));
            }
        } else {
            if(dist(midpt.getX(), midpt.getY() + len, 256, 256) > dist(midpt.getX(), midpt.getY() - len, 256, 256)){
                x = midpt.getX();
                y = midpt.getY() + len;
            } else {
                x = midpt.getX();
                y = midpt.getY() - len;
            }
        }

        return new Point((int)x, (int)y);
    }

    //distance formula
    private double dist(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        KochFractal kch = new KochFractal(4);
    }
}
