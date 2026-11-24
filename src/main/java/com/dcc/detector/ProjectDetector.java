package com.dcc.detector;

import java.nio.file.Files;
import java.nio.file.Path;

public class ProjectDetector {
	public String detectProjectType(Path projectPath) {
		Path marker=projectPath.resolve("pom.xml");
		if(Files.exists(marker)) {
			return "Maven";
		}
		else {
			marker=projectPath.resolve("build.gradle");
			if(Files.exists(marker)) {
				return "Gradle";
			}
			else {
				marker=projectPath.resolve("package.json");
				if(Files.exists(marker)) {
					return "Node.js";
				}
				else {
					marker=projectPath.resolve("composer.json");
					if(Files.exists(marker)) {
						return "PHP Composer";
					}
					else {
						return "Unknown";
					}
				}
			}
		}
	}
}
