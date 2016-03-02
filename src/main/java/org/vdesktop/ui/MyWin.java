package org.vdesktop.ui;

import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MyWin extends JFrame {
	public MyWin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setSize(300, 300);
		setTitle("调试两分钟");
		String initialPage = "http://toutiao.com/";  
        
        JEditorPane jep = new JEditorPane();  
        jep.setEditable(false);  
        //jep.addHyperlinkListener(new LinkFollower(jep));  
          
        try {  
            jep.setPage(initialPage);  
        } catch (IOException e) {  
            System.err.println("Usage: java simpleWebBrowser url");  
            System.err.println(e);  
            System.exit(1);  
        }  
          
        JScrollPane scrollPane = new JScrollPane(jep);
        setContentPane(scrollPane);
	}

	public static void main(String[] args) {
		MyWin win = new MyWin();
		win.setVisible(true);

	}
}
