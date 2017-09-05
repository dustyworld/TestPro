package com.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class PengDiao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		System.setProperty("java.net.useSystemProxies", "true");
		System.setProperty("http.proxyHost", "124.88.67.19");
		System.setProperty("http.proxyPort", "80");
		
		String url = "https://www.sgs.gov.cn/notice/home";
		try{
			HttpClient client = new SSLClient();
        	HttpGet get = new HttpGet(url);
        	HttpResponse response = client.execute(get);
        	String body = EntityUtils.toString(response.getEntity(),"utf-8");
			
        	System.out.print(body);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
//	public static void getProxy() {
//
//		BrowserProxyInfo b = new BrowserProxyInfo();
//
//		b.setType(ProxyType.AUTO);
//
//		b.setAutoConfigURL("http://autocache.hpicorp.net/");
//		//b.setAutoConfigURL("C:\\Users\\zhous\\Desktop\\proxy.pac");
//		DummyAutoProxyHandler handler = new DummyAutoProxyHandler();
//		try {
//
//			handler.init(b);
//
//		} catch (ProxyConfigException e1) {
//
//			// TODO Auto-generated catch block
//
//			e1.printStackTrace();
//
//		}
//
//		try {
//
//			// need add socket
//
//			URL url = new URL("https://www.sgs.gov.cn/notice/home");
//
//			ProxyInfo[] ps = handler.getProxyInfo(url);
//
//			for (ProxyInfo p : ps) {
//
//				String[] info = p.toString().split(":");
//
//				System.out.println("proxyHost :" + info[0]);
//				System.out.println("proxyPort :" + Integer.parseInt(info[1]));
//
//			}
//
//		} catch (MalformedURLException e) {
//
//			// TODO Auto-generated catch block
//
//			e.printStackTrace();
//
//		}
//	} 

}
