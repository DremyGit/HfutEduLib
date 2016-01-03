package cn.dremy.hfut.hfutedulib.service;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;

import cn.dremy.hfut.hfutedulib.model.HfutClass;
import cn.dremy.hfut.hfutedulib.model.HfutMajor;
import cn.dremy.hfut.hfutedulib.model.HfutStudent;
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
        HfutClass[][][] lessonTable = edu.getStudentLessonTable();
        assertEquals(7, lessonTable.length);
        assertEquals(11, lessonTable[0].length);
//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; j < 11; j++) {
//                for (int k = 0; k < 3 && lessonTable[i][j][k] != null; k++) {
//                    System.out.print(lessonTable[i][j][k] + "\t\t\t");
//                }
//            }
//            System.out.println("");
//        }
    }
    
    @Test(timeout=1000)
    public void testGetLessonAndClassOfUser() throws Exception {
        List<Map<String, String>> matchList = edu.getLessonAndClassOfUser();
        assertFalse(matchList.isEmpty());
        assertEquals(4, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetClassStudentList() throws Exception {
        List<HfutStudent> matchList = edu.getClassStudentList("027", "0521032B", "0002");
        assertEquals(92, matchList.size());
        assertNotNull(matchList.get(0).getStudentName());
    }
    
    @Test(timeout=1000)
    public void testGetMajorLessonPlan() throws Exception {
    	List<Map<String, String>> matchList = edu.getMajorLessonPlan("027", "1", "0120133222");
    	assertEquals(11, matchList.size());
        assertEquals(6, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetMajorList() throws Exception {
        List<HfutMajor> matchList = edu.getMajorList();
        assertFalse(matchList.isEmpty());
        assertNotNull(matchList.get(0).getGradeMajorId());
    }
    
    @Test(timeout=1000)
    public void testGetLessonClassListByLessonId() throws Exception {
        List<Map<String, String>> matchList = edu.getLessonClassListByLessonId("027", "0200051B");
        assertEquals(29, matchList.size());
        assertEquals(7, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetLessonClassListByLessonName() throws Exception {
        List<Map<String, String>> matchList = edu.getLessonClassListByLessonName("027", "离散");
        assertEquals(3, matchList.size());
        assertEquals(7, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetLessonScoreList() throws Exception {
    	List<Map<String, String>> matchList = edu.getLessonScoreList();
    	assertFalse(matchList.isEmpty());
    	assertEquals(7, matchList.get(0).size());
    }
    
    @Test(timeout=1000)
    public void testGetStudentInfo() throws Exception {
    	Map<String, String> match = edu.getStudentDetailInfo();
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
        Map<String, String> match = edu.getClassDetailInfo("027", "0200051B", "0001");
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
