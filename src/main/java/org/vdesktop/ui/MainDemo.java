package org.vdesktop.ui;

import com.sun.jna.Library;
import com.sun.jna.Native;

interface Kernel32 extends Library {

	Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32",
			Kernel32.class);

	int GetLogicalDriveStringsA(int length, byte[] buffer);

	int GetSystemDirectoryA(byte[] buffer, int size);
}

public class MainDemo {
	public static void main(String[] args) throws Exception{
		Kernel32 lib = Kernel32.INSTANCE;
		byte[] buffer2 = new byte[100];
		lib.GetLogicalDriveStringsA(buffer2.length / 2, buffer2);
		System.out.println(new String(buffer2,"GB18030"));

		byte[] buffer = new byte[50];

		lib.GetSystemDirectoryA(buffer, 50);
		for (byte bt : buffer) {
			System.out.print((char) bt);
		}
	}
}
