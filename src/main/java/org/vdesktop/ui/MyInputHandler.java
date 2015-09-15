package org.vdesktop.ui;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;

import javax.swing.JFrame;


public class MyInputHandler {
	public static void main(String[] args) {
		JFrame jFrame = new JFrame("x");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setSize(400, 400);
		jFrame.setVisible(true);
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

			@Override
			public void eventDispatched(AWTEvent event) {
				System.out.println(event);

			}
		}, AWTEvent.KEY_EVENT_MASK);
	}
}
