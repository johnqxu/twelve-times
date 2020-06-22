package xyz.pinjiajia.twelvetimes;

import com.sun.tools.javac.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import xyz.pinjiajia.twelvetimes.controller.MainController;

import java.io.IOException;
import java.net.URL;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {
    private final String applicationTitle;
    private final Resource fxml;
    private final ApplicationContext ac;

    StageListener(@Value("${spring.application.ui.title}") String applicationTitle,
                  @Value("classpath:/xyz/pinjiajia/twelvetimes/ui/main.fxml") Resource resource,
                  ApplicationContext ac) {
        this.applicationTitle = applicationTitle;
        this.fxml = resource;
        this.ac = ac;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        try {
            Stage stage = stageReadyEvent.getStage();
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(ac::getBean);
            Parent root = fxmlLoader.load();
            MainController mc = (MainController) fxmlLoader.getController();
            mc.setApplicationContext(ac);
            Scene sence = new Scene(root);
            sence.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(sence);
            stage.setTitle(this.applicationTitle);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}