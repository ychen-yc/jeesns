package com.lxinet.jeesns.core.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class ImageUtil {
    public static final String THUMB_DEFAULT_PREVFIX = "thumb_";
    public static final String SMALL_DEFAULT_PREVFIX = "small_";
    private static final int DEFAULT_WIDTH = 260;
    private static final int DEFAULT_HEIGHT = 160;
    private static final int THUMB_DEFAULT_WIDTH = 160;
    private static final int THUMB_DEFAULT_HEIGHT = 160;
    private File targetFile;

    public String dealImage(File imgFile) {
        thumbnailImage(imgFile);

        return cutImage(this.targetFile);
    }

    private String thumbnailImage(File imgFile, String prevfix) {
        String thumbPath = "";
        String fileName = "";
        if (imgFile.exists()) {
            try {
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                if (imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }
                if ((suffix == null) || (types.toLowerCase().indexOf(suffix.toLowerCase()) < 0)) {
                    return thumbPath;
                }
                Image img = ImageIO.read(imgFile);
                int width = img.getWidth(null);
                int height = img.getHeight(null);
                int w;
                int h;
                if (width > height) {
                    h = 160;
                    w = (int) (width * h * 1.0D / (height * 1.0D));
                } else {
                    w = 260;
                    h = (int) (height * w * 1.0D / (width * 1.0D));
                }
                BufferedImage bi = new BufferedImage(w, h, 1);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                fileName = prevfix + imgFile.getName();
                String p = imgFile.getPath();

                thumbPath = p.substring(0, p.lastIndexOf(File.separator)) + File.separator + fileName;
                this.targetFile = new File(thumbPath);
                ImageIO.write(bi, suffix, this.targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    private String thumbnailImage(File imgFile) {
        return thumbnailImage(imgFile, "small_");
    }

    private String cutImage(File imgFile) {
        String fileName = "";
        String thumbPath = "";
        try {
            String types = Arrays.toString(ImageIO.getReaderFormatNames());
            String suffix = null;
            if (imgFile.getName().indexOf(".") > -1) {
                suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
            }
            if ((suffix == null) || (types.toLowerCase().indexOf(suffix.toLowerCase()) < 0)) {
                String str1 = thumbPath;
                return str1;
            }
            Object img = ImageIO.read(imgFile);
            int width = ((Image) img).getWidth(null);
            int height = ((Image) img).getHeight(null);
            int w;
            int x;
            if (width > 160) {
                x = (width - 160) / 2;
                w = 160 + x;
            } else {
                w = width;
                x = 0;
            }
            int h;
            int y;
            if (height > 160) {
                y = (height - 160) / 2;
                h = 160 + y;
            } else {
                h = height;
                y = 0;
            }
            BufferedImage bi = new BufferedImage(160, 160, 1);
            Graphics g = bi.getGraphics();
            g.drawImage((Image) img, 0, 0, w, h, x, y, x + w, y + h, null);
            g.dispose();

            String name = imgFile.getName();
            name = name.replaceAll("small_", "");
            fileName = "thumb_" + name;
            String p = imgFile.getPath();

            thumbPath = p.substring(0, p.lastIndexOf(File.separator)) + File.separator + fileName;
            this.targetFile = new File(thumbPath);
            ImageIO.write(bi, suffix, this.targetFile);
        } catch (Exception e) {
            e =


                    e;
            e.printStackTrace();
        } finally {
        }
        return fileName;
    }
}
