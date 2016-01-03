package cn.dremy.hfut.hfutedulib.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import cn.dremy.hfut.hfutedulib.common.SiteConst;
import cn.dremy.hfut.hfutedulib.model.HfutClass;
import cn.dremy.hfut.hfutedulib.model.HfutMajor;
import cn.dremy.hfut.hfutedulib.model.HfutStudent;

public class HfutEduLib {
    
    private Fetch fetch;
    
    /**
     * 创建HfutEduLib用户对象,需要学号和密码进行登录
     * @param studentId 学生用户学号
     * @param password 教务系统密码
     * @throws Exception 网络错误或者登录失败时抛出异常
     */
    public HfutEduLib(String studentId, String password) throws Exception {
        this.fetch = new Fetch();
        if (!login(studentId, password)) {
            throw new Exception("Login Error");
        }
    }
    
    private boolean login(String studentId, String password) {
        
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("user", studentId);
        requestParams.put("password", password);
        requestParams.put("UserStyle", "student");
        HttpResponse res = fetch.fetchSitePage(SiteConst.loginAction, requestParams);
        return res.getStatusLine().getStatusCode() == 302 ;
    }
    
    /**
     * 获取登录用户的当前课程表信息
     * lessonName, classPlace, timeBegin, timeEnd
     * @return 返回教学班三维数组, 第一维表示星期, 第二维表示上课时间, 第三维表示不同时期相同时间的课程
     * @throws Exception
     */
    public HfutClass[][][] getStudentLessonTable() {
        HttpResponse res = fetch.fetchSitePage(SiteConst.studentLessonTable);
        return RegexMatch.matchStudentLessonList(getContent(res));
    }
    
    /**
     * 获取登录用户当前学期的教学班列表(含课程信息)
     * termId, lessonId, classId, lessonName
     * @return 教学班列表
     * @throws Exception
     */
    public List<Map<String, String>> getLessonAndClassOfUser() {
        
        HttpResponse res = fetch.fetchSitePage(SiteConst.lessonAndClassOfUser);
        return RegexMatch.matchLessonAndClassOfUser(getContent(res));
    }
    
    /**
     * 获取教学班中的学生列表
     * id, studentId, studentName
     * @param termId 学期id
     * @param lessonId 课程id
     * @param classId 教学班id
     * @return 学生列表
     * @throws Exception
     */
    public List<HfutStudent> getClassStudentList(String termId, String lessonId, String classId) {
        
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("xqdm", termId);
        requestParams.put("kcdm", lessonId);
        requestParams.put("jxbh", classId);
        
        HttpResponse res = fetch.fetchSitePage(SiteConst.classStudentList, requestParams);
        List<Map<String, String>> matchList = RegexMatch.matchClassStudentList(getContent(res));
        return mapListToModelList(matchList, HfutStudent.class);
    }
    
    /**
     * 获取登录用户的学生信息
     * @return 学生信息
     * @throws Exception
     */
    public Map<String, String> getStudentDetailInfo() {
    	HttpResponse res = fetch.fetchSitePage(SiteConst.studentInfo);
    	return RegexMatch.matchStudentDetailInfo(getContent(res));
    }
    
    /**
     * 获取专业的课程计划
     * id, lessonId, lessonName, lessonCredit, lessonTime, teachUnit
     * @param termId 学期id
     * @param lessonTypeId 课程类型id, 必修课为1, 选修课为2
     * @param gradeMajorId 年级专业id
     * @return 课程列表
     * @throws Exception
     */
    public List<Map<String, String>> getMajorLessonPlan(String termId, String lessonTypeId, String gradeMajorId) {
    	Map<String, Object> requestParams = new HashMap<>();
    	requestParams.put("xqdm", termId);
    	requestParams.put("kclxdm", lessonTypeId);
    	requestParams.put("ccjbyxzy", gradeMajorId);
    	
    	HttpResponse res = fetch.fetchSitePage(SiteConst.majorLessonPlan, requestParams);
    	return RegexMatch.matchMajorLessonPlanList(getContent(res));
    }
    
    /**
     * 获取年级专业列表
     * gradeMajorId, gradeMajorName
     * @return 年级专业列表
     * @throws Exception
     */
    public List<HfutMajor> getMajorList() {
        HttpResponse res = fetch.fetchSitePage(SiteConst.majorList);
        List<Map<String, String>> matchList = RegexMatch.matchMajorList(getContent(res));
        return mapListToModelList(matchList, HfutMajor.class);
    }
    
    /**
     * 通过课程id获取课程的教学班列表
     * lessonId, lessonName, classId, maxStudentNumber, teacherId, teacherName, lessonType
     * @param termId 学期id
     * @param lessonId 课程id
     * @return 教学班列表
     * @throws Exception
     */
    public List<Map<String, String>> getLessonClassListByLessonId(String termId, String lessonId) {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("xqdm", termId);
        requestParams.put("kcdm", lessonId);
        
        HttpResponse res = fetch.fetchSitePage(SiteConst.lessonClassList, requestParams);
        return RegexMatch.matchLessonClassList(getContent(res));
    }
    
    /**
     * 通过课程名查询课程, 获取相关课程的教学班列表
     * lessonId, lessonName, classId, maxStudentNumber, teacherId, teacherName, lessonType
     * @param termId 学期id
     * @param lessonName 课程名(可使用关键字)
     * @return 教学班列表
     * @throws Exception
     */
    public List<Map<String, String>> getLessonClassListByLessonName(String termId, String lessonName) {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("xqdm", termId);
        requestParams.put("kcmc", lessonName);
        
        HttpResponse res = fetch.fetchSitePage(SiteConst.lessonClassList, requestParams);
        return RegexMatch.matchLessonClassList(getContent(res));
    }
    

    /**
     * 获取教学班详细信息
     * @param termId 学期id
     * @param lessonId 课程id
     * @param classId 教学班id
     * @return 教学班详情
     * @throws Exception
     */
    public Map<String, String> getClassDetailInfo(String termId, String lessonId, String classId) {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("xqdm", termId);
        requestParams.put("jxbh", classId);
        requestParams.put("kcdm", lessonId);
        
        HttpResponse res = fetch.fetchSitePage(SiteConst.classDetailInfo, requestParams);
        return RegexMatch.matchClassDetailInfo(getContent(res));
    }
    
    /**
     * 获取登录用户的全部考试成绩列表
     * termName, lessonId, lessonName, classId, lessonScore, resitScore, lessonCredit
     * @return 考试成绩列表
     * @throws Exception
     */
    public List<Map<String, String>> getLessonScoreList() {
    	HttpResponse res = fetch.fetchSitePage(SiteConst.lessonScoreList);
    	return RegexMatch.matchLessonScoreList(getContent(res));
    }
    
    

    
    private String getContent(HttpResponse res) {
        String content;
        try {
            content = EntityUtils.toString(res.getEntity(), SiteConst.encode);
            return content;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private <T> T mapToModel(Map<String, String> map, Class<T> type) {
        try {
            return type.getConstructor(Map.class).newInstance(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private <T> List<T> mapListToModelList(List<Map<String, String>> mapList, Class<T> type) {
        List<T> resultList = new ArrayList<>();
        for (Map<String, String> map : mapList) {
            resultList.add(mapToModel(map, type));
        }
        return resultList;
    }
    

}
