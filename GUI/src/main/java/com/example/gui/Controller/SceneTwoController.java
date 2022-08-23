package com.example.gui.Controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.util.Callback;

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
        table.setItems(dataList);
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
        inputs.setSortable(false); /* Disable Column sorting */
    }
    public void setObservableList( ObservableList<Data> list){

        this.dataList = list;

    }

    public void setColumnNum(int num ){
        this.colNum = num;
    }


}
