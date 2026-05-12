/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.domain.Cliente;
import br.edu.ifsc.fln.model.domain.PessoaJuridica;
import br.edu.ifsc.fln.model.domain.PessoaFisica;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mpisc
 */
public class FXMLAnchorPaneCadastroClienteDialogController implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btConfirmar;

    @FXML
    private RadioButton rbPessoaJuridica;

    @FXML
    private RadioButton rbPessoaFisica;

    @FXML
    private Group gbTipo;

    @FXML
    private ToggleGroup tgTipo;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfCelular;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfNumFiscal;

    @FXML
    private TextField tfInscricaoEstadual;

    private Stage dialogStage;
    private boolean btConfirmarClicked = false;
    private Cliente cliente;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        if (cliente.getId() != 0) {
            this.tfNome.setText(this.cliente.getNome());
            this.tfEmail.setText(this.cliente.getEmail());
            this.tfCelular.setText(this.cliente.getCelular());
            this.gbTipo.setDisable(true);
            if (cliente instanceof PessoaFisica) {
                rbPessoaFisica.setSelected(true);
                tfNumFiscal.setText(((PessoaFisica) this.cliente).getCpf());
                tfInscricaoEstadual.setText("BRASIL");
                tfInscricaoEstadual.setDisable(true);
            } else {
                rbPessoaJuridica.setSelected(true);
                tfNumFiscal.setText(((PessoaJuridica) this.cliente).getCnpj());
                tfInscricaoEstadual.setText(((PessoaJuridica) this.cliente).getInscricaoEstadual());
                tfInscricaoEstadual.setDisable(false);
            }
        }
        this.tfNome.requestFocus();
    }

    @FXML
    public void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            cliente.setNome(tfNome.getText());
            cliente.setEmail(tfEmail.getText());
            cliente.setCelular(tfCelular.getText());
            if (rbPessoaFisica.isSelected()) {
                ((PessoaFisica) cliente).setCpf(tfNumFiscal.getText());
            } else {
                ((PessoaJuridica) cliente).setCnpj(tfNumFiscal.getText());
                ((PessoaJuridica) cliente).setInscricaoEstadual(tfInscricaoEstadual.getText());
            }
            btConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleBtCancelar() {
        dialogStage.close();
    }

    @FXML
    public void handleRbPessoaFisica() {
        this.tfInscricaoEstadual.setText("BRASIL");
        this.tfInscricaoEstadual.setDisable(true);
    }

    @FXML
    public void handleRbPessoaJuridica() {
        this.tfInscricaoEstadual.setText("");
        this.tfInscricaoEstadual.setDisable(false);
    }

    //método para validar a entrada de dados
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        if (this.tfNome.getText() == null || this.tfNome.getText().length() == 0) {
            errorMessage += "Nome inválido.\n";
        }

        if (this.tfCelular.getText() == null || this.tfCelular.getText().length() == 0) {
            errorMessage += "Telecelular inválido.\n";
        }

        if (this.tfEmail.getText() == null || this.tfEmail.getText().length() == 0 || !this.tfEmail.getText().contains("@")) {
            errorMessage += "Email inválido.\n";
        }

        if (rbPessoaFisica.isSelected()) {
            if (this.tfNumFiscal.getText() == null || this.tfNumFiscal.getText().length() == 0) {
                errorMessage += "CNPJ inválido.\n";
            }
        } else {
            if (this.tfNumFiscal.getText() == null || this.tfNumFiscal.getText().length() == 0) {
                errorMessage += "NIF inválido.\n";
            }
            if (this.tfInscricaoEstadual.getText() == null || this.tfInscricaoEstadual.getText().length() == 0) {
                errorMessage += "Informe o nome do País.\n";
            }
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
