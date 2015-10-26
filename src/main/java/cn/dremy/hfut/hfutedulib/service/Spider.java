package cn.dremy.hfut.hfutedulib.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import cn.dremy.hfut.hfutedulib.common.SiteConst;

@SuppressWarnings("deprecation")
public class Spider {


	private HttpClient client  = HttpClients.createDefault();
	
	/**
	 * 执行GET请求，返回Response
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResponse getGETResponse(String url) throws ClientProtocolException, IOException {
		
//		System.out.println("Executing GET request " + url);
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
//		System.out.println("  State:" + response.getStatusLine().getStatusCode());
		return response;
	}
	
	/**
     * 执行POST请求，返回Response
     * @param url
     * @param formparams
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpResponse getPOSTResponse(String url) throws ClientProtocolException, IOException {
        
//      System.out.println("Executing POST request " + url);
        HttpPost request = new HttpPost(url);
        request.setEntity(new UrlEncodedFormEntity(null, SiteConst.encode));
        HttpResponse response = client.execute(request);
//      System.out.println("  State:" + response.getStatusLine().getStatusCode());
        return response;
    }
	
	/**
	 * 执行带参数的POST请求，返回Response
	 * @param url
	 * @param formparams
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResponse getPOSTResponse(String url, List<NameValuePair> formparams) throws ClientProtocolException, IOException {
		
//		System.out.println("Executing POST request " + url);
		HttpPost request = new HttpPost(url);
		request.setEntity(new UrlEncodedFormEntity(formparams, SiteConst.encode));
		HttpResponse response = client.execute(request);
//		System.out.println("  State:" + response.getStatusLine().getStatusCode());
		return response;
	}
	
	/**
	 * 执行GET请求，返回Content
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String getGETContent(String url) throws ClientProtocolException, IOException{
		
		HttpEntity responseEntity = getGETResponse(url).getEntity();
		String content = EntityUtils.toString(responseEntity, SiteConst.encode);
//		System.out.println(content);
		return content;
	}

	/**
	 * 执行GET请求，返回状态码
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public int getGETCode(String url) throws ClientProtocolException, IOException{
		
		HttpResponse res = getGETResponse(url);
		EntityUtils.toString(res.getEntity(), SiteConst.encode);
		return res.getStatusLine().getStatusCode();
	}

	/**
	 * 执行POST请求，返回Content
	 * @param url
	 * @param formparams
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String getPOSTContent(String url,List<NameValuePair> formparams) throws ClientProtocolException, IOException{
		
		
		HttpEntity responseEntity = getPOSTResponse(url, formparams).getEntity();
		String content = EntityUtils.toString(responseEntity, SiteConst.encode);
//        System.out.println(content);
        return content;
	}

	/**
	 * 执行POST请求，返回状态码
	 * @param url
	 * @param formparams
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public int getPOSTCode(String url,List<NameValuePair> formparams) throws ClientProtocolException, IOException{
		
	    HttpResponse res = getPOSTResponse(url, formparams);
	    EntityUtils.toString(res.getEntity(), SiteConst.encode);
		return res.getStatusLine().getStatusCode();
	}
	
	/**
	 * 返回Client的Cookie
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public List<Cookie> getClientCookies(String url) throws ClientProtocolException, IOException {
		CookieStore cookieStore = new BasicCookieStore();   
        HttpContext localContext = new BasicHttpContext();   
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);   
        HttpGet httpget = new HttpGet(url);    
        client.execute(httpget, localContext);
        List<Cookie> cookies = cookieStore.getCookies();   
        return cookies;
	}

	
	public String getGETLocation(String url, String key) throws ClientProtocolException, IOException {
		Header[] headers = getGETResponse(url).getAllHeaders();
		for(Header header : headers) {
			if(header.getName().equals(key)) {
				return header.getValue();
			}
		}
		return null;
	}



}
