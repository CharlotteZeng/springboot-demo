package com.springbootdemo.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	/**
	 * 16位MD5串
	 * @param sourceStr
	 * @return
	 * @throws Exception
	 */
	public static String str2MD5_16(String sourceStr) throws Exception {
		String result = "";
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(sourceStr.getBytes());
		byte[] b = md.digest();
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0) {
                i += 256;
            }
			if (i < 16) {
                buf.append("0");
            }
			buf.append(Integer.toHexString(i));
		}
		result = buf.toString().substring(8, 24);
		return result;
	}
	
	/**
	 * 32位MD5串
	 * @param sourceStr
	 * @return
	 * @throws Exception
	 */
	public static String str2MD5_32(String sourceStr) throws Exception {
		String result = "";
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(sourceStr.getBytes());
		byte[] b = md.digest();
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0) {
                i += 256;
            }
			if (i < 16) {
                buf.append("0");
            }
			buf.append(Integer.toHexString(i));
		}
		result = buf.toString();
		return result;
	}
	/**
	 * 32位MD5串
	 * @param sourceStr
	 * @return
	 * @throws Exception
	 */
	public static byte[] md5(String text)throws Exception{
		byte[] returnByte = null; 

		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			// 定义编码方式
			returnByte = md.digest(text.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnByte;
	}

	public static void main(String[] args){
		String str="helloMD5";
		try{
		str2MD5_16(str);
		str2MD5_32(str);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static String md5Sign(String signStr) {

		byte[] signInfo = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			signInfo = md.digest(signStr.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return byteArrayToHexString(signInfo);

	}

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	private static final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
