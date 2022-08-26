package com.example.gui.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static org.testfx.util.NodeQueryUtils.hasText;

/*****************************************************************************************
 * Tests using TestFX @https://github.com/TestFX/TestFX
 */
@ExtendWith(ApplicationExtension.class)
class MenuControllerTest {
    MenuController menuController;
    @Start
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        Parent root = loader.load();
        menuController = loader.getController();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    @Test
    public void containButtonWithText(){
        Button buttonTwo = menuController.buttonTwo;
        FxAssert.verifyThat(buttonTwo, hasText("2"));
        Button buttonThree = menuController.buttonThree;
       FxAssert.verifyThat(buttonThree, hasText("3"));
        Button custom = menuController.buttonCustom;
        FxAssert.verifyThat(custom, hasText("Custom"));
    }

}