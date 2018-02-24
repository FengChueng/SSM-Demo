package com.zyl.ssm.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils {
	private static final Logger LOGGER = Logger.getLogger(OkhttpUtils.class);
	private final static OkHttpClient M_OK_HTTP_CLIENT = new OkHttpClient().newBuilder()
			.connectTimeout(5, TimeUnit.MINUTES)// 连接超时时间5min
			.readTimeout(5, TimeUnit.MINUTES)// 读取响应结果超时时间5min
			.writeTimeout(5, TimeUnit.MINUTES)// 写超时时间5min
			.build();

	public final static String MONITOR_BASE_URL = System.getenv("netty_node") + "monitor/";
	// public final static String MONITOR_BASE_URL =
	// "http://127.0.0.1:9090/monitor/";

	/**
	 * 不开启异步线程
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Response execute(Request request) throws IOException {
		return M_OK_HTTP_CLIENT.newCall(request).execute();
	}

	public static Response execute(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		return M_OK_HTTP_CLIENT.newCall(request).execute();
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {
			int i = 0;
			@Override
			public void run() {
				while(true){
					
					if(i%5000 == 0){
						getPrice();
					}
					
					try {
						Thread.sleep(60);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
				}
			}
		}).start();
	}

	private static void getPrice() {
		String BASE_URL = "https://api.huobi.pro/market";
		StringBuilder url = new StringBuilder();
		url.append(BASE_URL);
		// url.append("monitor/");
		url.append("/detail/merged?symbol=");
		url.append("eosusdt");
		Request request = new Request
				.Builder()
				.url(url.toString())
				.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.build();
		Response response = null;
		try {
			response = OkhttpUtils.execute(request);
			if (response.isSuccessful()) {
				String responseBody = response.body().string();
				System.out.println(responseBody);
				JsonElement parse = new JsonParser().parse(responseBody);
				JsonElement jsonElement = parse.getAsJsonObject().get("status");
				if("ok".equals(jsonElement.getAsString())){
					MergeData fromJson = new Gson().fromJson(responseBody, MergeData.class);
					Tick tick = fromJson.getTick();
					BigDecimal close = tick.getClose();
					BigDecimal open = tick.getOpen();
					BigDecimal high = tick.getHigh();
					BigDecimal low = tick.getLow();
					
//					int compareTo = close.compareTo(open.multiply(new BigDecimal(1.1)));
					int compareTo = close.compareTo((new BigDecimal(14)));
					if(compareTo >= 0){
						String body = "[开:" + open.doubleValue() + ",高:" + high.doubleValue() + ",低:"
								+ low.doubleValue() + ",止:" + close.doubleValue() + "]";
						SMSUtils.sendCodeSMS(body, new String[]{"18380586504"});
						return;
					}
					
					int compareTo2 = close.compareTo(new BigDecimal("8.5"));
					if(compareTo2 <=0){
						//发送短信
						String body = "[开:" + open.doubleValue() + ",高:" + high.doubleValue() + ",低:"
								+ low.doubleValue() + ",止:" + close.doubleValue() + "]";
						SMSUtils.sendCodeSMS(body, new String[]{"18380586504"});
						return;
					}
					System.out.println(close.toString());
				}else{
					
				}
			}else{
				System.out.println(response.code());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	/**
	 * 开启异步线程访问，访问结果自行处理
	 * 
	 * @param url
	 * @param responseCallback
	 */
	public static void equene(String url, Callback responseCallback) {
		Request request = new Request.Builder().url(url).build();
		M_OK_HTTP_CLIENT.newCall(request).enqueue(responseCallback);
	}

	/**
	 * 开启异步线程访问，访问结果自行处理
	 * 
	 * @param request
	 * @param responseCallback
	 */
	public static void enqueue(Request request, Callback responseCallback) {
		M_OK_HTTP_CLIENT.newCall(request).enqueue(responseCallback);
	}

	/**
	 * 为HttpGet请求拼接一个参数
	 * 
	 * @author wangsong 2015-10-9
	 * @param url
	 * @param name
	 * @param value
	 */
	public static String jointURL(String url, String name, String value) {
		return url + "?" + name + "=" + value;
	}

	/**
	 * 为HttpGet请求拼接多个参数
	 * 
	 * @author wangsong 2015-10-9
	 * @param url
	 * @param name
	 * @param value
	 */
	public static String jointURL(String url, Map<String, String> values) {
		StringBuffer result = new StringBuffer();
		result.append(url).append("?");
		Set<String> keys = values.keySet();
		for (String key : keys) {
			result.append(key).append("=").append(values.get(key)).append("&");
		}
		return result.toString().substring(0, result.toString().length() - 1);
	}

}
