package com.jlabs.lab6;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class EncodingCipher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EncodingCipher ciph = new EncodingCipher();
		try {
			ciph.encrypt("clean.txt", "encoded.txt");
			ciph.decrypt("encoded.txt", "decoded.txt");
			System.out.print("OK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	private String getEncoding(int num)
	{
		String encoding;
				if (num == 0)
					encoding = "windows-1250";
				else
					if (num == 1)
						encoding = "windows-1251";
					else
						if (num == 2)
							encoding = "windows-1252";
						else
								if (num == 3)
									encoding = "windows-1254";
								else	
									if (num == 4)
										encoding = "windows-1257";
									else
										encoding = "windows-1257";
		return encoding;
	}
	
	public void encrypt(String input, String output) throws IOException
	{
		String inText = new Scanner(new File(input)).useDelimiter("\\Z").next();
		byte[] data = inText.getBytes("KOI8-R");
		PrintWriter out = new PrintWriter(output);
		int num = 0;
		for(byte bByte : data)
		{
			byte[] dData = {bByte};
			String outText = new String(dData, getEncoding(num));
			num=(num+1)%8;
			out.print(outText);
		}
		out.close();
	}
	
	public void decrypt(String input, String output) throws IOException
	{
		String inText = new Scanner(new File(input)).useDelimiter("\\Z").next();
		PrintWriter out = new PrintWriter(output);
		int num = 0;
		for (int i=0; i<inText.length(); i++)
		{
			String tStr = inText.substring(i, i+1);
			byte[] data = tStr.getBytes(getEncoding(num));
			String outText = new String(data, "KOI8-R");
			num=(num+1)%8;
			out.print(outText);
		}
		out.close();
	}
}

