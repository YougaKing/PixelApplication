package com.youga.pixel;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by YougaKing on 2016/10/20.
 */

public class Pixel {


    static final String FILE_DIR = "E:/CGSOFT-Company/Pixel";
    static final String[] SUPPORT_PIXELS = new String[]{
            "240x320",//ldpi
            "320x480",//mdpi
            "480x800",//hdpi
            "480x854",
            "540x960",
            "600x1024",
            "720x1184",
            "720x1196",
            "720x1280",//xhdpi
            "768x1024",
            "768x1134",
            "768x1280",
            "800x1280",
            "800x1216",
            "1080x1794",
            "1080x1812",
            "1080x1920",//xxhdpi
            "1200x1920",
            "1440x2560",//xxxhdpi
            "1536x1952"
    };

    public void generate(int width, int height) {
        File dir = new File(FILE_DIR);
        if (!dir.exists()) dir.mkdirs();

        for (String pixel : SUPPORT_PIXELS) {
            String[] wH = pixel.split("x");
            int w = Integer.valueOf(wH[0]);
            int h = Integer.valueOf(wH[1]);

            File fileDir = new File(dir.getPath() + File.separator + "values-" + h + "x" + w);
            if (!fileDir.exists()) fileDir.mkdirs();
            File xFile = new File(fileDir, "dim_x.xml");
            File yFile = new File(fileDir, "dim_y.xml");

            writeXml(xFile, changeStrings(w, width, "x"));
            writeXml(yFile, changeStrings(h, height, "y"));
        }
    }

    private void writeXml(File file, String strings) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(file));
            writer.print(strings);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String changeStrings(int actual, int basic, String prefix) {
        //320 / x = 720 / 320;
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        builder.append("<resources>\n");
        float xRatio = actual / (float) basic;
        for (int i = 1; i <= basic; i++) {
            builder.append("<dimen name=\"" + prefix + i + "\">" + change(i * xRatio) + "px</dimen>\n");
            System.out.println(i + ":" + change(i * xRatio));
        }
        builder.append("</resources>");
        return builder.toString();
    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }

    @Test
    public void Test() {
        int width = 720;
        int height = 1280;

        generate(width, height);
    }

}
