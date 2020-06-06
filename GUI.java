import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;

public class GUI{

    private JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    private boolean color = true;

    public GUI(){

        //kochGUI();
        juliaGUI();

        panel.setLayout(new BoxLayout(panel, 1));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Julia Set Generator");
        frame.pack();
        frame.setVisible(true);
    }
    
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

    private void juliaGUI() {
        JTextField realField = new JTextField("-0.4", 8);
        JTextField imagField = new JTextField("0.6", 8);

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

        JButton juliaButton = new JButton(new AbstractAction("Create Julia Fractal"){
            @Override
            public void actionPerformed(ActionEvent arg0){
                double a = Double.parseDouble(realField.getText());
                double b = Double.parseDouble(imagField.getText());

                ComplexNum c = new ComplexNum(a, b);
                new JuliaFractal(c, color);
            }
        });

        String suggestedValues = "\nSuggested Values:\n-0.4+0.6i\n0.285+0.01i\n-0.7269 + 0.1889i";

        panel.add(new JLabel("A julia fractal takes a complex parameter, C"));
        panel.add(new JLabel("Real Component of C"));
        panel.add(realField);
        panel.add(new JLabel("Imaginary Component of C"));
        panel.add(imagField);
        panel.add(juliaButton);
        panel.add(colorBox);
        panel.add(new JTextArea(suggestedValues));
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
    }

}