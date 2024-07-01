package frame_utility;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.io.File;

public class ImageUtils {

    public static ImageIcon createThumbnailIcon(String isbn) {
        ImageIcon icon = null;
        String path = FilePath.getFilePath();
        String thumbnailPath = path + isbn + ".jpg";

        try {
            File file = new File(thumbnailPath);
            if (file.exists()) {
                icon = new ImageIcon(thumbnailPath);
                Image image = icon.getImage();
                Image scaledImage = image.getScaledInstance(70, 80, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledImage);
            } else {
                System.err.println("이미지 파일이 존재하지 않습니다: " + thumbnailPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return icon;
    }
}
