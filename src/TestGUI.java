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
    JButton nextButton;
    JButton previousButton;
    JButton directoryButton;
    JLabel label;
    JLabel imageLabel;
    File directory;
    String[] fileList;

    public TestGUI() {
        frame = new JFrame();
        mainPanel = new JPanel();
        imagePanel = new JPanel();
        bottomPanel = new JPanel();
        label = new JLabel("Image: " + count);
        nextButton = new JButton(new AbstractAction("Next") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count < fileList.length-1) {
                    count++;
                    label.setText("Image: " + count);
                    try {
                        showImage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        previousButton = new JButton(new AbstractAction("Previous") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count > 0) {
                    count++;
                    label.setText("Image: " + count);
                    try {
                        showImage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        directoryButton = new JButton(new AbstractAction("Choose Directory") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));
                int chooserOption = fileChooser.showOpenDialog(frame);
                if(chooserOption == JFileChooser.APPROVE_OPTION) {
                    directory = fileChooser.getSelectedFile();
                    System.out.println(directory.getPath());
                    fileList = directory.list();
                    for (String file : fileList) {
                        System.out.println(file);
                    }
                    try {
                        showImage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    System.out.println("No directory selected");
                }
            }
        });

        imageLabel = new JLabel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imageLabel.setVisible(false);
        imagePanel.add(directoryButton, BorderLayout.PAGE_END);

        bottomPanel.add(previousButton);
        bottomPanel.add(nextButton);
        bottomPanel.add(label);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(imagePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Image Viewer");
        frame.pack();
        frame.setVisible(true);
    }

    private void showImage() throws IOException {
        if(fileList.length != 0) {
            BufferedImage image = ImageIO.read(new File(directory.getPath() + "\\" +  fileList[count]));
            Image resizedImage = image.getScaledInstance(696*2,464*2, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(resizedImage));
            if(directoryButton.isVisible()) {
                directoryButton.setVisible(false);;
            }

        } else {
            imageLabel.setText("There are no images in this directory.");
            if(!directoryButton.isVisible()) {
                directoryButton.setVisible(true);;
            }
        }

        if(!imageLabel.isVisible()) {
            imageLabel.setVisible(true);
        }

        frame.pack();
    }

    public static void main(String[] args) throws IOException {
        new TestGUI();
    }
}
