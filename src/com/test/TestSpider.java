package com.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class TestSpider {

	public static void main(String[] args) {
		
		System.getProperties().setProperty("http.proxyHost", "web-proxy.cn.hpicorp.net");  //autocache.hpicorp.net  web-proxy.cn.hpicorp.net
		System.getProperties().setProperty("http.proxyPort", "8080");
//		System.out.print(new TestSpider().getHtml("http://www.tianyancha.com/search"));
		Document doc;
		Map<String, String> cookies = new HashMap<String,String>();
		cookies.put("aliyungf_tc", "AQAAAPdpJSyNFwYABDxQcCeXxaJXP8ZJ");
		cookies.put("TYCID", "951315ed4e3d471ebed0d4d79cbf2ecc");
		cookies.put("tnet", "112.80.60.4");
		cookies.put("Hm_lvt_e92c8d65d92d534b0fc290df538b4758", "1481869964,1482117854");
		cookies.put("Hm_lpvt_e92c8d65d92d534b0fc290df538b4758", "1482127842");
		cookies.put("_pk_id.1.e431", "74f15511460cc55a.1481869964.4.1482127843.1482125644.");
		cookies.put("_pk_ses.1.e431", "*");
		cookies.put("token", "6273a2bb21b544fc994b9bfeed6023d7");
		cookies.put("_utm", "7-d832894d-d-8khdnrfkdkvh4n278d3");
		
//		String url = "https://www.sgs.gov.cn/notice/home";
		String url = "http://www.tianyancha.com/search?checkFrom=searchBox";
		
        try {
        	
//        	HttpClient client = new SSLClient();
//        	HttpGet post = new HttpGet(url);
//        	HttpResponse response = client.execute(post);
//        	String body = EntityUtils.toString(response.getEntity(),"utf-8");
//        	doc = Jsoup.parse(body);
        	
            doc = Jsoup.connect(url).cookies(cookies)
            		.userAgent(""
            				+ "Mozilla/5.0 (Windows NT 6.1; WOW64) "
            				+ "AppleWebKit/537.36 (KHTML, like Gecko) "
            				+ "Chrome/44.0.2403.157 "
            				+ "Safari/537.36")
            		.get();
            System.out.println(doc);
//            System.out.println("doc.text()\n"+doc.text());
            System.out.println("doc.title()\n"+doc.title());
//            System.out.println("doc --- "+doc.toString());
//            
//            Elements ListDiv = doc.getElementsByAttributeValue("","inClick?'visited':''");
            Elements list = doc.select("img[class=download_global]");
            
//            Elements list = doc.getElementsByTag("img");
            System.out.println("list ----" +list);
//            Elements titles = doc.select("title");
//            String title = titles.get(0).text().trim();
//            System.out.println("title --"+title);
//            for (Element element :list) {
//                Elements links = element.getElementsByTag("span");
                for (Element link : list) {
                    String linkHref = link.attr("src");
                    String linkText = link.text().trim();
                    System.out.println(linkHref);
                    System.out.println(linkText);
                }
//            }
            System.out.print("success .......");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	} 
public static SSLContext IgnoreVerifySSL() throws Exception{
		
		SSLContext sc = SSLContext.getInstance("SSL");
	    // 设置屏蔽SSL请求告警
		X509TrustManager trustManager = new X509TrustManager() {
			    @Override
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
			    @Override
	            public void checkClientTrusted(
	                java.security.cert.X509Certificate[] certs, String authType) {
	            }
			    @Override
	            public void checkServerTrusted(
	                java.security.cert.X509Certificate[] certs, String authType) {
	            }
	        };	
	 
	    sc.init(null, new TrustManager[]{trustManager}, null);
	    
	    return sc;
	}
	
	/**
	 * 模拟请求
	 * 
	 * @param url		资源地址
	 * @param map	参数列表
	 * @param encoding	编码
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String send(String url, Map<String,String> map,String encoding) throws Exception {
		String body = "";
		//采用绕过验证的方式处理https请求
		SSLContext sslcontext = IgnoreVerifySSL();
		
        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("http", PlainConnectionSocketFactory.INSTANCE)
            .register("https", new SSLConnectionSocketFactory(sslcontext))
            .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);

        //创建自定义的httpclient对象
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
//		CloseableHttpClient client = HttpClients.createDefault();
		
		//创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);
		
		//装填参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if(map!=null){
			for (Entry<String, String> entry : map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		//设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

		System.out.println("请求地址："+url);
		System.out.println("请求参数："+nvps.toString());
		
		//设置header信息
		//指定报文头【Content-type】、【User-Agent】
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36");
		//执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);
		//获取结果实体
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			//按指定编码转换结果实体为String类型
			body = EntityUtils.toString(entity, encoding);
		}
		EntityUtils.consume(entity);
		//释放链接
		response.close();
        return body;
	}

	public static String getHtml(String urlString) {
		try {
			
			StringBuffer html = new StringBuffer();
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String temp;
			while ((temp = br.readLine()) != null) {
			   html.append(temp).append("\n");
			}
			br.close();
			isr.close();
			return html.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
