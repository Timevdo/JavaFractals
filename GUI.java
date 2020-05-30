import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;

public class GUI{

    private JFrame frame = new JFrame();

    public GUI(){

        JSlider kochSlider = new JSlider(0, 4, 4);
        kochSlider.setPaintTrack(true);
        kochSlider.setPaintTicks(true);
        kochSlider.setPaintLabels(true);
        kochSlider.setMajorTickSpacing(1);

        JButton kochButton = new JButton(new AbstractAction("Create Koch Fractal"){
        
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new KochFractal(kochSlider.getValue());
                
            }
        });

        JPanel panel = new JPanel();
        panel.add(kochSlider);
        panel.add(kochButton);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);

    }


    public static void main(String[] args) {
        GUI gui = new GUI();
    }

}