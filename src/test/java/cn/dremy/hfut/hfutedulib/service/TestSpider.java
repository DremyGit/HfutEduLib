package cn.dremy.hfut.hfutedulib.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import cn.dremy.hfut.hfutedulib.common.SiteConst;
import cn.dremy.hfut.hfutedulib.service.Spider;

public class TestSpider {
    
    
    @Test
    public void test() throws ClientProtocolException, IOException {
        
        int code = Spider.getGETCode(SiteConst.hostnameList.get(0));
        assertEquals(200, code);
    }

}
