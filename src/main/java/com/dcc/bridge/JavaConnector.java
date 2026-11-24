package com.dcc.bridge;

import java.io.*;
import java.nio.file.Path;

import com.dcc.analyzer.CodeAnalyzer;
import com.dcc.analyzer.HealthAnalyzer;
import com.dcc.detector.ProjectDetector;
import com.dcc.models.CodeMetrics;
import com.dcc.models.HealthReport;
import com.dcc.scanner.ProjectScanner;
import com.dcc.scanner.ProjectStats;

import javafx.concurrent.Task;
import javafx.stage.DirectoryChooser;
import com.google.gson.Gson;
public class JavaConnector {
	String path;
	Path path1;
	ProjectStats stats;
	CodeMetrics analysis;
	HealthReport healthReport;
	String projectType;
	public void projectPath() {

	    DirectoryChooser chooser = new DirectoryChooser();
	    chooser.setTitle("Select Project Directory");
	    chooser.setInitialDirectory(new File(System.getProperty("user.home")));

	    File dir = chooser.showDialog(null);

	    if (dir == null) {
	        return;
	    }

	    path = dir.getAbsolutePath();
	    path1 = Path.of(path);

	    Task<Void> task = new Task<>() {

	        @Override
	        protected Void call() throws Exception {

	            System.out.println("Background thread started");

	            ProjectScanner scanner = new ProjectScanner();

	            stats = scanner.scanProject(path1);

	            System.out.println("Scanner finished");

	            return null;
	        }
	    };

	    Thread thread = new Thread(task);
	    thread.setDaemon(true);
	    thread.start();

	    System.out.println("projectPath() returned immediately");
	}
	public int javaCount(){
	    return stats.getJavaCount();
	}
	public int htmlCount(){
	    return stats.getHtmlCount();
	}
	public int cssCount(){
	    return stats.getCssCount();
	}
	public int jsCount(){
	    return stats.getJsCount();
	}
	public int sqlCount(){
	    return stats.getSqlCount();
	}
	public int otherCount(){
	    return stats.getOtherCount();
	}
	public String filesInfo() {
		Gson gson=new Gson();
		String json=gson.toJson(stats.getFiles());
		return json;
	}
	public String projectType() {
		return projectType;
	}
	public String codeMetrics() {
		Gson gson=new Gson();
		String json=gson.toJson(analysis);
		return json;
	}
	public String healthReport() {
		Gson gson=new Gson();
		String json=gson.toJson(healthReport);
		return json;	
	}
	public void log(String message) {
	    System.out.println("JS: " + message);
	}
}
