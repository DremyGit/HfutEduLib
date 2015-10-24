package cn.dremy.hfut.hfutedulib.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import cn.dremy.hfut.hfutedulib.common.SiteConst;

public class HfutEduLib {
    
    public HfutEduLib(String studentId, String password) throws Exception {
        if (!login(studentId, password)) {
            throw new Exception("Login Error");
        }
    }
    
    public boolean login(String studentId, String password) throws Exception {
        
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("user", studentId);
        requestParams.put("password", password);
        requestParams.put("UserStyle", "student");
        HttpResponse res = Fetch.fetchSitePage(SiteConst.loginAction, requestParams);
        EntityUtils.toString(res.getEntity(), SiteConst.encode);
        return res.getStatusLine().getStatusCode() == 302 ;
    }
    
    public List<Map<String, String>> getStudentLessonTable() throws Exception {
        HttpResponse res = Fetch.fetchSitePage(SiteConst.studentLessonTable);
        return RegexMatch.matchStudentLessonList(getContent(res));
    }
    
    public List<Map<String, String>> getLessonAndClassOfUser() throws Exception {
        
        HttpResponse res = Fetch.fetchSitePage(SiteConst.lessonAndClassOfUser);
        return RegexMatch.matchLessonAndClassOfUser(getContent(res));
    }
    
    public List<Map<String, String>> getClassStudentList(String termId, String lessonId, String classNo) throws Exception {
        
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("xqdm", termId);
        requestParams.put("kcdm", lessonId);
        requestParams.put("jxbh", classNo);
        
        HttpResponse res = Fetch.fetchSitePage(SiteConst.classStudentList, requestParams);
        return RegexMatch.matchClassStudentList(getContent(res));
    }
    

    
    private String getContent(HttpResponse res) throws ParseException, IOException {
        String content =  EntityUtils.toString(res.getEntity(), SiteConst.encode);
        return content;
    }
    

}
