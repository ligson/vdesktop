package org.vdesktop.event;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;

public class KeyHandler implements LowLevelKeyboardProc, Runnable {
	private KeyEventCallBack callBack;
	private static HHOOK hhk;
	private static User32 lib = User32.INSTANCE;
	private Kernel32 kernel32 = Kernel32.INSTANCE;
	private boolean isExit = false;

	public KeyHandler(KeyEventCallBack callBack) {
		super();
		this.callBack = callBack;
	}

	public KeyEventCallBack getCallBack() {
		return callBack;
	}

	public void setCallBack(KeyEventCallBack callBack) {
		this.callBack = callBack;
	}

	public void start() {

		HMODULE hMod = kernel32.GetModuleHandle(null);
		LowLevelKeyboardProc keyboardHook = this;

		hhk = lib.SetWindowsHookEx(WinUser.WH_KEYBOARD_LL, keyboardHook, hMod,
				0);

		Thread thread = new Thread(this);
		thread.start();

		// 以下部分是干嘛的？
		int result;
		MSG msg = new MSG();
		while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
			if (result == -1) {
				System.err.println("error in get message");
				break;
			} else {
				System.err.println("got message");
				lib.TranslateMessage(msg);
				lib.DispatchMessage(msg);
			}
		}
		lib.UnhookWindowsHookEx(hhk);
	}

	@Override
	public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {
		List<Key> downKeys = new ArrayList<Key>();
		for (Key key : KeyCodeUtils.ALLK_KEYS) {
			if (key.getKeyCode() == info.vkCode) {
				if (info.flags == 0) {
					key.setDown(true);
				} else if (info.flags == 128) {
					key.setDown(false);
				}
			}
			if (key.isDown()) {
				downKeys.add(key);
			}
		}
		if (info.flags == 0) {
			callBack.onKeyDown(downKeys);
		} else {
			callBack.onKeyUp(info.vkCode);
		}

		return lib.CallNextHookEx(hhk, nCode, wParam, info.getPointer());
	}

	@Override
	protected void finalize() throws Throwable {
		isExit = true;
	}

	@Override
	public void run() {
		while (!isExit) {
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		lib.UnhookWindowsHookEx(hhk);
	}

}
