package cn.vlinker.logclient.controller;

import cn.vlinker.logclient.common.HttpCommon;
import cn.vlinker.logclient.common.Window;
import cn.vlinker.logclient.config.HttpConfig;
import cn.vlinker.logclient.config.SizeConfig;
import cn.vlinker.logclient.model.User;
import cn.vlinker.logclient.util.CodeUtil;
import com.alibaba.fastjson.JSONObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    @FXML
    private AnchorPane main;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField mac;

    @FXML
    private TextField code;

    @FXML
    private Button authCode;

    @FXML
    private Button register;

    @FXML
    private void initialize() {
        mac.setText(CodeUtil.getMACAddress());
    }


    @FXML
    private void getCodeEmail() {
        HttpCommon.GetRequest(new StringBuffer(HttpConfig.getCode), new HashMap() {{
            put("owner", "1");
            put("mac", mac.getText());
            put("email", email.getText());
        }});
        authCode.setDisable(true);
    }

    @FXML
    private void doRegister() {
        User user = new User();
        user.setEmail(email.getText());
        user.setMac(mac.getText());
        user.setName(name.getText());
        Map map = new HashMap() {{
            put("auth", code.getText());
        }};
        String string = HttpCommon.PostRequest(HttpConfig.host, HttpConfig.addUserPath, HttpConfig.port, map, user);
        JSONObject object = JSONObject.parseObject(string);
        if (object.getIntValue("code") == 200) {
            Window.alert("注册成功", e -> {
                Window.dialogStage.close();
                Parent mainFxml;
                try {
                    mainFxml = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
                    Scene scene = main.getScene();
                    scene.getWindow().setWidth(SizeConfig.MainW);
                    scene.getWindow().setHeight(SizeConfig.MainH);
                    scene.getWindow().centerOnScreen();
                    main.getChildren().setAll(mainFxml);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        } else {
            Window.alert("注册失败");
        }
    }

}
