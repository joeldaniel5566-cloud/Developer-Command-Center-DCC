package com.dcc;

import java.io.IOException;

import com.dcc.bridge.JavaConnector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import javafx.concurrent.Worker;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        WebView webView = new WebView();

        WebEngine webEngine = webView.getEngine();
        
        webEngine.setOnAlert(event -> {
            System.out.println("JavaScript Alert: " + event.getData());
        });
 
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {

                JSObject window = (JSObject) webEngine.executeScript("window");

                window.setMember("javaConnector", new JavaConnector());
            }
        });

        String url = getClass()
                .getResource("/ui/index.html")
                .toExternalForm();

        webEngine.load(url);
        
        Scene scene = new Scene(webView, 1200, 750);

        stage.setTitle("Developer Command Center");
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(650);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}