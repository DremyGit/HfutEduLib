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
    
    public static void setAvailableHostname() throws Exception {
        
        if (SiteConst.preHostname == null) {
            
            boolean flag = false;
            for (String hostname : SiteConst.hostnameList) {
                try {
                    if (Spider.getGETCode(hostname) == 200) {
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
    
    
    public static HttpResponse fetchSitePage(SitePage sitePage) throws Exception {
        
        
        try {
            switch (sitePage.getMethod()) {
            case "GET":
                return Spider.getGETResponse(SiteConst.preHostname + sitePage.getUrl());
            
            case "POST":
                return Spider.getPOSTResponse(SiteConst.preHostname + sitePage.getUrl());

            default:
                return null;
            }
        } catch (Exception e) {
            return null;
        }


    }
    
    public static HttpResponse fetchSitePage(SitePage sitePage, Map<String, Object> requestParams) throws Exception {
        
        
        try {
            switch (sitePage.getMethod()) {
            case "GET":
                return Spider.getGETResponse(SiteConst.preHostname + sitePage.getUrl() + buildGetParams(requestParams));
            
            case "POST":
                return Spider.getPOSTResponse(SiteConst.preHostname + sitePage.getUrl(), buildPostParams(requestParams));

            default:
                return null;
            }
        } catch (Exception e) {
            return null;
        }


    }
    
    public static String buildGetParams(Map<String, Object> requestParams) {
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
    
    public static List<NameValuePair> buildPostParams(Map<String, Object> requestParams) {

       Set<String> keySet = requestParams.keySet();
       List<NameValuePair> postParamList = new ArrayList<>();
       for (String key : keySet) {
           NameValuePair postParam = new BasicNameValuePair(key, requestParams.get(key).toString());
           postParamList.add(postParam);
       }
       return postParamList;
    }
    
    
}
