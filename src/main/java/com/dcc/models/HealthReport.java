package com.dcc.models;

import java.util.List;

public class HealthReport {
	List<String> largeFiles;
	List<String> lowCommentFiles;
	List<String> manyMethodFiles;
	int healthScore;
	
	public HealthReport(List<String> largeFiles, List<String> lowCommentFiles, List<String> manyMethodFiles, int healthScore) {
		this.largeFiles = largeFiles;
		this.lowCommentFiles = lowCommentFiles;
		this.manyMethodFiles = manyMethodFiles;
		this.healthScore=healthScore;
	}
	
	public List<String> getLargeFiles() {
		return largeFiles;
	}
	public List<String> getLowCommentFiles() {
		return lowCommentFiles;
	}
	public List<String> getManyMethodFiles() {
		return manyMethodFiles;
	}
	public int getHealthScore() {
		return healthScore;
	}
}
