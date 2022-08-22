package com.example.gui.Controller;

import com.example.gui.Controller.Data;
import com.example.gui.Model.FileModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MenuController {
    FileModel model;
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

        model = new FileModel();

        ArrayList<String> listInit = model.getListInit();
        
        ArrayList<Data> dataArrayList = listInit.stream().map(Data::new).collect(Collectors.toCollection(ArrayList::new));
        //dataArrayList.forEach(System.out::println);
        ObservableList<Data> list = FXCollections.observableArrayList(dataArrayList);

        inputs.setCellValueFactory(new PropertyValueFactory<Data,String>("inputs"));
        table.setItems(list);
    }
    @FXML
    public void clickButtonTwo( ActionEvent event)throws IOException{
        System.out.println("Clicked button Two");
    }
    @FXML
    public void clickButtonThree( ActionEvent event) throws IOException{
        System.out.println("Clicked button Three");

    }

    /*****************************************************************
     * Possible function for handling a custom number of columns.
     ***************************************************************/
    @FXML
    public void clickButtonCustom( ActionEvent event)throws  IOException{

    }
}