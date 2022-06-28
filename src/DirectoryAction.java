//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class DirectoryAction extends AbstractAction {
//    JFrame frame;
//    File directory;
//    String[] fileList;
//
//    public DirectoryAction(String name, JFrame frame) {
//        super(name);
//        this.frame = frame;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));
//        int chooserOption = fileChooser.showOpenDialog(frame);
//        if(chooserOption == JFileChooser.APPROVE_OPTION) {
//            directory = fileChooser.getSelectedFile();
//            System.out.println(directory.getPath());
//            fileList = directory.list();
////            AppData.setFileArrayList(fileList);
//            fileArrayList = new ArrayList<String>();
//            Collections.addAll(fileArrayList, fileList);
//            for (String file : fileArrayList) {
//                System.out.println(file);
//            }
//            try {
//                TestGUI.showImage();
//                showImage();
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        } else {
//            System.out.println("No directory selected");
//        }
//    }
//}
