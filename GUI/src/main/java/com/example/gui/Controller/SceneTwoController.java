package com.example.gui.Controller;

import com.example.gui.Controller.Data;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SceneTwoController {
    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, String> inputs;
    private int colNum;
    private ObservableList<Data> dataList;

    @FXML
    public void initialize(){
        inputs.setText("Output for Input with "+ colNum + " columns");
        inputs.setCellValueFactory(new PropertyValueFactory<Data,String>("inputs"));
        table.setItems(dataList);
        inputs.setSortable(false); /* Disable Column sorting */
    }
    public void setObservableList( ObservableList<Data> list){

        this.dataList = list;

    }

    public void setColumnNum(int num ){
        this.colNum = num;
    }
}
