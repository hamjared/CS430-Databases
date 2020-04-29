import javax.swing.*;

public class Main {

    public static void main(String[] args){
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI(frame).panel);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
