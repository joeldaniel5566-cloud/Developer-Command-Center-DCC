package com.dcc.models;

public class CodeMetrics {
private int totalLines;
private int codeLines;
private int blankLines;
private int commentLines;
public CodeMetrics(int totalLines, int codeLines, int blankLines, int commentLines) {
	this.totalLines = totalLines;
	this.codeLines = codeLines;
	this.blankLines = blankLines;
	this.commentLines = commentLines;
}
public int getTotalLines() {
	return totalLines;
}
public int getCodeLines() {
	return codeLines;
}
public int getBlankLines() {
	return blankLines;
}
public int getCommentLines() {
	return commentLines;
}

}
