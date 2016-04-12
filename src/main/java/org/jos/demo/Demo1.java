package org.jos.demo;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.awt.image.renderable.RenderableImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/***
 * 录屏测试
 *
 * @author SanKai
 */
public class Demo1 extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -5993317698571152785L;
    ImageIcon icon = null;

    public BufferedImage convertRenderedImage(RenderedImage img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        ColorModel cm = img.getColorModel();
        int width = img.getWidth();
        int height = img.getHeight();
        WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        Hashtable properties = new Hashtable();
        String[] keys = img.getPropertyNames();
        if (keys != null) {
            for (int i = 0; i < keys.length; i++) {
                properties.put(keys[i], img.getProperty(keys[i]));
            }
        }
        BufferedImage result = new BufferedImage(cm, raster, isAlphaPremultiplied, properties);
        img.copyData(raster);
        return result;
    }


    public Demo1() throws Exception {
        // TODO Auto-generated constructor stub
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Robot robot = new Robot();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Rectangle rectangle = new Rectangle(toolkit.getScreenSize());
        BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        //RenderedImage
        //RenderedImage renderableImage = bufferedImage;
        //ImageIO.
        System.out.println(bufferedImage instanceof RenderedImage);
        ImageIO.write(bufferedImage, "png", new FileOutputStream(new File("1.png")));
        icon = new ImageIcon(bufferedImage);


    }


    @Override
    public void update(Graphics g) {

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        if (robot == null) {
            return;
        }
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Rectangle rectangle = new Rectangle(toolkit.getScreenSize());
        BufferedImage bufferedImage = robot.createScreenCapture(rectangle);

        icon = new ImageIcon(bufferedImage);
        Image image = icon.getImage().getScaledInstance(getWidth(),getHeight(),Image.SCALE_DEFAULT);
        icon.setImage(image);
        System.out.println(icon);
        icon.paintIcon(this, g, 0, 0);
        System.out.println(new Date());

    }



    public static void main(String[] args) throws Exception {
        final Demo1 demo1 = new Demo1();
        demo1.setVisible(true);
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    demo1.update(demo1.getGraphics());
                }

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

}
