import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestGUI implements ActionListener {
    int clicks = 0;
    JFrame frame;
    JPanel panel;
    JButton button;
    JLabel label;

    public TestGUI() {
        frame = new JFrame();
        panel = new JPanel();
        button = new JButton("Test Button");
        label = new JLabel("Number of clicks: " + clicks);

        button.addActionListener(this);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Test GUI");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clicks++;
        label.setText("Number of clicks: " + clicks);
    }

    public static void main(String[] args) {
        new TestGUI();
    }
}

//Based on example from https://www.youtube.com/watch?v=5o3fMLPY7qY