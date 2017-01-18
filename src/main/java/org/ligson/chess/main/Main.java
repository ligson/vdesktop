package org.ligson.chess.main;

import org.ligson.chess.model.QiPan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * Created by ligson on 2017/1/16.
 */
public class Main extends JFrame implements MouseListener, KeyListener {
    private static File bgFile = new File(Thread.currentThread().getContextClassLoader().getResource("assets/b2.jpg").getFile());
    private static Image bg = Toolkit.getDefaultToolkit().createImage(bgFile.getAbsolutePath());
    private QiPan qiPan;

    public Main() {
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setBackground(new Color(0xF4, 0xE2, 0xBC));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addMouseListener(this);
        addKeyListener(this);
        qiPan = new QiPan(new Point(50, 24 + 50), this);
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(bg, 0, 0, this);

        qiPan.paint(g);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setVisible(true);
        main.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            qiPan.processClick(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F1) {
            qiPan.init();
            repaint();
        }else if(e.getKeyCode()==KeyEvent.VK_F2){
            qiPan.huiqi();
            repaint();
        }
    }
}
