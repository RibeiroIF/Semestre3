package ifsc.atividade1.controller;

import ifsc.atividade1.model.domain.Pessoa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class IMCController implements Initializable {

    @FXML
    private TextField insertNome;

    @FXML
    private Spinner<Integer> insertIdade;

    @FXML
    private ChoiceBox<String> insertSexo;

    private String sexo;
    private String[] opcoesSexo = {"Masculino", "Feminino"};

    @FXML
    private TextField insertAltura;

    @FXML
    private TextField insertPeso;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        insertSexo.getItems().addAll(opcoesSexo);
        SpinnerValueFactory<Integer> idades = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);

        idades.setValue(1);
        insertIdade.setValueFactory(idades);
    }

    @FXML
    void calcularIMC(ActionEvent event) {
        sexo = insertSexo.getValue();
        double altura = Double.parseDouble(insertAltura.getText());
        double peso = Double.parseDouble(insertPeso.getText());

        Pessoa pessoa = new Pessoa(
                this.insertNome.getText(),
                this.insertIdade.getValue(),
                this.insertSexo.getValue(),
                altura, peso);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultados do paciente "+this.insertNome.getText());
        alert.setHeaderText("Cálculo do IMC");
        alert.setContentText(pessoa.mostrarDados());
        alert.show();
    }

    @FXML
    void limparCampos(ActionEvent event) {
        this.insertNome.clear();
        this.insertIdade.getValueFactory().setValue(0);
        this.insertSexo.setValue(null);
        this.insertAltura.clear();
        this.insertPeso.clear();
    }
}
