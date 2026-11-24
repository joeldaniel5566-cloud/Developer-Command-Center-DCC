package com.dcc.analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.dcc.models.CodeMetrics;
import com.dcc.models.HealthReport;
import com.dcc.scanner.ProjectStats;

public class HealthAnalyzer {
	private CodeAnalyzer analyzer=new CodeAnalyzer();
	int count;
	ProjectStats stats;
	
	List<String> largeFiles = new ArrayList<>();
	List<String> lowCommentFiles = new ArrayList<>();
	List<String> manyMethodFiles = new ArrayList<>();
	
	boolean isLargeFile(Path path) {
		return analyzer.countLines(path)>500;
	}
	
	boolean lessComments(Path path) {
		CodeMetrics metrics=analyzer.analyzeFile(path);
		double min=(0.1)*metrics.getTotalLines();
		boolean result = metrics.getCommentLines() < min;
		return result;
	}
	
	boolean manyMethods(Path path) {
		count=0;
		List<String> lines;
		try {
			lines = Files.readAllLines(path);
			lines.forEach(line->{
				if ((line.contains("public ")||line.contains("private "))&&line.contains("(")){
					count++;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count>15;
	}
	
	public void setStats(ProjectStats stats) {
	    this.stats = stats;
	}
	
	public HealthReport analyzeProject(Path projectPath) {
		largeFiles.clear();
		lowCommentFiles.clear();
		manyMethodFiles.clear();
		int healthScore=0;
		
	    try (Stream<Path> paths=Files.walk(projectPath)){
	        
	        paths.filter(path -> Files.isRegularFile(path))
	            .forEach(path -> {
	                if (analyzer.isSourceFile(path)) {
	                	if (isLargeFile(path)) {
	                        largeFiles.add(path.getFileName().toString());
	                    }
	                	if (lessComments(path)) {
	                        lowCommentFiles.add(path.getFileName().toString());
	                    }
	                	if (manyMethods(path)) {
	                        manyMethodFiles.add(path.getFileName().toString());
	                    }
	                }
	            });

			HealthReport report=new HealthReport(largeFiles,lowCommentFiles,manyMethodFiles,0);
	        healthScore= calculateHealthScore(report);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return new HealthReport(
	        largeFiles,
	        lowCommentFiles,
	        manyMethodFiles,
	        healthScore
	    );
	}
	
	public int calculateHealthScore(HealthReport report) {
		int total= stats.getJavaCount()+stats.getHtmlCount()+stats.getCssCount()+stats.getJsCount()+stats.getSqlCount();
		if(total==0) {
			return 0;
		}
		double largeper=(double) report.getLargeFiles().size()/total;
		double commentper=(double) report.getLowCommentFiles().size()/total;
		double methodper=(double) report.getManyMethodFiles().size()/total;
		double totalissuesper=(largeper*40)+(commentper*30)+(methodper*30);
		int healthScore=(int) (100-totalissuesper);
		healthScore=Math.max(0, healthScore);
		return healthScore;
	}
}
