package org.vdesktop.ui;

import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinUser.*;
import com.sun.jna.platform.KeyboardUtils;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;

public class MouseLLHook {
	private static boolean quit;
	private static HHOOK hhk;

	public static void main(String[] args) {
		
		final User32 lib = User32.INSTANCE;
		Kernel32 kernel32 = Kernel32.INSTANCE;
		HMODULE hMod = kernel32.GetModuleHandle(null);
		LowLevelKeyboardProc keyboardHook = new LowLevelKeyboardProc() {

			@Override
			public LRESULT callback(int nCode, WPARAM wParam,
					KBDLLHOOKSTRUCT info) {
				System.out.println(info.flags);
				System.out.println(info.vkCode);
				if (nCode >= 0) {
					if (info.vkCode == 81) {
						//quit = true;
					}
				}
				return lib
						.CallNextHookEx(hhk, nCode, wParam, info.getPointer());
			}

		};

		hhk = lib.SetWindowsHookEx(WinUser.WH_KEYBOARD_LL, keyboardHook, hMod,
				0);
		System.out
				.println("Keyboard hook installed, type anywhere, 'q' to quit");
		new Thread() {
			public void run() {
				while (!quit) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.err.println("unhook and exit");
				lib.UnhookWindowsHookEx(hhk);
				System.exit(0);
			}
		}.start();

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

}