import javax.swing.*;
import java.awt.*;

public class JuliaFractal extends JPanel {
    
    private double cx, cy;

    private int precision = 100;
    private int size = 500, offset = size/2;
    double scale = 125.0;

    public JuliaFractal(double ctermx, double ctermy){
        cx = ctermx;
        cy = ctermy;

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(size, size));

        JFrame frame = new JFrame("Julia Fractal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(size,size);

        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g){
        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                if (inFractal((x - offset)/scale, (y - offset)/scale)){
                    g.drawLine(x, y, x, y);
                }
            }
        }
    }

    /**
     * Is a complex number Z part of the Julia Set represented by this object?
     * @param z a complex number
     * @return a boolean representing whether a complex number is part of this set or not
     */
    private boolean inFractal(double zx, double zy){
        int iteration = 0;
        int max_iterations = 100;
        double xtemp;

        while (zx * zx + zy * zy < 4 && iteration < max_iterations){
            xtemp = zx * zx - zy * zy;
            zy = 2 * zx * zy + cy;
            zx = xtemp + cx;

            iteration++;
        }

        return (iteration == max_iterations);
    }

    public static void main(String[] args) {
        JuliaFractal j = new JuliaFractal(-0.4, 0);
    }

}