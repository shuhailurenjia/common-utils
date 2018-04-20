package com.zwh.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.shiro.codec.Base64;

public class Base64ToImage {

	/**
	 * @Descriptionmap 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * @author temdy
	 * @Date 2015-01-26
	 * @param path
	 *            图片路径
	 * @return
	 */
	public static String imageToBase64(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		try (InputStream in = new FileInputStream(path);) {
			data = new byte[in.available()];
			in.read(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		return Base64.encodeToString(data);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * @Descriptionmap 对字节数组字符串进行Base64解码并生成图片
	 * @author temdy
	 * @Date 2015-01-26
	 * @param base64
	 *            图片Base64数据
	 * @param path
	 *            图片路径
	 * @return
	 */
	public static boolean base64ToImage(String base64, String path) {// 对字节数组字符串进行Base64解码并生成图片
		if (base64 == null) { // 图像数据为空
			return false;
		}
		try (OutputStream out = new FileOutputStream(path);) {
			// Base64解码
			byte[] bytes = Base64.decode(base64);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			out.write(bytes);
			out.flush();
			return true;
		} catch (Exception e) {
			System.out.println("=======>9999图片保存错误：" + e.getMessage());
			return false;
		}
	}
}
