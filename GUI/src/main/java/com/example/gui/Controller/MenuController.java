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
    private int minSize;
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
    @FXML
    public void initialize(){

        model = new FileModel();
        ArrayList<String> listInit = model.getListInit();
        ObservableList<Data> list = getObservable(listInit);
        minSize = list.size();
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

        createTextID();

    }

    /**********************************************************************************************
     * Create a TextInputDialog for custom number of columns from user. Attach to Custom button.
     * Disable okButton from TextInputDialog if user input is illegal in function isValid().
     * ********************************************************************************************/
    public void createTextID(){
        td = new TextInputDialog("");
        td.setHeaderText("Enter the number of columns.");
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
    public void clickButtonTwo()throws IOException{
        ArrayList<String> listForm = model.getListFormat(2);
        ObservableList<Data> observableList = getObservable(listForm);
        switchScene(2, observableList);
    }
    @FXML
    public void clickButtonThree() throws IOException{
        ArrayList<String> listForm = model.getListFormat(3);
        ObservableList<Data> observableList = getObservable(listForm);
        switchScene(3, observableList);
    }


    public void switchScene(int num,ObservableList<Data> list) throws IOException {
         Stage stage;
         Scene scene;
         Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sceneTwo.fxml"));
        root = loader.load();


        SceneTwoController controller = loader.getController();

        stage = (Stage) anchorPane.getScene().getWindow();

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

    /****************************************************************************************************************
     * This function is used validate user input. User can only input a number between (1,Integer Max Value).
     * If user inputs custom value greater than number of items, the max number of columns will default to
     * number of items.
     * @param s = User input for custom number of columns.
     * @return = true if valid request.
     ****************************************************************************************************************/
    public boolean isValid(String s){
        if(s.isEmpty()){
            return false;
        }else {
            try {
                if(s.length() > 10){
                    setAlertMaxNumber();
                    return false;
                }
                else{
                    double num2 = Double.parseDouble(s);
                    if(num2 > Integer.MAX_VALUE) {
                        setAlertMaxNumber();
                        return false;

                    }else {

                        numCol= Integer.parseInt(s);

                        if (numCol == 0) {
                            inputField.setText("");
                            return false;
                        }
                        if (numCol > minSize) {
                            numCol = minSize;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                setAlertLetters();
                inputField.setText("");
                return false;
            }catch (IllegalArgumentException e){
                return false;
            }
        }
        return true;
    }
    public void setAlertLetters(){
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Enter Only Numbers");
        a.show();
    }
    public void setAlertMaxNumber(){
        Alert b = new Alert(Alert.AlertType.INFORMATION);
            b.setContentText("Greater Than Max Number of Columns Entered.");
            b.show();
    }
    public void customSwitchEvent(ObservableList<Data> list ) throws IOException {

        switchScene(numCol, list);
    }
    public void printObservableDataList(ObservableList<Data> observableList){
        observableList.forEach(System.out::println);
    }
}