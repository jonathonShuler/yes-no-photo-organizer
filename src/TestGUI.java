import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestGUI {
    int count = 0;
    JFrame frame;
    JPanel mainPanel;
    JPanel imagePanel;
    JPanel bottomPanel;
    JButton button;
    JButton button2;
    JLabel label;
    JLabel imageLabel;

    public TestGUI() throws IOException {
        frame = new JFrame();
        mainPanel = new JPanel();
        imagePanel = new JPanel();
        bottomPanel = new JPanel();
        button = new JButton(new AbstractAction("Increase Count") {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                label.setText("Count: " + count);
            }
        });
        button2 = new JButton(new AbstractAction("Decrease Count") {
            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                label.setText("Count: " + count);
            }
        });
        label = new JLabel("Count: " + count);

        BufferedImage image = ImageIO.read(new File("C:\\Users\\General\\Desktop\\Google Drive\\Hobby\\Rocks\\Photography\\Other\\test\\Canon-EOS-90D.JPG"));

        Image resizedImage = image.getScaledInstance(696*2,464*2, Image.SCALE_SMOOTH);
        imageLabel = new JLabel(new ImageIcon(resizedImage));
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        bottomPanel.add(button);
        bottomPanel.add(button2);
        bottomPanel.add(label);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(imagePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Test GUI");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new TestGUI();
    }
}
