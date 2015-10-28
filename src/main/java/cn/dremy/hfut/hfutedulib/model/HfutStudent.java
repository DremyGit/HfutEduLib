package cn.dremy.hfut.hfutedulib.model;

import java.util.Map;

public class HfutStudent {
    
    private String id;

    private String studentId;
    
    private String studentName;
    
    public HfutStudent(Map<String, String> map) {
        
        id          =   map.get("id");
        studentId   =   map.get("studentId");
        studentName =   map.get("studentName");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    
    
}
