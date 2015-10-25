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
    
    public static List<Map<String, String>> matchMajorLessonPlanList(String content) {
    	String regexStr = "(?<id>\\d+)</td>\\s[^>]+><a[^>]+>(?<lessonId>[^<]+).+\\s[^>]+>(?<lessonName>[^<]*).+\\s[^>]+>(?<lessonCredit>[^<]*).+\\s[^>]+>(?<lessonTime>[^<]*).+\\s[^>]+>(?<teachUnit>[^<]*)";
    	String[] keys = {"id", "lessonId", "lessonName", "lessonCredit", "lessonTime", "teachUnit"};
    	return matchList(content, regexStr, keys);
    }
    
    public static Map<String, String> matchStudentInfo(String content) {
    	
    	Map<String, String> matchResult = new HashMap<>();
    	
    	String regexStr = "<tr \\S+ bgcolor=\"#D6D3CE\">\\s+(?<tr>(?:.*?\\s)*?)\\s+</tr>";
    	String[] keys = {"tr"};
    	
		List<Map<String, String>> matchList = matchList(content, regexStr, keys);
		
		String content1 = "", content2 = "";
		for (int i = 0; i < 7; i++) {
			if (i < 2)
				content1 += "\n" + matchList.get(i).get("tr");
			else
				content2 += "\n" + matchList.get(i).get("tr");
		}
		
		String[] keys1 = {"studentId", "name", "sex", "canSelectLesson", "registerStatus", "studyStatus"};
		String regexStr1 = "<td[^>]+>[^:]+:\\s*(?<value>[^\\s<]+)";
		List<Map<String, String>> list1 = matchList(content1, regexStr1, "value");
		
		String[] keys2 = {"college", "major", "class", "examerId", "nationality", "birthday", "province",
				"politicalStatus", "IDNumber", "marrigeStatus", "homeAddress", "homeTelephone", "highSchool",
				"homeProvince", "phone", "inSchoolTime", "inSchoolType", "foregnLanguage"};
		String regexStr2 = "<td[^>]+>(?<value>[^\\s&<]*)";
		List<Map<String, String>> list2 = matchList(content2, regexStr2, "value");
		
		int i = 0;
		for (Map<String, String> match : list1) {
			matchResult.put(keys1[i++], match.get("value"));
		}
		
		i = 0;
		for (Map<String, String> match : list2) {
			matchResult.put(keys2[i++], match.get("value"));
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
