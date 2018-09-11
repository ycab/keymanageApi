package com.example.keymanageapi.Util;


import com.example.keymanageapi.Assistant.MyX509TrustManager;
import com.example.keymanageapi.model.AccessToken;
import com.example.keymanageapi.model.JsapiTicket;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

/**
 * 
 * 类名称: WeiXinUtil
 * 类描述:
 * @author yuanjun
 * 创建时间:2017年12月8日下午4:38:42
 */

public class WeiXinUtil {
	/**
	 * 开发者id
	 */
	//private static final String APPID = "wxe14120ff14b7aad0";
	//private static final String APPID = "wx53db088f8ebaf7fd";
	private static final String APPID = "wxde9aeb9d7982fe19";

	/**
	 * 开发者秘钥
	 */
	//private static final String APPSECRET="e79d7bcbddb3b0e82e5df09a48e4df48";
	//private static final String APPSECRET="e6aaed5b483e421e9d060dd7427a05a6";
	private static final String APPSECRET="69c5542b32005b0e7d39f25510f653f5";

	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?"
			+ "grant_type=client_credential&appid=APPID&secret=APPSECRET";


	 /**
	 * 定时从微信服务器上面获取token值
	 */
	// ////测试每5秒执行一次
	//@Scheduled(cron="0/5 * *  * * ? ")   //测试每5秒执行一次
	//@Scheduled(cron="0/7000 * *  * * ? ")   //每7000秒执行一次
	//@Scheduled(cron = "0 0 */2 * * ?")  //俩小时执行一次
//	public void getAccessToken() {
//
//		AccessToken token = new AccessToken();
//		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
//
//		RestTemplate restTemplate = new RestTemplate();
//		String response = restTemplate.getForObject(url, String.class);
//		JSONObject json = JSONObject.fromObject(response);
//		String access_token=json.getString("access_token");
//		Integer expires_in=json.getInt("expires_in");
//		token.setAccess_token(access_token);
//		token.setExpires_in(expires_in);
//
//	}


	/**
	 * 发起https请求并获取结果
	 *
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {

		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = {  new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}


	/**
	 * 获取access_token
	 *
	// * @param appid 凭证
	 //* @param appsecret 密钥
	 * @return
	 **/
	public static AccessToken getAccessToken() {
		//获取公众号access_token的链接

		AccessToken token = null;
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);

		//String requestUrl = access_token.replace("APPID", appid).replace("APPSECRET", appsecret);
		//String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(url, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				token = new AccessToken();
				token.setAccess_token(jsonObject.getString("access_token"));
				token.setExpires_in(jsonObject.getInt("expires_in"));

			} catch (JSONException e) {
				token = null;
				// 获取token失败
				System.out.println("获取token失败 errcode:{} errmsg:{}"+jsonObject.getInt("errcode")+jsonObject.getString("errmsg"));
			}
		}
		return token;
	}



	/**
	 * 获取jsapi_ticket
	 *
	 * @paramappid凭证
	 * @paramappsecret密钥
	 * @return
	 */
	public static JsapiTicket getJsapiTicket(String accessToken) {
		//获取公众号jsapi_ticket的链接
		String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		JsapiTicket jsapiticket = null; //ticket分享值
		if(accessToken != null){
			String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);
			//String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					jsapiticket = new JsapiTicket();
					jsapiticket.setTicket(jsonObject.getString("ticket"));
					jsapiticket.setExpiresIn(jsonObject.getInt("expires_in"));
//					Timestamp d = new Timestamp(System.currentTimeMillis());
//					jsapiticket.setUPDATE_TIME(d.toString());
				} catch (JSONException e) {
					jsapiticket = null;
					// 获取ticket失败
					System.out.println("获取ticket失败 errcode:{} errmsg:{}" + jsonObject.getInt("errcode") + jsonObject.getString("errmsg"));
				}
			}
		}else{
			System.out.println("*****token为空 获取ticket失败******");
		}

		return jsapiticket;
	}



}
