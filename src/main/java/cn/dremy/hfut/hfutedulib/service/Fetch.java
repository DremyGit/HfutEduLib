package cn.dremy.hfut.hfutedulib.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import cn.dremy.hfut.hfutedulib.common.SiteConst;
import cn.dremy.hfut.hfutedulib.model.SitePage;

public class Fetch {
    
    private Spider spider;
    
    public Fetch() throws Exception {
        
        this.spider = new Spider();
        
        if (SiteConst.preHostname == null) {
            
            boolean flag = false;
            for (String hostname : SiteConst.hostnameList) {
                try {
                    if (spider.getGETCode(hostname) == 200) {
                        flag = true;
                        SiteConst.preHostname = hostname;
                        break;
                    }
                    
                } catch (IOException e) {
                    continue;
                }
            }
            
            if (flag == false) {
                throw new Exception("Network or hostname list is unavailable");
            }
        }
    }
    
    
    public HttpResponse fetchSitePage(SitePage sitePage) {
        
        
        try {
            switch (sitePage.getMethod()) {
            case "GET":
                return spider.getGETResponse(SiteConst.preHostname + sitePage.getUrl());
            
            case "POST":
                return spider.getPOSTResponse(SiteConst.preHostname + sitePage.getUrl());

            default:
                return null;
            }
        } catch (Exception e) {
            return null;
        }


    }
    
    public HttpResponse fetchSitePage(SitePage sitePage, Map<String, Object> requestParams) {
        
        
        try {
            switch (sitePage.getMethod()) {
            case "GET":
                return spider.getGETResponse(SiteConst.preHostname + sitePage.getUrl() + buildGetParams(requestParams));
            
            case "POST":
                return spider.getPOSTResponse(SiteConst.preHostname + sitePage.getUrl(), buildPostParams(requestParams));

            default:
                return null;
            }
        } catch (Exception e) {
            return null;
        }


    }
    
    public String buildGetParams(Map<String, Object> requestParams) {
        Set<String> keySet = requestParams.keySet();
        if (keySet.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        for (String key : keySet) {
            sb.append(key + "=");
            sb.append(requestParams.get(key).toString());
            sb.append('&');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    public List<NameValuePair> buildPostParams(Map<String, Object> requestParams) {

       Set<String> keySet = requestParams.keySet();
       List<NameValuePair> postParamList = new ArrayList<>();
       for (String key : keySet) {
           NameValuePair postParam = new BasicNameValuePair(key, requestParams.get(key).toString());
           postParamList.add(postParam);
       }
       return postParamList;
    }
    
    
}
