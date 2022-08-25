package com.example.gui.Controller;


import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

// implement a minimum size for column text width()!!!
//set that in autoResizeColumns
public class SceneTwoController {
    @FXML
    private AnchorPane pane;
    @FXML
    private Button buttonBack;
    @FXML
    private Label label;
    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, String> inputs;
    private int colNum;
    private static String font = "Consolas";
    private static int fontSize = 14;
    private static int numItems;
    private static double columnHeight;
    private static double maxColumnWidth;
    private ObservableList<Data> dataList;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void initialize(){
        String header= "Output for Input with "+ colNum + " columns";

        label.setText(header);
        table.setItems(dataList);


        inputs.setCellValueFactory(new PropertyValueFactory<>("inputs"));

        inputs.setCellFactory(new Callback<TableColumn<Data, String>, TableCell<Data, String>>() {
            @Override
            public TableCell<Data, String> call(TableColumn<Data, String> param) {

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

        inputs.setSortable(false); /* Disable Column sorting */

         //   inputs.prefWidthProperty().bind(table.widthProperty());
    }

    @FXML
    public void clickButtonBack(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        root = loader.load();

        stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setObservableList( ObservableList<Data> list){

        this.dataList = list;
        initialize();
        numItems = list.size();
        createListener();
        autoResizeColumns(table);

    }


    public void setColumnNum(int num ){
        this.colNum = num;
    }

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
            maxColumnWidth = max+10.d;
            columnHeight = text.getLayoutBounds().getHeight();


        } );
    }

    /**************************************************************************************************
     * Create listener for AnchorPanel height and width, so that when the window is resized. The
     * max Table Height and width changes too.
     **/

    public void createListener(){
        InvalidationListener listener = new InvalidationListener(){
            @Override
            public void invalidated(Observable o){

                double paneWN = pane.getWidth();
                double paneHN = pane.getHeight();

                table.setMaxSize(paneWN-200, paneHN-160);

                if(maxColumnWidth < paneWN-200){
                    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                }else{
                    table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
               }

            }
        };
        pane.widthProperty().addListener(listener);
        pane.heightProperty().addListener(listener);
    }
    public void tableHeightHelper(TableView<?> table, int rowHeight, int headerHeight, int margin) {
        table.prefHeightProperty().bind(Bindings.max(2, Bindings.size(table.getItems()))
                .multiply(rowHeight)
                .add(headerHeight)
                .add(margin));
        table.minHeightProperty().bind(table.prefHeightProperty());
        table.maxHeightProperty().bind(table.prefHeightProperty());
    }

}


