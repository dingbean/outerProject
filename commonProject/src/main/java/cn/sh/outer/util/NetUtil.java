package cn.sh.outer.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
/**
 * 无用的类
 * @author Administrator
 *
 */
public class NetUtil {
	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String PUT = "PUT";
	public static final String DELETE = "DELETE";
	public static Logger log = Logger.getLogger(NetUtil.class.getName());

	/**
	 * 发送Http请求
	 * 
	 * @param reqUrl
	 * @param reqMeth
	 *            POST或GET
	 * @param reqParam
	 * @return
	 */
	public static String send(String reqUrl, String reqMeth, String reqParam) {
		return send(reqUrl, reqMeth, reqParam, "text/html");

	}

	public static String send(String reqUrl, String reqMeth, String reqParam,
			String contentType) {
		DataOutputStream dataOutStream = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		HttpURLConnection connection = null;
		log.info("Request[" + reqUrl + "] params[" + reqParam + "]");
		try {

			URL url = new URL(reqUrl);
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(hv);

			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod(reqMeth);
			connection.setRequestProperty("Content-type", contentType);
			connection.setRequestProperty("Charset", "UTF-8");
			connection.connect();

			if (!reqMeth.equals("GET") && StringUtils.isNotEmpty(reqParam)) {

				outputStream = connection.getOutputStream();
				dataOutStream = new DataOutputStream(outputStream);
				dataOutStream.write(reqParam.getBytes("utf-8"));
				dataOutStream.flush();
				outputStream.flush();
			}
			inputStream = connection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = bufferedReader.readLine()) != null) {
				sb.append(lines);
			}

			log.info("Response[" + reqUrl + "] result[" + sb.toString() + "]");

			return sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("", e);
			return "";
		} finally {
			try {
				if (dataOutStream != null)
					dataOutStream.close();
				if (outputStream != null)
					outputStream.close();
				if (bufferedReader != null)
					bufferedReader.close();
				if (inputStreamReader != null)
					inputStreamReader.close();
				if (inputStream != null)
					inputStream.close();
				if (connection != null)
					connection.disconnect();
			} catch (Exception e) {
				log.error("", e);
				e.printStackTrace();
			}

		}
	}
	
	public static int codeSend(String reqUrl, String reqMeth, String reqParam,
			String contentType) {
		DataOutputStream dataOutStream = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		HttpURLConnection connection = null;
		log.info("Request[" + reqUrl + "] params[" + reqParam + "]");
		try {

			URL url = new URL(reqUrl);
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(hv);

			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod(reqMeth);
			if(StringUtils.isNotEmpty(contentType)){
				connection.setRequestProperty("Content-type", contentType);
			}
			connection.setRequestProperty("Charset", "UTF-8");
			connection.connect();

			if (!reqMeth.equals("GET") && StringUtils.isNotEmpty(reqParam)) {

				outputStream = connection.getOutputStream();
				dataOutStream = new DataOutputStream(outputStream);
				dataOutStream.write(reqParam.getBytes("utf-8"));
				dataOutStream.flush();
				outputStream.flush();
			}

			return connection.getResponseCode();

		} catch (Exception e) {

			e.printStackTrace();
			log.error("", e);
			return 0;
		} finally {
			try {
				if (dataOutStream != null)
					dataOutStream.close();
				if (outputStream != null)
					outputStream.close();
				if (bufferedReader != null)
					bufferedReader.close();
				if (inputStreamReader != null)
					inputStreamReader.close();
				if (inputStream != null)
					inputStream.close();
				if (connection != null)
					connection.disconnect();
			} catch (Exception e) {
				log.error("", e);
				e.printStackTrace();
			}

		}
	}

	static HostnameVerifier hv = new HostnameVerifier() {
		public boolean verify(String urlHostName, SSLSession session) {
			return true;
		}
	};

	private static void trustAllHttpsCertificates() throws Exception {
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
				.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sc
				.getSocketFactory());
	}

	static class miTM implements javax.net.ssl.TrustManager,
			javax.net.ssl.X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(
				java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}


	public static final String CHARSET_UTF_8 = "UTF-8";
	
	public static final String CHARSET_GB2312 = "GB2312";

	public static String post(String urlStr, Map<String, String> requestParams) throws IOException{
		URLConnection connection = new URL(urlStr).openConnection();
		
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		setRequestParams(connection.getOutputStream(), requestParams);
		return reader(connection.getInputStream());
	}
	
	private static void setRequestParams(OutputStream outputStream,
			Map<String, String> requestParams) {
		if(requestParams == null || requestParams.isEmpty())
			return;
		
		PrintWriter out = new PrintWriter(outputStream, true);
		StringBuffer buffer = new StringBuffer();
		for (Entry<String, String> entry : requestParams.entrySet()) {
			buffer.append(entry.getKey());
			buffer.append("=");
			buffer.append(entry.getValue());
			buffer.append("&");
		}
		buffer.deleteCharAt(buffer.length() - 1);
		out.write(buffer.toString());
		out.close();
	}
	
	private static String reader(InputStream input) {
		Scanner scanner = new Scanner(input, CHARSET_GB2312);
		StringBuffer buffer = new StringBuffer();
		while (scanner.hasNextLine()) {
			buffer.append(scanner.nextLine() + "\n");
		}
		scanner.close();
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		String url = "https://172.31.21.9:8442/MobileServiceInvoker/message/pmi.do";
		String param = "[{}]";
		System.out.println(NetUtil.send(url, POST, param));
	}
}
