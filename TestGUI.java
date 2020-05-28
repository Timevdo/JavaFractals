import java.awt.*;
import javax.swing.*;

public class TestGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(256,256);
        
        frame.getContentPane().add(new MyPanel());
        frame.pack();
        frame.setVisible(true);
    }
}

class MyPanel extends JPanel {
    public MyPanel(){
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(256, 256));
    }

    @Override
    public void paintComponent(Graphics page){
        super.paintComponent(page);
        Polygon poly = new Polygon(new int[] {64, 128, 192, 128}, new int[] {128, 64, 128, 192}, 4);
        page.drawPolygon(poly);
    }   
}

