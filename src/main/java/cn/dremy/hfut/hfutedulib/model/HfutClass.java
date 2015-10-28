package cn.dremy.hfut.hfutedulib.model;

public class HfutClass {
    
    private String lessonId;

    private String lessonName;
    
    private String lessonCredit;
    
    private String lessonScore;
    
    private String resitScore;
    
    private String classId;
    
    private String classPlace;
    
    private String maxStudentNumber;
    
    private String teacherId;
    
    private String teacherName;
    
    private String lessonTime;
    
    private String timeBegin;
    
    private String timeEnd;
    
    private String teachUnit;

    public HfutClass(String lessonName, String classPlace, String timeBegin, String timeEnd) {
        this.lessonName = lessonName;
        this.classPlace = classPlace;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getClassPlace() {
        return classPlace;
    }

    public void setClassPlace(String classPlace) {
        this.classPlace = classPlace;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "Lesson [lessonName=" + lessonName + ", classPlace=" + classPlace + ", timeBegin=" + timeBegin
                + ", timeEnd=" + timeEnd + "]";
    }
    
    
}
