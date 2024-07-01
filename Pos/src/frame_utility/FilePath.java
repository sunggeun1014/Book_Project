package frame_utility;

public class FilePath {
    private static String FILE_PATH = "images/thumbnail/";

    public static String getFilePath() {
        return FILE_PATH;
    }

    public static void setFilePath(String newPath) {
        FILE_PATH = newPath;
    }
}