package org.vdesktop.event.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.vdesktop.event.Key;
import org.vdesktop.event.KeyEventCallBack;
import org.vdesktop.event.KeyHandler;

public class Test implements KeyEventCallBack {
	public static void main(String[] args) {
		Test test = new Test();
		KeyHandler keyHandler = new KeyHandler(test);
		keyHandler.start();
	}

	@Override
	public void onKeyDown(List<Key> keyCodeList) {
		System.out.println("down............");
		for(Key key:keyCodeList){
			if(key.isDown()){
				System.out.println(key.getKeyCode());
			}
		}

	}

	@Override
	public void onKeyUp(int keyCode) {
		System.out.println("up..........."+keyCode);
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_0);
			robot.keyRelease(KeyEvent.VK_0);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
