package cn.dremy.hfut.hfutedulib.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.dremy.hfut.hfutedulib.common.SiteConst;
import cn.dremy.hfut.hfutedulib.service.Spider;

public class TestSpider {
    
    @BeforeClass
    public static void init() throws Exception {
        Fetch.setAvailableHostname();
    }
    
    
    @Test
    public void test() throws ClientProtocolException, IOException {
        
        int code = Spider.getGETCode(SiteConst.preHostname);
        assertEquals(200, code);
    }

}
