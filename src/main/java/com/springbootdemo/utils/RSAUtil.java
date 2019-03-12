package com.springbootdemo.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("restriction")
public class RSAUtil {
	/**
	 * 全文加密
	 * 
	 * @param key
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encrypt(String str, String key) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");  	
			cipher.init(Cipher.ENCRYPT_MODE,getPublicKey(key));
			byte[] enBytes = cipher.doFinal(str.getBytes());			
			str =  (new BASE64Encoder()).encode(enBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 全文解密
	 * 
	 * @param key
	 * @param str
	 * @return
	 */
	public static String decrypt(String str, String key) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");  
			cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(key));
			byte[] deBytes = cipher.doFinal((new BASE64Decoder()).decodeBuffer(str));
			str =  new String(deBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static Map<String, String> keyGenerator() {
		Map<String, String> keyMap = new HashMap<String, String>(2);
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(512);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			RSAPrivateKey privateKeyObject = (RSAPrivateKey) keyPair
					.getPrivate();
			RSAPublicKey publicKeyObject = (RSAPublicKey) keyPair.getPublic();
			String publicKey = getKeyString(publicKeyObject);
			String privateKey = getKeyString(privateKeyObject);
			keyMap.put("publicKey", publicKey);
			keyMap.put("privateKey", privateKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyMap;
	}

	/**
	 * 得到公钥
	 * @param key 密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * 得到私钥
	 * @param key  密钥字符串（经过base64编码）
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	public static String getKeyString(Key key) throws Exception {
		byte[] keyBytes = key.getEncoded();
		String str = (new BASE64Encoder()).encode(keyBytes);
		return str;
	}
}
