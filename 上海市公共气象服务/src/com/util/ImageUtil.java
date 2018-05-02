package com.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static void 正方形图片(File file)
	{
		try {
		BufferedImage img = ImageIO.read(file);
		String name=file.getName();
		int size = Math.min(img.getWidth(), img.getHeight());
		BufferedImage nimg = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		Graphics g = nimg.getGraphics();
		int dx = (img.getWidth() - size) / 2;
		int dy = (img.getHeight() - size) / 2;
		g.drawImage(img, 0, 0, size, size, dx, dy, dx + size, dy + size, null);
		g.dispose();
		String prefix = name.substring(name.lastIndexOf(".") + 1);
		
			ImageIO.write(nimg, prefix, file);
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
	}
}
