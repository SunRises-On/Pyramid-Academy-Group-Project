package com.example.gui.Controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.stream.Collectors;

// implement a minimum size for column text width()!!!
//set that in autoResizeColumns
public class SceneTwoController {
    @FXML
    private Label label;
    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, String> inputs;
    private int colNum;
    private int numberSpaces;
    private static String font = "Consolas";
    private static int fontSize = 14;
    private static int numItems;
    private ObservableList<Data> dataList;

    @FXML
    public void initialize(){
        String header= "Output for Input with "+ colNum + " columns";

        label.setText(header);
        table.setItems(dataList);


        inputs.setCellValueFactory(new PropertyValueFactory<>("inputs"));

        inputs.setCellFactory(new Callback<TableColumn<Data, String>, TableCell<Data, String>>() {
            @Override
            public TableCell<Data, String> call(TableColumn<Data, String> param) {
                //TableCell<Data, String> cell = new TableCell<>();
                // cell.setTextOverrun( OverrunStyle.valueOf());
                return new TableCell<Data, String>(){
                    @Override
                    public void updateItem(String item, boolean empty){
                        //super.updateItem(item, empty);
                        if(item == null || empty){
                            setText(null);
                        } else {
                            super.updateItem(item, empty);
                            setFont(Font.font(font, fontSize));
                        }
                            setText(item);

                    }
                };
            }
        });

        table.setItems(dataList);
        
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        inputs.setSortable(false); /* Disable Column sorting */


    }
    public void setObservableList( ObservableList<Data> list){

        this.dataList = list;
        initialize();
        numItems = list.size();
        autoResizeColumns(table);

    }

    public void setColumnNum(int num ){
        this.colNum = num;
    }
    public void setNumberSpaces(int num){ this.numberSpaces = num;}
    public static void autoResizeColumns( TableView<?> table )
    {
        //Set the right policy
        table.setColumnResizePolicy( TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.getColumns().stream().forEach( (column) ->
        {
            //Minimal width = columnheader
            Text text = new Text( column.getText() );
            double max = text.getLayoutBounds().getWidth();
            System.out.println("max width of text = "+max);
            for ( int i = 0; i <= numItems-1; i++ )
            {
                //cell must not be empty
                if ( column.getCellData( i ) != null )
                {
                    text = new Text( column.getCellData( i ).toString());
                    text.setFont(Font.font(font,fontSize));
                    System.out.println("hello in autoresize");
                    System.out.println(text.toString());;
                    double calcWidth = text.getLayoutBounds().getWidth();
                    System.out.println("Calc width = "+calcWidth);
                    //remember new max-width
                    if ( calcWidth > max )
                    {
                        max = calcWidth;
                    }
                }
            }
            //set the new max-widht with some extra space
            column.setPrefWidth( max + 10.0d );
        } );
    }
}
