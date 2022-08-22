module com.example.gui {
    requires javafx.controls;
    requires javafx.fxml;


  //  opens com.example.gui to javafx.fxml;
  //  exports com.example.gui;
  //  exports com.example.gui.Controller;
   // opens com.example.gui.Controller to javafx.fxml;
    opens com.example.gui.Controller to javafx.fxml;
    exports com.example.gui.Controller;
}