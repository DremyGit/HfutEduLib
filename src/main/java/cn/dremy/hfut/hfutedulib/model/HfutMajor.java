package cn.dremy.hfut.hfutedulib.model;

import java.util.Map;

public class HfutMajor {

    private String majorId;
    
    private String majorName;
    
    public HfutMajor(Map<String, String> match) {
        
        majorId     =   match.get("majorId");
        majorName   =   match.get("majorName");
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
    
    
}
