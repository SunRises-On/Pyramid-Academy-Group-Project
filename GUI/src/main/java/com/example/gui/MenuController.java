package com.example.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController {
    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, String> inputs;

    @FXML
    ToolBar tool;
    @FXML
    Button buttonTwo;
    @FXML
    Button buttonThree;
    @FXML
    Button buttonCustom;
    ObservableList<Data> list = FXCollections.observableArrayList(
            new Data ("one"),
            new Data ("two"),
            new Data ("three")
    );
    @FXML
    public void initialize(){
        
        inputs.setCellValueFactory(new PropertyValueFactory<Data,String>("inputs"));
        table.setItems(list);
    }
}