package com.gl365.app.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * 类说明：<br>
 * 
 * 
 * <p>
 * 详细描述：<br>
 * 
 * 
 * </p>
 * 
 * @author 
 * 
 * CreateDate: 2013-7-23
 */
public final class HttpUtil {
	private static final String CHARSET = "UTF-8";

	/**
	 * 
	 * 方法说明：<br>
	 * 
	 * @param url 请求url
	 * @param content post参数 key1=val1&key2=val2&key3=val3
	 * @return
	 * @throws Exception
	 */
	public static String sendByPost(String url, String content) throws Exception {
		StringBuffer result = new StringBuffer();
		URL u = new URL(url);
		HttpURLConnection con = (HttpURLConnection) u.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setAllowUserInteraction(false);
		con.setUseCaches(false);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		con.setConnectTimeout(10000);
		con.setReadTimeout(20000);
		OutputStream out = null;
		BufferedReader reader = null;
		String line = null;
		try {
			out = con.getOutputStream();
			out.write(content.getBytes(CHARSET));
			out.flush();
			reader = new BufferedReader(new InputStreamReader(con.getInputStream(), CHARSET));
			while (null != (line = reader.readLine())) {
				result.append(line);
			}
		}
		finally {
			if (out != null) {
				out.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
		return result.toString();
	}

	/**
	 * 
	 * 方法说明：<br>
	 * 
	 * @param url 请求url
	 * @param content get参数 key1=val1&key2=val2&key3=val3
	 * @return
	 * @throws IOException
	 */
	public static String sendByGet(String url, String content) throws IOException {
		URL u = new URL(url + "?" + content);
		HttpURLConnection con = (HttpURLConnection) u.openConnection();
		con.setUseCaches(false);
		StringBuffer result = new StringBuffer();
		BufferedReader reader = null;
		String line = null;
		reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "GBK"));
		while (null != (line = reader.readLine())) {
			result.append(line);
		}
		if (reader != null) {
			reader.close();
		}
		return result.toString();
	}
}
