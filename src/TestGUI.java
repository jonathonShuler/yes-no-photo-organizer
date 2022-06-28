import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestGUI {
    private static TestGUI testGUI = new TestGUI();
    int count = 0;
    File directory;
//    ArrayList<String> fileArrayList;
    String[] directoryItems;
    GraphicsDevice graphicsDevice;
    JFrame frame;
    JPanel mainPanel;
    JPanel imagePanel;
    JPanel bottomPanel;
    JPanel testPanel;
    JButton keepButton;
    JButton discardButton;
    JButton nextButton;
    JButton previousButton;
    JButton directoryButton;
    JLabel label;
    JLabel imageLabel;


    public TestGUI() {
        frame = new JFrame();
        mainPanel = new JPanel();
        imagePanel = new JPanel();
        bottomPanel = new JPanel();
        testPanel = new JPanel();
        label = new JLabel("Image: " + count);
        graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        keepButton = new JButton(new AbstractAction("Keep Image") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Files.exists(Paths.get(directory.getPath() + "\\Keep\\"))) {
                    new File(directory.getPath() + "\\Keep\\").mkdir();
                }
                try {
                    Path keep = Files.move(Paths.get(directory.getPath() + "\\" +  AppData.getFileFromList(count)), Paths.get(directory.getPath() + "\\Keep\\" +  AppData.getFileFromList(count)));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                AppData.removeFileFromList(count);
                if(count < AppData.getFileListSize()-1) {
                    try {
                        showImage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    label.setText("Image: " + count);
                } else {
                    count--;
                    try {
                        showImage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    label.setText("Image: " + count);
                }
            }
        });

        discardButton = new JButton(new AbstractAction("Discard Image") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Files.exists(Paths.get(directory.getPath() + "\\Discard\\"))) {
                    new File(directory.getPath() + "\\Discard\\").mkdir();
                }
                try {
                    Path keep = Files.move(Paths.get(directory.getPath() + "\\" +  AppData.getFileFromList(count)), Paths.get(directory.getPath() + "\\Discard\\" +  AppData.getFileFromList(count)));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                AppData.removeFileFromList(count);
                if(count < AppData.getFileListSize()-1) {
                    try {
                        showImage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    label.setText("Image: " + count);
                } else {
                    count--;
                    try {
                        showImage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    label.setText("Image: " + count);
                }
            }
        });

        nextButton = new JButton(new AbstractAction(">") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count < AppData.getFileListSize()-1) {
                    count++;
                    try {
                        showImage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    label.setText("Image: " + count);
                }
            }
        });

        previousButton = new JButton(new AbstractAction("<") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count > 0) {
                    count--;
                    try {
                        showImage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    label.setText("Image: " + count);
                }
            }
        });

        directoryButton = new JButton(new AbstractAction("Choose Image Directory") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));
                int chooserOption = fileChooser.showOpenDialog(frame);
                if(chooserOption == JFileChooser.APPROVE_OPTION) {
                    directory = fileChooser.getSelectedFile();
                    System.out.println(directory.getPath());
                    directoryItems = directory.list();
                    AppData.setFileList(directoryItems);
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
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setVisible(false);
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        bottomPanel.add(previousButton);
        bottomPanel.add(discardButton);
        bottomPanel.add(keepButton);
        bottomPanel.add(nextButton);
        bottomPanel.add(label);

        testPanel.setLayout(new BoxLayout(testPanel, BoxLayout.Y_AXIS));
        directoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        testPanel.add(directoryButton);
        testPanel.add(bottomPanel);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(imagePanel, BorderLayout.CENTER);
        mainPanel.add(testPanel, BorderLayout.PAGE_END);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Image Viewer");
        frame.setSize(getDimension());
        frame.setVisible(true);
    }

    public static TestGUI getInstance() {
        return testGUI;
    }

    private BufferedImage validateFile(File file) throws IOException {
        if(file.isFile()){
            return ImageIO.read(file);
        } else {
            return null;
        }
    }

    private Dimension getDimension() {
        int screenWidth = graphicsDevice.getDisplayMode().getWidth();
        int screenHeight = graphicsDevice.getDisplayMode().getHeight();
        int applicationWidth = (int) Math.round(screenWidth*0.45);
        int applicationHeight = (int) Math.round(screenHeight*0.55);
        return new Dimension(applicationWidth, applicationHeight);
    }

    public void showImage() throws IOException {
        if(AppData.getFileListSize() != 0) {
            File file = new File(directory.getPath() + "\\" +  AppData.getFileFromList(count));
            BufferedImage image = validateFile(file);
            if(image != null) {
                int imageHeight = image.getHeight();
                int imageWidth = image.getWidth();
                Image resizedImage;
                if(imageHeight >= imageWidth) {
                    int height = (int) Math.round(frame.getHeight()*0.9);
                    resizedImage = image.getScaledInstance(-1, height, Image.SCALE_SMOOTH);
                } else {
                    int width = (int) Math.round(frame.getWidth()*0.9);
                    resizedImage = image.getScaledInstance(width,-1, Image.SCALE_SMOOTH);
                }
                imageLabel.setIcon(new ImageIcon(resizedImage));
                if(directoryButton.isVisible()) {
                    directoryButton.setVisible(false);;
                }
            } else {
                AppData.removeFileFromList(count);
                if(count == AppData.getFileListSize()) {
                    count--;
                }
                showImage();
            }
        } else {
            imageLabel.setText("There are no images in this directory. Choose a different directory.");
            if(!directoryButton.isVisible()) {
                directoryButton.setVisible(true);;
            }
        }

        if(!imageLabel.isVisible()) {
            imageLabel.setVisible(true);
        }
    }


}
