package cn.vlinker.logclient.panel;

import cn.vlinker.logclient.config.SizeConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("日报");
        primaryStage.setScene(new Scene(root, SizeConfig.MainW, SizeConfig.MainH));
        primaryStage.show();
        primaryStage.setResizable(false);
        stage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
