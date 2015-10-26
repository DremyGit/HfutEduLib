package cn.dremy.hfut.hfutedulib.common;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.junit.Test;

import cn.dremy.hfut.hfutedulib.model.SitePage;

public class TestXmlParser {

    @SuppressWarnings("unchecked")
    @Test
    public void testParsePagesConfig() throws DocumentException, IOException {

        Map<String, Object> parseSiteConfig = XmlParser.parseSiteConfig();
        assertNotNull(parseSiteConfig);
        
        List<String> hostnameList = (List<String>) parseSiteConfig.get("hostnameList");
        assertFalse(hostnameList.isEmpty());
        
        Map<String, SitePage> sitePages = (Map<String, SitePage>) parseSiteConfig.get("sitePages");
        assertNotNull(sitePages);      
    }

}
