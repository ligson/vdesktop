/*
 * Copyright (c) 2011-2015 Jarek Sacha. All Rights Reserved.
 *
 * Author's e-mail: jpsacha at gmail.com
 */

package org.vdesktop.ui;

import org.bytedeco.javacv.OpenCVFrameGrabber;

public class CameraCapture {
	public static void main(String[] args) throws Exception{
		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(-1);
		grabber.start();
	}
}