package cn.dremy.hfut.hfutedulib.common;

import java.util.List;
import java.util.Map;

import cn.dremy.hfut.hfutedulib.model.SitePage;

@SuppressWarnings("unchecked")
public class SiteConst {

    public static List<String> hostnameList;
    
    public static String preHostname;
    
    public static String encode;
    
    public static Map<String, SitePage> sitePages;

    public static SitePage loginPage;
    
    public static SitePage loginAction;
    
    public static SitePage studentLessonTable;
    
    public static SitePage lessonAndClassOfUser;
    
    public static SitePage classStudentList;
    
    
    static {
        Map<String, Object> xmlSiteConfig = XmlParser.parseSiteConfig();
        hostnameList = (List<String>) xmlSiteConfig.get("hostnameList");
        sitePages = (Map<String, SitePage>) xmlSiteConfig.get("sitePages");
        encode = xmlSiteConfig.get("encode").toString();
        loginPage = sitePages.get("loginPage");
        loginAction = sitePages.get("loginAction");
        studentLessonTable = sitePages.get("studentLessonTable");
        lessonAndClassOfUser = sitePages.get("lessonAndClassOfUser");
        classStudentList = sitePages.get("classStudentList");
    }
}
