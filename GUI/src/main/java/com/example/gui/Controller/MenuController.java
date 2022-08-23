package com.example.gui.Controller;


import com.example.gui.Model.FileModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MenuController {
    private int numCol ;

    FileModel model;
    TextField inputField;
    TextInputDialog td;
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
    @FXML
    AnchorPane anchorPane;
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
                        setFont(Font.font("Consolas", 14));
                        setText(item);

                    }
                };
            }
        });

        table.setItems(list);
        inputs.setSortable(false); /* Disable Column sorting */

        /***********************************************************************
         * Create a TextInputDialog  to popup and Handle Custom Number of Columns.
         * ***********************************************************************/
         td = new TextInputDialog("");
        td.setHeaderText("Enter the number of columns.");
       // Button okButton = (Button) td.getDialogPane().lookupButton(ButtonType.OK);
        inputField = td.getEditor();

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // td.showAndWait();
                Button okButton = (Button) td.getDialogPane().lookupButton(ButtonType.OK);
                BooleanBinding isInvalid = Bindings.createBooleanBinding(()-> !isValid (inputField.getText()), inputField.textProperty());
                okButton.disableProperty().bind(isInvalid);
                okButton.setOnAction(e-> {
                    try {
                        ArrayList<String> listForm = model.getListFormat(numCol);
                        ObservableList<Data> observableList = getObservable(listForm);
                        customSwitchEvent(observableList);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                td.showAndWait();
            }
        };

        buttonCustom.setOnAction(event);
    }
    @FXML
    public void clickButtonTwo( ActionEvent event)throws IOException{
        ArrayList<String> listForm = model.getListFormat(2);
        ObservableList<Data> observableList = getObservable(listForm);
        //observableList.forEach(System.out::println);
        switchScene(2, observableList, event);
    }
    @FXML
    public void clickButtonThree( ActionEvent event) throws IOException{
        ArrayList<String> listForm = model.getListFormat(3);
        ObservableList<Data> observableList = getObservable(listForm);
        switchScene(3, observableList, event);
    }

    @FXML
    public void clickButtonCustom( ActionEvent event)throws  IOException{

    }

    public void switchScene(int num,ObservableList<Data> list , ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sceneTwo.fxml"));
        root = loader.load();


        SceneTwoController controller = loader.getController();

        stage = (Stage) anchorPane.getScene().getWindow();


        int len =  list.get(0).toString().length();

        controller.setNumberSpaces(len);
        controller.setObservableList(list);
        controller.setColumnNum( num);
        controller.initialize();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public ObservableList<Data> getObservable (ArrayList<String> list){

        ArrayList<Data> dataArrayList = list.stream().map(Data::new).collect(Collectors.toCollection(ArrayList::new));
       return FXCollections.observableArrayList(dataArrayList);
    }
    public boolean isValid(String s){
        System.out.println("in isValid");
        System.out.println(s);
        if(s.isEmpty()){
            System.out.println("hello");
            return false;
        }else {
            try {
                int num = Integer.parseInt(s);
                numCol = num;
                System.out.println("numCol = " + numCol);
            } catch (NumberFormatException e) {
                setAlert();
                inputField.setText("");
                return false;
            }
        }
        return true;
    }
    public void setAlert(){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Enter Only Numbers");
        a.show();
    }
    public void customSwitchEvent(ObservableList<Data> list ) throws IOException {

        switchScene(numCol, list, new ActionEvent());
    }
}