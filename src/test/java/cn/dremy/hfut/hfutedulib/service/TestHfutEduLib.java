package cn.dremy.hfut.hfutedulib.service;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

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
    
}
