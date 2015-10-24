package cn.dremy.hfut.hfutedulib.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexMatch {

    public static List<Map<String, String>> matchLessonAndClassOfUser(String content) {
        
        String regexStr = "name=\"xqdm\" value=(?<xqdm>\\w*)>\\s*.*value=(?<kcdm>\\w*)>\\s*.*value=(?<jxbh>\\w*)>\\s+.+\\s+.+\\s+[^>]*>(?<lessonName>[^<]*)";
        String[] keys = {"xqdm", "kcdm", "lessonName"};
        return matchList(content, regexStr, keys);
    }
    
    public static List<Map<String, String>> matchClassStudentList(String content) {
        String regexStr = "<TR[^>]+>\\s+<TD>(?<id>\\w+)</TD>\\s+<TD>\\s*(?<studentId>\\S+)\\s+</TD>\\s+<TD>\\s+(?<studentName>\\S+)\\s+</TD>";
        String[] keys = {"id", "studentId", "studentName"};
        return matchList(content, regexStr, keys);
    }
    
    public static List<Map<String, String>> matchStudentLessonList(String content) {
        String regexStr = "<TR[^>]+>\\s+.+\\s+<TD>(?<Mon>[^>]*)</TD>\\s+<TD>(?<Tue>[^>]*)</TD>\\s+<TD>(?<Wed>[^>]*)</TD>\\s+<TD>(?<Thu>[^>]*)</TD>\\s+<TD>(?<Fri>[^>]*)</TD>\\s+<TD>(?<Sat>[^>]*)</TD>\\s+<TD>(?<Sun>[^>]*)</TD>\\s+";
        String[] keys = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        return matchList(content, regexStr, keys);
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
                matchList.add(matchMap);
            }
        } catch (PatternSyntaxException ex) {
            
        }
        return matchList;
    }
}
