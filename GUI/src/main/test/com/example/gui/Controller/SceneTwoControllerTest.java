package com.example.gui.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
class SceneTwoControllerTest{
    SceneTwoController controller;
        @Start
        public void start(Stage primaryStage) throws Exception{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sceneTwo.fxml"));
            Parent root = loader.load();
            controller = loader.getController();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        @Test
        public void containButtonWithText(){
            Button b = controller.getButtonBack();
            FxAssert.verifyThat(b, hasText("<--"));
        }
        @Test
        public void labelContainsColNumText0(){
            Label l = controller.getLabel();
            FxAssert.verifyThat(l, hasText("Output for Input with 0 columns"));
        }



}