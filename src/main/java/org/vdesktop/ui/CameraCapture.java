/*
 * Copyright (c) 2011-2015 Jarek Sacha. All Rights Reserved.
 *
 * Author's e-mail: jpsacha at gmail.com
 */

package org.vdesktop.ui;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameGrabber;

public class CameraCapture {
	public static void main(String[] args) throws Exception {
		/*
		 * OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
		 * grabber.start();
		 */
		FrameGrabber grabber = FrameGrabber.createDefault(0);
		grabber.setFormat("video4linux2");
		grabber.start();
		Frame grabbedImage = grabber.grab();
		CanvasFrame frame = new CanvasFrame("视频格式",
				CanvasFrame.getDefaultGamma() / grabber.getGamma());
		while (frame.isVisible() && (grabbedImage = grabber.grab()) != null) {
			frame.showImage(grabbedImage);
		}
		frame.dispose();
		grabber.stop();
	}
}