package cn.vlinker.logclient.common;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Window {
    public static Stage dialogStage;

    public static void alert(String s, EventHandler<ActionEvent> e) {
        Button button = new Button("OK");
        Text text = new Text(s);
        text.autosize();
        VBox vbox = new VBox(text, button);
        vbox.setAlignment(Pos.BASELINE_CENTER);
        vbox.setPadding(new Insets(15));
        vbox.setPrefWidth(200);
        vbox.setPrefHeight(70);
        dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setScene(new Scene(vbox));
        dialogStage.setResizable(false);
        dialogStage.show();
        dialogStage.setAlwaysOnTop(true);
        button.setOnAction(e);
    }

    public static void alert(String s) {
        alert(s, e -> dialogStage.close());
    }
}
