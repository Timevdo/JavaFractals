import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;

public class JuliaFractal extends JPanel {
    
    private ComplexNum c;

    private int precision = 100;
    private int size = 1000, offset = size/2;
    double scale = 300.0;

    private boolean color;

    public JuliaFractal(ComplexNum c_term, boolean do_color){
        c = c_term;
        color = do_color;

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(size, size));

        JFrame frame = new JFrame("Julia Fractal, C = " + c.toString());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(size,size);

        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

    }
    
    @Override
    public void paintComponent(Graphics g){
        int z;

        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                z = inFractal(new ComplexNum((x - offset)/scale, (y - offset)/scale));
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

        while (z.modulus() < 3 && iteration < max_iterations){
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

        double h = c * (255.0/100.0);
        return new Color((int)h, Math.abs(128 - (int)h), 255);
    }

    public static void main(String[] args) {
        JuliaFractal j = new JuliaFractal(new ComplexNum(-0.8, -0.15), true);
    }

}