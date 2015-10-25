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
        assertFalse(matchList.isEmpty());
        assertFalse(matchList.get(0).isEmpty());
    }
    
    @Test(timeout=1000)
    public void testGetLessonAndClassOfUser() throws Exception {
        List<Map<String, String>> matchList = edu.getLessonAndClassOfUser();
        assertFalse(matchList.isEmpty());
        assertFalse(matchList.get(0).isEmpty());
    }
    
    @Test(timeout=1000)
    public void testGetClassStudentListOfUser() throws Exception {
        List<Map<String, String>> matchList = edu.getClassStudentList("027", "0521032B", "0002");
        assertFalse(matchList.isEmpty());
        assertFalse(matchList.get(0).isEmpty());
    }
    
    @Test(timeout=1000)
    public void testGetMajorLessonPlan() throws Exception {
    	List<Map<String, String>> matchList = edu.getMajorLessonPlan("027", "1", "0120133222");
    	assertFalse(matchList.isEmpty());
    	assertFalse(matchList.get(0).isEmpty());
    }
    
    @Test(timeout=1000)
    public void testGetMajorList() throws Exception {
        List<Map<String, String>> matchList = edu.getMajorList();
        assertFalse(matchList.isEmpty());
        assertFalse(matchList.get(0).isEmpty());
    }
    
    @Test(timeout=1000)
    public void testGetLessonClassList() throws Exception {
        List<Map<String, String>> matchList = edu.getLessonClassList("027", "0200051B");
        assertFalse(matchList.isEmpty());
        assertFalse(matchList.get(0).isEmpty());
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
    
}
