package com.zwh.common.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created with IntelliJ IDEA. User: Suhua Date: 14-5-1 Time: 上午11:10 To change this template use File | Settings | File Templates.
 */
public class SerializeUtil
{

	public static byte[] serialize(Object object)
	{
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try
		{
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e)
		{
			e.printStackTrace();

		}
		return null;
	}

	public static Object unserialize(byte[] bytes)
	{
		ByteArrayInputStream bais = null;
		try
		{
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e)
		{
			e.printStackTrace();

		}
		return null;
	}

	public static <T> T reSerializableObject(byte[] byteCode)
	{
		if (byteCode == null || byteCode.length < 1)
		{
			return null;
		}
		ByteArrayInputStream bIs = new ByteArrayInputStream(byteCode);
		try
		{
			ObjectInputStream oIs = new ObjectInputStream(bIs);
			return (T) oIs.readObject();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
