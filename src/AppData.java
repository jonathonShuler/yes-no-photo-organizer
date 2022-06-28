import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class AppData {
    private static AppData appData = new AppData();
    private static File directory;
    private static ArrayList<String> fileList = new ArrayList<>();

    private AppData() {}

    public static AppData getInstance() {
        return appData;
    }

    //Methods for file list
    public static void setFileList(String[] directoryItems) {
        Collections.addAll(fileList, directoryItems);
        for (String file : fileList) {
            System.out.println(file);
        }
    }

    public static int getFileListSize() {
        return fileList.size();
    }

    public static String getFileFromList(int index) {
        return fileList.get(index);
    }

    public static void removeFileFromList(int index) {
        fileList.remove(index);
    }

}
