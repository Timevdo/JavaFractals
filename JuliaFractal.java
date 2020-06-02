import javax.swing.*;
import java.awt.*;

public class JuliaFractal extends JPanel {
    
    private ComplexNum c;

    private int precision = 100;
    private int size = 1500, offset = size/2;
    double scale = 500.0;

    public JuliaFractal(ComplexNum c_term){
        c = c_term;

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(size, size));

        JFrame frame = new JFrame("Julia Fractal, C = " + c.toString());
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
                if (inFractal(new ComplexNum((x - offset)/scale, (y - offset)/scale))){
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
    private boolean inFractal(ComplexNum z){
        int iteration = 0;
        int max_iterations = 100;

        while (z.modulus() < 2 && iteration < max_iterations){
            z.square();
            z.add(c);

            iteration++;
        }

        return (iteration == max_iterations);
    }

    public static void main(String[] args) {
        JuliaFractal j = new JuliaFractal(new ComplexNum(-0.8, -0.15));
    }

}