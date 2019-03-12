/**
 * 
 */
package com.springbootdemo.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 脱敏工具类
 * @author 201410135010
 *
 */
public class DPUtils {
	
	/**
	 * 获得脱敏手机号（显示前3后4）
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public static String getMobile(String mobile) throws Exception{
		String result = "";
		if(mobile!=null && !mobile.equals("")){
			int startIndex=3;
			int endIndex =mobile.length()-4;
			int dlength = 0;
			dlength = mobile.substring(startIndex, endIndex).length();
			for(int i=0;i<dlength;i++){
				result = result+"*";
			}
			result = mobile.substring(0, startIndex)+result+mobile.substring(endIndex, mobile.length());
		}
		return result;
	}
	/**
	 * 获得脱敏的用户账号(手机号、邮箱)
	 * @param uName
	 * @return
	 * @throws Exception
	 */
	public static String getUname(String uName) throws Exception{
		String result = "";
		if(uName != null){
			if(uName.contains("@")){
				result = DPUtils.getEmail(uName);
			}else {
				result = DPUtils.getMobile(uName);
			}
		}
		return result;
	}
	/**
	 * 获得脱敏邮箱（显示第一位后加4位*）
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public static String getEmail(String email) throws Exception{
		String result = "";
		if(email!=null && !email.equals("")){
			int startIndex = email.indexOf('@');
			if(startIndex>0){
				result = email.charAt(0)+"****"+email.substring(startIndex);
			}
		}
		return result;
	}
	
	/**
	 * 获得脱敏身份证号（显示前3后4）
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public static String getIdentityNo(String identityNo) throws Exception{
		String result = "";
		if(identityNo!=null && !identityNo.equals("")){
			int startIndex = 3;
			int endIndex = identityNo.length()-4;
			int dlength = 0;
			String head = "";
			String tail = "";
			if(identityNo.length()>7){
				startIndex = 3;
				endIndex = identityNo.length()-4;
				dlength = identityNo.substring(startIndex, endIndex).length();
				head = identityNo.substring(0, startIndex);
				tail = identityNo.substring(endIndex);
			}else if(identityNo.length()>=5 && identityNo.length()<=7){
				startIndex = 2;
				endIndex = identityNo.length()-3;
				dlength = identityNo.substring(startIndex, endIndex).length();
				head = identityNo.substring(0, startIndex);
				tail = identityNo.substring(endIndex);
			}else if(identityNo.length()<5){
				startIndex = 1;
				endIndex = 0;
				dlength = 9;
				head = identityNo.substring(0, startIndex);
				tail = "";
			}
			for(int i=0;i<dlength;i++){
				result = result+"*";
			}
			result = head+result+tail;
		}
		return result;
	}
	
	/**
	 * 获取脱敏银行卡号
	 * @param bankCardNo
	 * @return
	 * @throws Exception
	 */
	public static String getBankCardNo(String bankCardNo) throws Exception{
		if(StringUtils.isEmpty(bankCardNo)) {
            return "";
        }
		StringBuffer bankNumberBuf =new StringBuffer(bankCardNo);
		bankNumberBuf = bankNumberBuf.replace(4,4+8, "********");
		bankNumberBuf = bankNumberBuf.insert(4, " ");
		bankNumberBuf = bankNumberBuf.insert(4+5, " ");
		bankNumberBuf = bankNumberBuf.insert(4+5+5, " ");
		return bankNumberBuf.toString();
	}
	/**
	 * 获取脱敏银行卡号 前三后四
	 * @param bankCardNo
	 * @return
	 * @throws Exception
	 */
	public static String getBankaccount(String bankCardNo) throws Exception{
		if(StringUtils.isEmpty(bankCardNo)) {
            return "";
        }
		return bankCardNo.substring(0,3)+" ********* "+bankCardNo.substring(bankCardNo.length()-4);
		
	}
	
	/**
	 * 获取脱敏后FSO用户名
	 * @param uName
	 * @return
	 * @throws Exception
	 */
	public static String getFsoUserName(String uName) throws Exception{
		try{
			if(uName!=null && !uName.equals("")){
				if(isNumericalValue(uName)){
					return DPUtils.getMobile(uName);
				}else{
					return DPUtils.getEmail(uName);
				}
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
	}
	/**
	 * 验证是否为数值
	 *
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isNumericalValue(String str) {
		String regex = "^[\\+\\-]?[0-9]+(.[0-9]*)?$";
		return match(regex, str);
	}
	/**
	 * @param regex
	 * 正则表达式字符串
	 * @param str
	 * 要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	public static void main(String[] args) throws Exception {
		System.out.println(DPUtils.getIdentityNo("110104199103122017"));
		System.out.println(DPUtils.getIdentityNo("1234567"));
		System.out.println(DPUtils.getIdentityNo("1234"));
	}
}
