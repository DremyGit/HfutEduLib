package cn.dremy.hfut.hfutedulib.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import cn.dremy.hfut.hfutedulib.common.SiteConst;
import cn.dremy.hfut.hfutedulib.model.Lesson;

public class HfutEduLib {
    
    private Fetch fetch;
    
    public HfutEduLib(String studentId, String password) throws Exception {
        this.fetch = new Fetch();
        if (!login(studentId, password)) {
            throw new Exception("Login Error");
        }
    }
    
    public boolean login(String studentId, String password) throws Exception {
        
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("user", studentId);
        requestParams.put("password", password);
        requestParams.put("UserStyle", "student");
        HttpResponse res = fetch.fetchSitePage(SiteConst.loginAction, requestParams);
//        EntityUtils.toString(res.getEntity(), SiteConst.encode);
        return res.getStatusLine().getStatusCode() == 302 ;
    }
    
    public Lesson[][][] getStudentLessonTable() throws Exception {
        HttpResponse res = fetch.fetchSitePage(SiteConst.studentLessonTable);
        return RegexMatch.matchStudentLessonList(getContent(res));
    }
    
    public List<Map<String, String>> getLessonAndClassOfUser() throws Exception {
        
        HttpResponse res = fetch.fetchSitePage(SiteConst.lessonAndClassOfUser);
        return RegexMatch.matchLessonAndClassOfUser(getContent(res));
    }
    
    public List<Map<String, String>> getClassStudentList(String termId, String lessonId, String classId) throws Exception {
        
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("xqdm", termId);
        requestParams.put("kcdm", lessonId);
        requestParams.put("jxbh", classId);
        
        HttpResponse res = fetch.fetchSitePage(SiteConst.classStudentList, requestParams);
        return RegexMatch.matchClassStudentList(getContent(res));
    }
    
    public Map<String, String> getStudentInfo() throws Exception {
    	HttpResponse res = fetch.fetchSitePage(SiteConst.studentInfo);
    	return RegexMatch.matchStudentInfo(getContent(res));
    }
    
    public List<Map<String, String>> getMajorLessonPlan(String termId, String lessonTypeId, String gradeMajorId) throws Exception {
    	Map<String, Object> requestParams = new HashMap<>();
    	requestParams.put("xqdm", termId);
    	requestParams.put("kclxdm", lessonTypeId);
    	requestParams.put("ccjbyxzy", gradeMajorId);
    	
    	HttpResponse res = fetch.fetchSitePage(SiteConst.majorLessonPlan, requestParams);
    	return RegexMatch.matchMajorLessonPlanList(getContent(res));
    }
    
    public List<Map<String, String>> getMajorList() throws Exception {
        HttpResponse res = fetch.fetchSitePage(SiteConst.majorList);
        return RegexMatch.matchMajorList(getContent(res));
    }
    
    public List<Map<String, String>> getLessonClassListByLessonId(String termId, String lessonId) throws Exception {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("xqdm", termId);
        requestParams.put("kcdm", lessonId);
        
        HttpResponse res = fetch.fetchSitePage(SiteConst.lessonClassList, requestParams);
        return RegexMatch.matchLessonClassList(getContent(res));
    }
    
    public List<Map<String, String>> getLessonClassListByLessonName(String termId, String lessonName) throws Exception {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("xqdm", termId);
        requestParams.put("kcmc", lessonName);
        
        HttpResponse res = fetch.fetchSitePage(SiteConst.lessonClassList, requestParams);
        return RegexMatch.matchLessonClassList(getContent(res));
    }
    
    public Map<String, String> getClassDetailInfo(String termId, String classId, String lessonId) throws Exception {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("xqdm", termId);
        requestParams.put("jxbh", classId);
        requestParams.put("kcdm", lessonId);
        
        HttpResponse res = fetch.fetchSitePage(SiteConst.classDetailInfo, requestParams);
        return RegexMatch.matchClassDetailInfo(getContent(res));
    }
    
    public List<Map<String, String>> getLessonScoreList() throws Exception {
    	HttpResponse res = fetch.fetchSitePage(SiteConst.lessonScoreList);
    	return RegexMatch.matchLessonScoreList(getContent(res));
    }
    
    

    
    private String getContent(HttpResponse res) throws ParseException, IOException {
        String content =  EntityUtils.toString(res.getEntity(), SiteConst.encode);
        return content;
    }
    

}
