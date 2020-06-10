import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;

/**
 * Generates the GUI used by the user to enter parameters for the Julia Fractal
 */
public class GUI{

    private JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    private boolean color = true;

    public GUI(){

        juliaGUI();

        panel.setLayout(new BoxLayout(panel, 1));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Julia Set Generator");
        frame.pack();
        frame.setVisible(true);
    }
    
    //method for generating GUI elements for a Koch Fractal
    @Deprecated
    private void kochGUI(){
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
        panel.add(kochSlider);
        panel.add(kochButton);

    }

    /**
     * Method for generating GUI elements for customizing JuliaFractals
     */
    private void juliaGUI() {
        //The two fields for entering the real and imaginary parts of the parameter, C
        JTextField realField = new JTextField("-0.4", 8);
        JTextField imagField = new JTextField("0.6", 8);

        //checkbox to determine whether the fractal should be drawn in color
        JCheckBox colorBox = new JCheckBox("Enable Color", true);
        colorBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {         
               if(e.getStateChange() == e.SELECTED){
                   color = true;
               } else {
                   color = false;
               }
            }           
         });

        //button that generates the JuliaFractal
        JButton juliaButton = new JButton(new AbstractAction("Create Julia Fractal"){
            @Override
            public void actionPerformed(ActionEvent arg0){
                //convert Strings from text fields to doubles
                double a = Double.parseDouble(realField.getText());
                double b = Double.parseDouble(imagField.getText());

                ComplexNum c = new ComplexNum(a, b);
                new JuliaFractal(c, color);
            }
        });
        
        //values of C that make cool-looking Julia Sets
        String suggestedValues = "\nSuggested Values:\n-0.4+0.6i\n0.285+0.01i\n0.285+0i\n-0.7269+0.1889i\n-0.8-0.15";

        //Add all this stuff to the panel.
        panel.add(new JLabel("A julia fractal takes a complex parameter, C"));
        panel.add(new JLabel("Real Component of C"));
        panel.add(realField);
        panel.add(new JLabel("Imaginary Component of C"));
        panel.add(imagField);
        panel.add(juliaButton);
        panel.add(colorBox);
        panel.add(new JTextArea(suggestedValues));
    }

    /**
     * Main Method of the program.
     * Creates a window with the GUI
     */
    public static void main(String[] args) {
        GUI gui = new GUI();
    }

}