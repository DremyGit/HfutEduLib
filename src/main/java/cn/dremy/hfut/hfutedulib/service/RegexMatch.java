package cn.dremy.hfut.hfutedulib.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cn.dremy.hfut.hfutedulib.model.HfutClass;

public class RegexMatch {

    public static List<Map<String, String>> matchLessonAndClassOfUser(String content) {
        
        String regexStr = "name=\"xqdm\" value=(?<termId>\\w*)>\\s*.*value=(?<lessonId>\\w*)>\\s*.*value=(?<classId>\\w*)>\\s+.+\\s+.+\\s+[^>]*>(?<lessonName>[^<]*)";
        String[] keys = {"termId", "lessonId", "classId", "lessonName"};
        return matchList(content, regexStr, keys);
    }
    
    public static List<Map<String, String>> matchClassStudentList(String content) {
        String regexStr = "<TR[^>]+>\\s+<TD>(?<id>\\w+)</TD>\\s+<TD>\\s*(?<studentId>\\S+)\\s+</TD>\\s+<TD>\\s+(?<studentName>\\S+)\\s+</TD>";
        String[] keys = {"id", "studentId", "studentName"};
        return matchList(content, regexStr, keys);
    }
    
    public static HfutClass[][][] matchStudentLessonList(String content) {
        String regexStr = "<TR[^>]+>\\s+.+\\s+<TD>(?<Mon>[^>]*)</TD>\\s+<TD>(?<Tue>[^>]*)</TD>\\s+<TD>(?<Wed>[^>]*)</TD>\\s+<TD>(?<Thu>[^>]*)</TD>\\s+<TD>(?<Fri>[^>]*)</TD>\\s+<TD>(?<Sat>[^>]*)</TD>\\s+<TD>(?<Sun>[^>]*)</TD>\\s+";
        String[] keys = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        List<Map<String, String>> matchList = matchList(content, regexStr, keys);
        if (matchList == null) {
            return null;
        }
        HfutClass lessonTable[][][] = new HfutClass[7][11][3];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 7; j++) {
                Map<String, String> match = matchList.get(i);
                String lessons = match.get(keys[j]);
                List<Map<String, String>> classDetail = matchLessonTableDetail(lessons);
                for (int k = 0; k < classDetail.size(); k++) {
                    HfutClass lesson = new HfutClass(classDetail.get(k).get("lessonName"), classDetail.get(k).get("classPlace"), classDetail.get(k).get("timeBegin"), classDetail.get(k).get("timeEnd"));
                    lessonTable[j][i][k] = lesson ;
                }
                
            }
        }
        return lessonTable;
    }
    
    private static List<Map<String, String>> matchLessonTableDetail(String content) {
        String regexStr = "(?<lessonName>[^<>\\[]+)\\[(?<classPlace>[^ ]+) \\((?<timeBegin>\\d+)-(?<timeEnd>\\d+)[^/]+/";
        String[] keys = {"lessonName", "classPlace", "timeBegin", "timeEnd"};
        return matchList(content, regexStr, keys);
    }
    
    public static List<Map<String, String>> matchMajorLessonPlanList(String content) {
    	String regexStr = "(?<id>\\d+)</td>\\s[^>]+><a[^>]+>(?<lessonId>[^<]+).+\\s[^>]+>(?<lessonName>[^<]*).+\\s[^>]+>(?<lessonCredit>[^<]*).+\\s[^>]+>(?<lessonTime>[^<]*).+\\s[^>]+>(?<teachUnit>[^<]*)";
    	String[] keys = {"id", "lessonId", "lessonName", "lessonCredit", "lessonTime", "teachUnit"};
    	return matchList(content, regexStr, keys);
    }
    
    public static List<Map<String, String>> matchMajorList(String content) {
        String regexStr = "<option value=\"(?<gradeMajorId>\\d{10})\">(?<gradeMajorName>[^<]+)</option>";
        String[] keys = {"gradeMajorId", "gradeMajorName"};
        return matchList(content, regexStr, keys);
    }
    
    public static List<Map<String, String>> matchLessonClassList(String content) {
        String regexStr = "(?<lessonId>\\w+)</a></td>\\s+<td[^>]+>(?<lessonName>[^<]+)</td>\\s+<td[^>]+>(?<classId>\\d{4})</td>\\s+<td[^>]+>(?<maxStudentNumber>[^<]*)</td>\\s+.+\\s+[^']+'(?<teacherId>\\d{8}).+\\s+(?<teacherName>\\S+)\\s+\\S+\\s+</td>\\s+<td[^>]+>(?<lessonType>[^<]+)";
        String[] keys = {"lessonId", "lessonName", "classId", "maxStudentNumber", "teacherId", "teacherName", "lessonType"};
        return matchList(content, regexStr, keys);
    }
    
    public static List<Map<String, String>> matchLessonScoreList(String content) {
    	String regexStr = "<TD>(?<termName>[^<]+)</TD>\\s+<TD[^>]*>(?<lessonId>[^<]+)</TD>\\s+<TD>(?<lessonName>[^<]+)</TD>\\s+<TD[^>]*>(?<classId>[^<]+)</TD>\\s+<TD[^>]*>\\s+(?<lessonScore>\\S+)\\s*</TD>\\s+<TD[^>]*>(?<resitScore>[^<]*)</TD>\\s+<TD>(?<lessonCredit>[^<]+)</TD>";
    	String[] keys = {"termName", "lessonId", "lessonName", "classId", "lessonScore", "resitScore", "lessonCredit"};
    	return matchList(content, regexStr, keys);
    }
    
    public static Map<String, String> matchStudentDetailInfo(String content) {
        
        String filterRegexStr = "<tr \\S+ bgcolor=\"#D6D3CE\">\\s+(?<value>(?:.*?\\s)*?)\\s+</tr>";
        String[] regexStrArray = {
                "<td[^>]+>[^:]+:\\s*(?<value>[^\\s<]+)",
                "<td[^>]+>(?<value>[^\\s&<]*)"
            };
        String[][] keysArray = {
                {"studentId", "name", "sex", "canSelectLesson", "registerStatus", "studyStatus"},
                
                {"college", "major", "class", "examerId", "nationality", "birthday", "province",
                  "politicalStatus", "IDNumber", "marrigeStatus", "homeAddress", "homeTelephone", "highSchool",
                  "homeProvince", "phone", "inSchoolTime", "inSchoolType", "foregnLanguage"}
        };
        int filterRows = 2;
        return matchDivideTable(content, filterRegexStr, regexStrArray, keysArray, filterRows);
    }
    
    public static Map<String, String> matchClassDetailInfo(String content) {
        String filterRegexStr = "<tr.+?bgcolor=\"#D6D3CE\">\\s+(?<value>(?:.+\\s+)+?)</tr>";
        String[] regexStrArray = {
                "<td align=\"center\">(?<value>[^&<]+)</td>",
                "<td.+?bgcolor=\"#D6D3CE\">\\s*(?<value>[^<\n]*)"
        };
        
        String[][] keysArray = {
                {"classId", "lessonName", "lessonType", "lessonCredit", "teachUnit", "campus",
                    "lessonTime", "examType", "sexRequire", "studentNumber"},
                {"priorityClass", "timePlace", "forbidRange", "remarks"}
        };
        
        return matchDivideTable(content, filterRegexStr, regexStrArray, keysArray);
    }
    
    private static Map<String, String> matchDivideTable(String content, String filterRegexStr, String[] regexStrArray, String[][] keysArray, int... filterRows) {
        Map<String, String> matchResult = new HashMap<>();
        
        
        List<Map<String, String>> matchList = matchList(content, filterRegexStr, "value");
        String[] filteredStrings = new String[filterRows.length + 1];
        
        int n = 0, m = 0;
        for (Map<String, String> match : matchList) {
            if (m < filterRows.length && n == filterRows[m]) {
                m++;
            }
            filteredStrings[m] += match.get("value") + "\n";
            n++;
        }
        
        for (int i = 0; i < regexStrArray.length; i++) {
            List<Map<String, String>> list = null;
            if (filterRows.length == 0) {
                list = matchList(filteredStrings[0], regexStrArray[i], "value");
            } else {
                list = matchList(filteredStrings[i], regexStrArray[i], "value");
            }
            
            int j = 0;
            for (Map<String, String> match : list) {
                matchResult.put(keysArray[i][j], match.get("value"));
                j++;
            }
        }
        return matchResult;
        
    }
    
    private static List<Map<String, String>> matchList(String content, String regexStr, String... keys) {
        
        List<Map<String, String>> matchList = new ArrayList<>();
        
        try {
            Pattern regex = Pattern.compile(regexStr);
            Matcher regexMatcher = regex.matcher(content);
            while (regexMatcher.find()) {
                Map<String, String> matchMap = new HashMap<String, String>();
                for (String key : keys) {
                    matchMap.put(key, regexMatcher.group(key));
                }
//                for (int i = 1; i <= keys.length; i++) {
//                	matchMap.put(String.valueOf(keys[i - 1]), regexMatcher.group(i));
//                }
                matchList.add(matchMap);
            }
        } catch (PatternSyntaxException ex) {
            
        }
        return matchList;
    }
}
