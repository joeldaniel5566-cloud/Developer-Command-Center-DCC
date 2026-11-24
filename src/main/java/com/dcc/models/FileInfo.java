package com.dcc.models;

public class FileInfo {
String fileName;
String fileType;
long fileSize;
public FileInfo(String fileName, String fileType, long fileSize) {
	this.fileName = fileName;
	this.fileType = fileType;
	this.fileSize = fileSize;
}
public String getFileName() {
	return fileName;
}
public String getFileType() {
	return fileType;
}
public long getFileSize() {
	return fileSize;
}
}
