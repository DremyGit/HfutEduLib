package cn.dremy.hfut.hfutedulib.common;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestSiteConst {

    @Test
    public void testHostnameList() {
        assertNotNull(SiteConst.hostnameList);
    }
    
    @Test
    public void testSiteUrls() {

        String id = "studentLessonTable";
        assertEquals(id, SiteConst.sitePages.get("studentLessonTable").getId());
        assertEquals("个人课表页", SiteConst.sitePages.get("studentLessonTable").getNote());
        
        assertNotNull(SiteConst.loginPage);
    }
}
