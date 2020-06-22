package xyz.pinjiajia.twelvetimes.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class MainController {

    private ApplicationContext ac;

    public void setApplicationContext(ApplicationContext ac) {
        this.ac = ac;
    }

    ;

    @FXML
    public Label lblAppTitle;

    @FXML
    public Button btnClose;

    @FXML
    public Button btnMin;

    @FXML
    public Button btnChinese;

    @FXML
    public Button btnEnglish;

    @FXML
    public Button btnMath;

    @FXML
    public void initialize() {
        this.lblAppTitle.setText("日拱一卒");
        this.btnClose.setOnAction(actionEvent -> {
            Platform.exit();
            System.exit(0);
        });
        this.btnMin.setOnAction(actionEvent -> {
            ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).setIconified(true);
        });

        this.btnChinese.setOnAction(actionEvent -> {
            Stage stage = (Stage) (((Button)actionEvent.getSource()).getScene().getWindow());
            Resource fxml = ac.getResource("classpath:/xyz/pinjiajia/twelvetimes/ui/chineseKnowledges.fxml");
            URL url = null;
            try {
                url = fxml.getURL();
                FXMLLoader fxmlLoader = new FXMLLoader(url);
                fxmlLoader.setControllerFactory(ac::getBean);
                Parent root = fxmlLoader.load();
                Scene sence = new Scene(root);
                stage.setScene(sence);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        this.btnMath.setOnAction(actionEvent -> {
        });

        this.btnEnglish.setOnAction(actionEvent -> {
        });
    }
}
