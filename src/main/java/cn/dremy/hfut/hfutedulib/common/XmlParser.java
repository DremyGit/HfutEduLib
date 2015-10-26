package cn.dremy.hfut.hfutedulib.common;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.dremy.hfut.hfutedulib.model.SitePage;

public class XmlParser {

    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseSiteConfig() {
        Map<String, Object> siteConfig = new HashMap<String, Object>();
        String filePath = XmlParser.class.getClassLoader().getResource("urls.xml").getPath();
        
        try {
            SAXReader reader = new SAXReader();
            Document document;
            if (filePath.indexOf("!") != -1) {
                InputStream input = XmlParser.class.getResourceAsStream("/urls.xml");
                document = reader.read(input);
            } else {
                File file = new File(filePath);
                document = reader.read(file);
            }

            Element root = document.getRootElement();
            
            Element hostnamesElement = root.element("hostnames");
            List<Element> hostnameElementList = hostnamesElement.elements();
            List<String> hostnameList = new ArrayList<String>();
            for (Element hostnameElement : hostnameElementList) {
                hostnameList.add(hostnameElement.getText());
            }
            siteConfig.put("hostnameList", hostnameList);
            siteConfig.put("encode", root.elementText("encode"));
            
            
            Element pagesElement = root.element("pages");
            List<Element> pageElementList = pagesElement.elements();
            Map<String, Object> sitePages = new HashMap<String, Object>();
            for (Element pageElement : pageElementList) {
                
                String id = pageElement.attributeValue("id");
                String note = pageElement.elementText("note");
                String url = pageElement.elementText("url");
                String method = pageElement.elementText("method");
                
                SitePage sitePage = new SitePage(id, note, url, method);
                sitePages.put(id, sitePage);
            }
            siteConfig.put("sitePages", sitePages);
            return siteConfig;
        } catch (Exception e) {
            return null;
        }
    }
}
