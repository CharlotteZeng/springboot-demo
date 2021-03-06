package com.springbootdemo.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CNNumberFormatUtils {
	private static final Pattern AMOUNT_PATTERN = Pattern
			.compile("^(0|[1-9]\\d{0,11})\\.(\\d\\d)$"); // 不考虑分隔符的正确性
	private static final Pattern AMOUNT_PATTERN_DEL_AFTER_4 = Pattern
			.compile("^(0|[1-9]\\d{0,11})\\.(\\d\\d\\d\\d)$"); // 不考虑分隔符的正确性
	private static final char[] RMB_NUMS = "零壹贰叁肆伍陆柒捌玖".toCharArray();
	private static final String[] UNITS = { "元", "角", "分", "整" };
	private static final String[] U1 = { "", "拾", "佰", "仟" };
	private static final String[] U2 = { "", "万", "亿" };

	/**
	 * 将金额（整数部分等于或少于12位，小数部分2位）转换为中文大写形式.
	 * 
	 * @param amount
	 *            金额数字
	 * @return 中文大写
	 * @throws IllegalArgumentException
	 */
	public static String convert(String amount) throws IllegalArgumentException {
		// 去掉分隔符
		amount = amount.replace(",", "");

		// 验证金额正确性
		if (amount.equals("0.00")) {
			throw new IllegalArgumentException("金额不能为零.");
		}
		Matcher matcher = AMOUNT_PATTERN.matcher(amount);
		if (!matcher.find()) {
			throw new IllegalArgumentException("输入金额有误.");
		}

		String integer = matcher.group(1); // 整数部分
		String fraction = matcher.group(2); // 小数部分

		String result = "";
		if (!integer.equals("0")) {
			result += integer2rmb(integer) + UNITS[0]; // 整数部分
		}
		if (fraction.equals("00")) {
			result += UNITS[3]; // 添加[整]
		} else if (fraction.startsWith("0") && integer.equals("0")) {
			result += fraction2rmb(fraction).substring(1); // 去掉分前面的[零]
		} else {
			result += fraction2rmb(fraction); // 小数部分
		}

		return result;
	}
	/**
	 * 将金额（整数部分等于或少于12位，小数部分2位 支持4位小数）转换为中文大写形式.
	 * 创新产品赎回功能使用 支持份额4位小数 转大写数字
	 * @param amount
	 *            金额数字
	 * @return 中文大写
	 * @throws IllegalArgumentException
	 */
	public static String convertDelFour(String amount) throws IllegalArgumentException {
		// 去掉分隔符
		amount = amount.replace(",", "");

		// 验证金额正确性
		if (amount.equals("0.00")) {
			throw new IllegalArgumentException("金额不能为零.");
		}
		if (amount.equals("0.0000")) {
			throw new IllegalArgumentException("金额不能为零.");
		}
		int indexOf = amount.indexOf(".");
		String substring = amount.substring(indexOf==-1?0:indexOf, amount.length());
		Matcher matcher = substring.length()>3?AMOUNT_PATTERN_DEL_AFTER_4.matcher(amount):AMOUNT_PATTERN.matcher(amount);
		if (!matcher.find()) {
			throw new IllegalArgumentException("输入金额有误.");
		}

		String integer = matcher.group(1); // 整数部分
		String fraction = matcher.group(2); // 小数部分

		String result = "";
		if (!integer.equals("0")&&substring.length()<=3) {//substring 长度在3或者3以内 是金额 大于3为份额 当份额时不加金额单位元
			result += integer2rmb(integer) + UNITS[0]; // 整数部分
		}
		if (fraction.equals("00")) {
			result += UNITS[3]; // 添加[整]
		} else if (fraction.startsWith("0") && integer.equals("0")) {
			result += fraction2rmb(fraction).substring(1); // 去掉分前面的[零]
		} else {
			result += fraction2rmb(fraction); // 小数部分
		}

		return result;
	}
	// 将金额小数部分转换为中文大写
	private static String fraction2rmb(String fraction) {
		char jiao = fraction.charAt(0); // 角
		char fen = fraction.charAt(1); // 分
		return (RMB_NUMS[jiao - '0'] + (jiao > '0' ? UNITS[1] : ""))
				+ (fen > '0' ? RMB_NUMS[fen - '0'] + UNITS[2] : "");
	}

	// 将金额整数部分转换为中文大写
	private static String integer2rmb(String integer) {
		StringBuilder buffer = new StringBuilder();
		// 从个位数开始转换
		int i, j;
		for (i = integer.length() - 1, j = 0; i >= 0; i--, j++) {
			char n = integer.charAt(i);
			if (n == '0') {
				// 当n是0且n的右边一位不是0时，插入[零]
				if (i < integer.length() - 1 && integer.charAt(i + 1) != '0') {
					buffer.append(RMB_NUMS[0]);
				}
				// 插入[万]或者[亿]
				if (j % 4 == 0) {
					if (i > 0 && integer.charAt(i - 1) != '0' || i > 1
							&& integer.charAt(i - 2) != '0' || i > 2
							&& integer.charAt(i - 3) != '0') {
						buffer.append(U2[j / 4]);
					}
				}
			} else {
				if (j % 4 == 0) {
					buffer.append(U2[j / 4]); // 插入[万]或者[亿]
				}
				buffer.append(U1[j % 4]); // 插入[拾]、[佰]或[仟]
				buffer.append(RMB_NUMS[n - '0']); // 插入数字
			}
		}
		return buffer.reverse().toString();
	}
	
	/**
	 * 将"100万" 转换成 1000000.0  ，或者将"100元"转换成 100.0
	 * @param s
	 * @return
	 */
	public static BigDecimal minAmountHelp(String s){
		
		String amountString ="";
		String pattern = "-?[1-9]\\d*";
		Pattern compile = Pattern.compile(pattern);
		Matcher m = compile.matcher(s);
		if (m.find()) {
			
			amountString =m.group(0);
		}else{
			//没有找到匹配项
		}
		BigDecimal d = new BigDecimal(amountString);
		if(s.contains("千万")){
			d = new BigDecimal(amountString) .multiply(new BigDecimal(10000000))  ;
		}else if(s.contains("万")){
			d = new BigDecimal(amountString).multiply(new BigDecimal(10000)) ;
		}
		return d;
	}
	public static void main(String[] args) {
		String s ="9千万美元";
		/*String amountString ="";
		String pattern = "-?[1-9]\\d*";
		Pattern compile = Pattern.compile(pattern);
		Matcher m = compile.matcher(s);
		if (m.find()) {
			
			amountString =m.group(0);
		}else{
			//没有找到匹配项
		}
		BigDecimal d = new BigDecimal(amountString);
		if(s.contains("千万")){
			d = new BigDecimal(amountString) .multiply(new BigDecimal(10000000))  ;
		}else if(s.contains("万")){
			d = new BigDecimal(amountString).multiply(new BigDecimal(10000)) ;
		}
		System.out.println(d);*/
		System.out.println(minAmountHelp(s));
	}
}
