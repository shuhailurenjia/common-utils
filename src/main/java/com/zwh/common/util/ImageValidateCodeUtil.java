package com.zwh.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageValidateCodeUtil {

	private int w = 70;
	private int h = 26;
	
	/**
	 * 创建验证码图片
	 * @return  [验证码，base64图片]
	 * @throws IOException
	 */
	public String[] createImage() throws IOException {

		/*
		 * 得到参数高，宽，都为数字时，则使用设置高宽，否则使用默认值
		 */
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		/*
		 * 生成背景
		 */
		createBackground(g);

		/*
		 * 生成字符
		 */
		String s = createCharacter(g);

		g.dispose();
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "png", os);
		byte[] bytes = os.toByteArray();
		char[] bs64Char = Base64.encode(bytes);
		String bs64 = new String(bs64Char);
		String[] result = new String[]{s,bs64.trim()};
		return result;
	}

	private Color getRandColor(int fc, int bc) {
		int f = fc;
		int b = bc;
		Random random = new Random();
		if (f > 255) {
			f = 255;
		}
		if (b > 255) {
			b = 255;
		}
		return new Color(f + random.nextInt(b - f), f + random.nextInt(b - f), f + random.nextInt(b - f));
	}

	private void createBackground(Graphics g) {
		// 填充背景
		g.setColor(getRandColor(220, 250));
		g.fillRect(0, 0, w, h);
		// 加入干扰线条
		for (int i = 0; i < 8; i++) {
			g.setColor(getRandColor(40, 150));
			Random random = new Random();
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int x1 = random.nextInt(w);
			int y1 = random.nextInt(h);
			g.drawLine(x, y, x1, y1);
		}
	}

	private String createCharacter(Graphics g) {
		char[] codeSeq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
		String[] fontTypes = { "Arial", "Arial Black", "AvantGarde Bk BT", "Calibri" };
		Random random = new Random();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);// random.nextInt(10));
			g.setColor(new Color(50 + random.nextInt(100), 50 + random.nextInt(100), 50 + random.nextInt(100)));
			g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)], Font.BOLD, 26));
			g.drawString(r, 15 * i + 5, 19 + random.nextInt(8));
			// g.drawString(r, i*w/4, h-5);
			s.append(r);
		}
		return s.toString();
	}

}
