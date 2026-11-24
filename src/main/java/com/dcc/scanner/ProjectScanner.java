package com.dcc.scanner;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;

import com.dcc.models.FileInfo;
public class ProjectScanner {
Path projectPath;
int javaCount=0;
int htmlCount=0;
int cssCount=0;
int jsCount=0;
int sqlCount=0;
int otherCount=0;
int fileCount=0;

public ProjectStats scanProject(Path projectPath) throws IOException{	
	javaCount=0;
	htmlCount=0;
	cssCount=0;
	jsCount=0;
	sqlCount=0;
	otherCount=0;
	fileCount=0;
	List<FileInfo> files=new ArrayList<>();
	
	try(Stream<Path> paths=Files.walk(projectPath)){
		paths.filter(path -> Files.isRegularFile(path))
		.forEach(path->{
		fileCount++;
		
		long size = 0;
		try {
			size = Files.size(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String path1=path.getFileName().toString();
		int inde=path1.lastIndexOf('.');
			
		String file;
		if(inde == -1) {
			otherCount++;
			file="Other";
		}else {
		file=path1.substring(inde+1);
		if(file.equals("java")) {
			javaCount++;
		}
		else if(file.equals("html")) {
			htmlCount++;
			}
		else if(file.equals("css")) {
			cssCount++;
		}
		else if(file.equals("js")) {
			jsCount++;
		}
		else if(file.equals("sql")) {
			sqlCount++;
		}
		else {
			otherCount++;
		}
		}
		FileInfo info= new FileInfo(path1,file,size);
		files.add(info);
	});
	}
	return new ProjectStats(fileCount,javaCount,htmlCount,cssCount,jsCount,sqlCount,otherCount,files);
	
}


//public static void main(String[] args) throws IOException {
//	ProjectScanner scanner=new ProjectScanner();
//	ProjectStats stats = scanner.scanProject(Path.of("C:/Users/Joel Daniel/eclipse-workspace/CIA/src/cia"));
//	for (FileInfo file : stats.getFiles()) {
//	    System.out.println(
//	        file.getFileName() + " | " +
//	        file.getFileType() + " | " +
//	        file.getFilesize()
//	    );
//	}
//}
}
