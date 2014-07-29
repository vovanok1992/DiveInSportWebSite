/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vovanok.commons;

import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

/**
 *
 * @author vovan_000
 */
public class ImageUtils {

    public void saveSmallImage(File dir, String fileName) throws IOException, ImagingOpException, IllegalArgumentException {
        BufferedImage img = ImageIO.read(new File(dir, fileName));
        BufferedImage scaledImg = Scalr.resize(img, 150);
        String nameOfFile = fileName;
        String type = nameOfFile.substring(nameOfFile.lastIndexOf(".") + 1).toLowerCase();
        ImageIO.write(scaledImg, type, new File(dir, fileName + "_small." + type));
    }

    public void saveImage(File dir, InputStream inp, String fileName) throws IOException, FileNotFoundException {

        OutputStream out = new FileOutputStream(new File(dir, fileName));
        int read;
        byte[] bytes = new byte[1024];
        while ((read = inp.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        inp.close();
        out.flush();
        out.close();

    }

}
