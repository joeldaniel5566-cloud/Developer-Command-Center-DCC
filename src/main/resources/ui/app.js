function startScan() {

    alert("Before projectPath");

    window.javaConnector.projectPath();

    alert("After projectPath");
}

function formatFileSize(bytes) {
    if (bytes < 1024) {
        return bytes + " B";
    }

    if (bytes < 1024 * 1024) {
        return (bytes / 1024).toFixed(2) + " KB";
    }

    if (bytes < 1024 * 1024 * 1024) {
        return (bytes / (1024 * 1024)).toFixed(2) + " MB";
    }

    return (bytes / (1024 * 1024 * 1024)).toFixed(2) + " GB";
}

function getHealthStatus(score){
	if(score>=80){
		return "Excellent";
	}
	else if(score>=60){
		return "Good";
	}
	else if(score>=40){
		return "Need Attention";
	}
	else{
		return "Critical";
	}
}
