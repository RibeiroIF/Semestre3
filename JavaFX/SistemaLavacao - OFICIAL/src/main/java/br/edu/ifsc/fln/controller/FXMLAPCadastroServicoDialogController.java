package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.domain.Servico;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author mpisc
 */
public class FXMLAPCadastroServicoDialogController implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btConfirmar;

    @FXML
    private TextField tfServicoDescricao;

    @FXML
    private TextField tfServicoValor;

    private Stage dialogStage;
    private boolean btConfirmarClicked = false;
    private Servico servico;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean isBtConfirmarClicked() {
        return btConfirmarClicked;
    }

    public void setBtConfirmarClicked(boolean btConfirmarClicked) {
        this.btConfirmarClicked = btConfirmarClicked;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
        tfServicoDescricao.setText(servico.getDescricao());
        tfServicoValor.setText(String.valueOf(servico.getValor()));
    }


    @FXML
    public void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            servico.setDescricao(tfServicoDescricao.getText());
            servico.setValor(Double.parseDouble(tfServicoValor.getText()));
            btConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleBtCancelar() {
        dialogStage.close();
    }

    //método para validar a entrada de dados
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        if (this.tfServicoDescricao.getText() == null || this.tfServicoDescricao.getText().length() == 0) {
            errorMessage += "Descrição inválida.\n";
        }

        if (this.tfServicoValor.getText() == null || this.tfServicoValor.getText().length() == 0) {
            errorMessage += "Valor inválido.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            //exibindo uma mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Corrija os campos inválidos!");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

}
