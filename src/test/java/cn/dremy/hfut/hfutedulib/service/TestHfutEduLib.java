package cn.dremy.hfut.hfutedulib.service;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;

import cn.dremy.hfut.hfutedulib.service.HfutEduLib;

public class TestHfutEduLib {
    
    private static HfutEduLib edu;
    
    @BeforeClass
    public static void init() throws Exception {
        edu = new HfutEduLib("2014217211", "654321");
        assertTrue(true);
    }

    @Test(timeout=1000)
    public void testGetStudentLessonTable() throws Exception {
        List<Map<String, String>> matchList = edu.getStudentLessonTable();
        assertEquals(11, matchList.size());
        assertEquals(7, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetLessonAndClassOfUser() throws Exception {
        List<Map<String, String>> matchList = edu.getLessonAndClassOfUser();
        assertFalse(matchList.isEmpty());
        assertEquals(3, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetClassStudentList() throws Exception {
        List<Map<String, String>> matchList = edu.getClassStudentList("027", "0521032B", "0002");
        assertEquals(92, matchList.size());
        assertEquals(3, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetMajorLessonPlan() throws Exception {
    	List<Map<String, String>> matchList = edu.getMajorLessonPlan("027", "1", "0120133222");
    	assertEquals(11, matchList.size());
        assertEquals(6, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetMajorList() throws Exception {
        List<Map<String, String>> matchList = edu.getMajorList();
        assertFalse(matchList.isEmpty());
        assertEquals(2, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetLessonClassListByLessonId() throws Exception {
        List<Map<String, String>> matchList = edu.getLessonClassListByLessonId("027", "0200051B");
        assertEquals(29, matchList.size());
        assertEquals(6, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetLessonClassListByLessonName() throws Exception {
        List<Map<String, String>> matchList = edu.getLessonClassListByLessonName("027", "离散");
        assertEquals(3, matchList.size());
        assertEquals(6, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetLessonScoreList() throws Exception {
    	List<Map<String, String>> matchList = edu.getLessonScoreList();
    	assertFalse(matchList.isEmpty());
    	assertEquals(7, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetStudentInfo() throws Exception {
    	Map<String, String> match = edu.getStudentInfo();
    	assertFalse(match.isEmpty());
    	Set<String> keySet = match.keySet();
    	int nullNum = 0;
    	for (String key : keySet) {
    		if (match.get(key).equals("")) {
    			nullNum++;
    		}
    	}
    	assertEquals(24, keySet.size());
    	assertEquals(3, nullNum);
    }
    
    @Test(timeout=1000)
    public void testGetClassDetailInfo() throws Exception {
        Map<String, String> match = edu.getClassDetailInfo("027", "0001", "0200051B");
        assertFalse(match.isEmpty());
        Set<String> keySet = match.keySet();
        int nullNum = 0;
        for (String key : keySet) {
            if (match.get(key).equals("")) {
                nullNum++;
            }
        }
        assertEquals(14, keySet.size());
        assertEquals(2, nullNum);
    }
    
}
