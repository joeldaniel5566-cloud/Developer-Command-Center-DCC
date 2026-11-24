package com.dcc.scanner;

import java.util.List;

import com.dcc.models.FileInfo;

public class ProjectStats {
	int totalFiles;
	int javaCount;
	int htmlCount;
	int cssCount;
	int jsCount;
	int sqlCount;
	int otherCount;
	List<FileInfo> files;
	public ProjectStats(int totalFiles, int javaCount, int htmlCount, int cssCount, int jsCount, int sqlCount, int otherCount, List<FileInfo> files ) {
		this.totalFiles=totalFiles;
		this.javaCount=javaCount;
		this.htmlCount=htmlCount;
		this.cssCount=cssCount;
		this.jsCount=jsCount;
		this.sqlCount=sqlCount;
		this.otherCount=otherCount;
		this.files=files;
	}
	public int getTotalFiles() {
		return totalFiles;
	}
	public int getJavaCount() {
		return javaCount;
	}
	public int getHtmlCount() {
		return htmlCount;
	}
	public int getCssCount() {
		return cssCount;
	}
	public int getJsCount() {
		return jsCount;
	}
	public int getSqlCount() {
		return sqlCount;
	}
	public int getOtherCount() {
		return otherCount;
	}
	public List<FileInfo> getFiles() {
		return files;
	}
	
}
