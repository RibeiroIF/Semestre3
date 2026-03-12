module ifsc.atividade1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ifsc.atividade1.inicial to javafx.fxml;
    exports ifsc.atividade1.inicial;

    opens ifsc.atividade1.controller to javafx.fxml;
    exports ifsc.atividade1.controller;

}