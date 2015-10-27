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
    
    public static SitePage studentInfo;
    
    public static SitePage majorLessonPlan;
    
    public static SitePage majorList;
    
    public static SitePage lessonClassList;
    
    public static SitePage classDetailInfo;
    
    public static SitePage lessonScoreList;
    
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
        studentInfo = sitePages.get("studentInfo");
        majorLessonPlan = sitePages.get("majorLessonPlan");
        majorList = sitePages.get("majorList");
        lessonClassList = sitePages.get("lessonClassList");
        classDetailInfo = sitePages.get("classDetailInfo");
        lessonScoreList = sitePages.get("lessonScoreList");
    }
}
