package cn.dremy.hfut.hfutedulib.model;

import java.util.Map;

public class HfutMajor {

    private String gradeMajorId;
    
    private String gradeMajorName;
    
    public HfutMajor(Map<String, String> match) {
        
        gradeMajorId     =   match.get("gradeMajorId");
        gradeMajorName   =   match.get("gradeMajorName");
    }

	public String getGradeMajorId() {
		return gradeMajorId;
	}

	public void setGradeMajorId(String gradeMajorId) {
		this.gradeMajorId = gradeMajorId;
	}

	public String getGradeMajorName() {
		return gradeMajorName;
	}

	public void setGradeMajorName(String gradeMajorName) {
		this.gradeMajorName = gradeMajorName;
	}

    
    
}
