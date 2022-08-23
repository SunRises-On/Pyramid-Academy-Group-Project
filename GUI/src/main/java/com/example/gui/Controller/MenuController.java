package com.example.gui.Controller;


import com.example.gui.Model.FileModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

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

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void initialize(){

        model = new FileModel();
        ArrayList<String> listInit = model.getListInit();
        ObservableList<Data> list = getObservable(listInit);

        inputs.setCellValueFactory(new PropertyValueFactory<>("inputs"));

        inputs.setCellFactory(new Callback<TableColumn<Data, String>, TableCell<Data, String>>() {
            @Override
            public TableCell<Data, String> call(TableColumn<Data, String> param) {
                return new TableCell<Data, String>(){
                    @Override
                    public void updateItem(String item, boolean empty){
                        //super.updateItem(item, empty);
                        super.updateItem(item, empty);
                        setFont(Font.font("Consolas", 16));
                        setText(item);

                    }
                };
            }
        });




        table.setItems(list);
        inputs.setSortable(false); /* Disable Column sorting */



    }
    @FXML
    public void clickButtonTwo( ActionEvent event)throws IOException{
        System.out.println("Clicked button Two");
        ArrayList<String> listForm = model.getListFormat(2);
        ObservableList<Data> observableList = getObservable(listForm);
        observableList.forEach(System.out::println);
        switchScene(2, observableList, event);
    }
    @FXML
    public void clickButtonThree( ActionEvent event) throws IOException{
        System.out.println("Clicked button Three");
        ArrayList<String> listForm = model.getListFormat(3);
        ObservableList<Data> observableList = getObservable(listForm);
        observableList.forEach(System.out::println);
        switchScene(3, observableList, event);
    }

    /*****************************************************************
     * Possible function for handling a custom number of columns.
     ***************************************************************/
    @FXML
    public void clickButtonCustom( ActionEvent event)throws  IOException{

    }

    public void switchScene(int num,ObservableList<Data> list , ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sceneTwo.fxml"));
        root = loader.load();

        SceneTwoController controller = loader.getController();
        controller.setObservableList(list);
        controller.setColumnNum( num);
        controller.initialize();


        stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public ObservableList<Data> getObservable (ArrayList<String> list){

        ArrayList<Data> dataArrayList = list.stream().map(Data::new).collect(Collectors.toCollection(ArrayList::new));
       return FXCollections.observableArrayList(dataArrayList);
    }
}