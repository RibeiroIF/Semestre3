package ifsc.atividade1.inicial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/ifsc/view/calculo-imc.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 340);
        stage.setTitle("Cálculo do IMC - FXML");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}