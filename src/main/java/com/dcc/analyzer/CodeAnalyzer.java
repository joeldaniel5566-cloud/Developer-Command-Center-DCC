package com.dcc.analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import com.dcc.models.CodeMetrics;

public class CodeAnalyzer {
	int count;
	boolean insideComment;
	int total=0, code=0, blank=0, comment=0;
	
	public int countLines(Path path) {
		try {
			List<String> lines=Files.readAllLines(path);
			return lines.size();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int countBlankLines(Path path) {
		count=0;
		try {
			List<String> lines=Files.readAllLines(path);
			lines.forEach(line->{
				if(line.isBlank()) {
					count++;
				}
			});
			return count;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int countCommentLines(Path path) {
		count=0;
		insideComment = false;
		try {
			List<String> lines=Files.readAllLines(path);
			lines.forEach(line->{
				line=line.trim();
				if(line.startsWith("//", 0)) {
					count++;
				}
				if(line.startsWith("/*",0)) {
					insideComment = true;
				}
				if(insideComment) {
					count++;
				}
				if(line.endsWith("*/")) {
					insideComment = false;
				}
			});
			return count;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean isSourceFile(Path path) {
		String fileName=path.getFileName().toString();
		int index=fileName.lastIndexOf('.');
		if(index==-1) {
			return false;
		}
		else {
		String ext=fileName.substring(index + 1);
		if(ext.equals("java")) {
			return true;
		}
		else if(ext.equals("html")) {
			return true;
		}
		else if(ext.equals("css")) {
			return true;
		}
		else if(ext.equals("js")) {
			return true;
		}
		else if(ext.equals("sql")) {
			return true;
		}
		else {
		return false;
		}
		}
	}
	
	public int countCodeLines(Path path) {
		count=0;
		insideComment=false;
		try {
			List<String> lines=Files.readAllLines(path);
			lines.forEach(line->{
				line=line.trim();
				
				if(line.isBlank()) {
					
				}
				else if(insideComment) {
					if(line.contains("*/")) {
					insideComment=false;
				}
				}
				else if(line.startsWith("//")) {
					
				}
				else if(line.startsWith("/*")) {
					insideComment = true;
					if(line.contains("*/")) {
						insideComment=false;
					}
				}
				else {
					count++;
				}
			});
			return count;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public CodeMetrics analyzeFile(Path path) {
		int total=countLines(path);
		int code=countCodeLines(path);
		int blank=countBlankLines(path);
		int comment=countCommentLines(path);
		return new CodeMetrics(total,code,blank,comment);
		
	}
	
	public CodeMetrics analyzeProject(Path projectPath) {
		total=0;
		code=0;
		blank=0; 
		comment=0;

		try (Stream<Path> paths=Files.walk(projectPath)){
			paths.
			filter(path->Files.isRegularFile(path)).
			forEach(path->{
				if(isSourceFile(path)) {
					CodeMetrics metrics = analyzeFile(path);
					total+=metrics.getTotalLines();
					code+=metrics.getCodeLines();
					blank+=metrics.getBlankLines();
					comment+=metrics.getCommentLines();
				}});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new CodeMetrics(total, code, blank, comment);
		
	}
}
