package cn.vlinker.logclient.controller;

import cn.vlinker.logclient.common.HttpCommon;
import cn.vlinker.logclient.common.Window;
import cn.vlinker.logclient.config.HttpConfig;
import cn.vlinker.logclient.config.SizeConfig;
import cn.vlinker.logclient.decorate.DateDecorate;
import cn.vlinker.logclient.model.Log;
import cn.vlinker.logclient.model.User;
import cn.vlinker.logclient.util.CodeUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {

    @FXML
    private AnchorPane main;

    @FXML
    private ChoiceBox<String> userList;

    @FXML
    private DatePicker date;

    @FXML
    private TextField project;

    @FXML
    private TextArea works;

    @FXML
    private Button addUser;

    private static User user;

    @FXML
    private void initialize() {
        String response = HttpCommon.GetRequest(new StringBuffer(HttpConfig.getUser), new HashMap() {{
            put("mac", CodeUtil.getMACAddress());
        }});
        JSONObject data = JSONObject.parseObject(response);
        if (isNotEmpty(response, data)) {
            JSONObject object = data.getJSONObject("data");
            user = object.toJavaObject(User.class);
            userList.setItems(FXCollections.observableArrayList(object.getString("name")));
            userList.setValue(object.getString("name"));
            main.getChildren().remove(addUser);
        } else {
            userList.setItems(FXCollections.observableArrayList("添加用户"));
            userList.setValue("添加用户");
        }
        date.setValue(LocalDate.now());
    }

    @FXML
    private void add() throws IOException {
        Parent user = FXMLLoader.load(getClass().getResource("/fxml/user.fxml"));
        Scene scene = main.getScene();
        scene.getWindow().setWidth(SizeConfig.UserW);
        scene.getWindow().setHeight(SizeConfig.UserH);
        scene.getWindow().centerOnScreen();
        main.getChildren().setAll(user);
    }

    @FXML
    private void subLog() {
        Log log = new Log();
        log.setDay(date.getValue());
        log.setName(user.getName());
        log.setProject(project.getText());
        log.setWork(works.getText());
        String data = HttpCommon.PostRequest(HttpConfig.host, HttpConfig.subLog, HttpConfig.port, null, log);
        JSONObject object = JSONObject.parseObject(data);
        if (object != null && object.getIntValue("code") == 200) {
            Window.alert("提交成功!", e -> {
                works.setText("");
                Window.dialogStage.close();
            });
        } else {
            Window.alert("提交失败,请检查提交内容或者联系管理员.");
        }
    }

    @FXML
    private void reload() {
        String response = HttpCommon.GetRequest(new StringBuffer(HttpConfig.getDate), new HashMap() {{
            put("name", user.getName());
        }});
        JSONObject data = JSONObject.parseObject(response);
        if (isNotEmpty(response, data)) {
            JSONArray array = data.getJSONArray("data");
            List<Map> dates = array.toJavaList(Map.class);
            date.setDayCellFactory(new DateDecorate(dates));
        }
    }

    private boolean isNotEmpty(String response, JSONObject data) {
        return response != null && !"".equals(response) && data.getIntValue("code") == 200 && data.get("data") != null;
    }
}
