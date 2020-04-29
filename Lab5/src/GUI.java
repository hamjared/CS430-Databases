import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI{
    public JPanel panel;
    private JTabbedPane tabbedPane;
    private JTextField enterMemberIDTextField;
    private JTextField textField3;
    private JButton submitButton;
    private JPanel bookPane;
    private JPanel memberIDPane;

    public GUI(JFrame frame) {

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setEnabledAt(1,true);
                tabbedPane.setSelectedIndex(1);

            }
        });
    }
}
