import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Making a new instance of this class will create a window containg a julia fractal generated given the parameter C
 */
public class JuliaFractal extends JPanel {
    
    private ComplexNum c;

    private int size = 1000;
    private int xCenter = 500, yCenter = 500;
    double scale = 300.0;

    private boolean color;
    /**
     * Constructor for the JuliaFracal class. Generates a JuliaFractal when called.
     * A julia set is generated by repeated iteration of Z^2 + C, where Z is a point on the complex plane, and C is a parameter
     * @param c_term the value of C with which the julia fractal is generated
     * @param do_color whether to draw the fractal in color or black & white
     */
    public JuliaFractal(ComplexNum c_term, boolean do_color){
        //set instance variables
        c = c_term;
        color = do_color;

        //set panel parameters
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(size, size));

        //create JFrame and set parameters
        JFrame frame = new JFrame("Julia Fractal, C = " + c.toString());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(size,size);

        //Method of Java Panel class that adds and event listener that is triggered when mouse is moved
        addMouseMotionListener(new MouseMotionListener(){
            //records the position of the mouse every time it is moved, and compares postion when dragged to previous position to determine direction
            int prevX, prevY;

            @Override
            public void mouseMoved(MouseEvent e){
                prevX = e.getX();
                prevY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e){
                //System.out.println("Dragged!");
                if (e.getX() > prevX && e.getY() > prevY){
                    //moved right and down
                    xCenter += (e.getX() - prevX)/2;
                    yCenter += (e.getY() - prevY)/2;
                } else if (e.getX() > prevX && e.getY() < prevY){
                    //moved right and up
                    xCenter += (e.getX() - prevX)/2;
                    yCenter -= (prevY - e.getY())/2;
                } else if (e.getX() < prevX && e.getY() > prevY) {
                    //moved left and down
                    xCenter -= (prevX - e.getX())/2;
                    yCenter += (e.getY() - prevY)/2;
                } else {
                    //moved left and up
                    xCenter -= (prevX - e.getX())/2;
                    yCenter -= (prevY - e.getY())/2;
                }
                drawFractal(getGraphics());
            }
        });

        //adds a mousewheel listener
        addMouseWheelListener(new MouseWheelListener() {
            //call the zoom() method when mousewheel is moved
            @Override
            public void mouseWheelMoved(MouseWheelEvent e){
                //zoom in by the amount the mouse wheel was moved, * a constant
                zoom(e.getPreciseWheelRotation() * -200);
            }

        });

        //JFrame stuff
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g){
        drawFractal(g);
    }

    //change the scale by an amount, and redraw
    private void zoom(double amt){
        scale += amt;
        drawFractal(this.getGraphics());
    }

    /**
     * Draws a julia fractal given the parameters stored as instance variables
     * @param g java graphics object with which to draw the fractal
     */
    private void drawFractal(Graphics g){
        int z;

        if (!color){
            super.paintComponent(g);
        }

        //loop thru all pixels
        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                //determine complex number that coresponds to each pixel
                z = inFractal(new ComplexNum((x - xCenter)/scale, (y - yCenter)/scale));
                //determine what color to make each pixel
                if (color){
                    g.setColor(genColor(z));
                    g.drawLine(x, y, x, y);
                } else {
                    if(z == 0){
                        g.setColor(Color.BLACK);
                        g.drawLine(x, y, x, y);
                    }
                }
            }
        }
    }

    /**
     * Is a complex number Z part of the Julia Set represented by this object?
     * @param z a complex number
     * @return a boolean representing whether a complex number is part of this set or not
     */
    private int inFractal(ComplexNum z){
        int iteration = 0;
        int max_iterations = 100;
        //there is a theorem that states that if |Z| becomes greater than some number R, where R^2 - R > |C|, Z will tend towards +/- infinity
        double r = c.modulus()*c.modulus() - c.modulus() + 5;
        

        while (z.modulus() < r && iteration < max_iterations){
            z.square();
            z.add(c);

            iteration++;
        }

        if (iteration == max_iterations){
            return 0;
        } else {
            return iteration;
        }
    }

    /**
     * given an integer between 0 and 100, return a corresponding color
     * @param c an integer between 0 and 100
     * @return a color corresponding to that integer
     */
    private Color genColor(int c){
        if (c == 0){
            return Color.BLACK;
        }

        //values determined experimentally
        float h = (c * 0.01f) + 0.5f;
        return Color.getHSBColor(h, 0.7f, 0.9f);
    }

    //main() method for ease of debugging. Please use GUI.main()
    public static void main(String[] args) {
        JuliaFractal j = new JuliaFractal(new ComplexNum(-0.8, -0.15), true);
    }

}