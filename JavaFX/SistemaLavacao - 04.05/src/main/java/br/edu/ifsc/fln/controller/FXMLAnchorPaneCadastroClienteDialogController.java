/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.domain.ClienteA;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    private DatePicker dpDataNascimento;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfEndereco;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTelefone;
    
    private Stage dialogStage;
    private boolean btConfirmarClicked = false;
    private ClienteA clienteA;
    
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

    public ClienteA getCliente() {
        return clienteA;
    }

    public void setCliente(ClienteA clienteA) {
        this.clienteA = clienteA;
        this.tfNome.setText(this.clienteA.getNome());
        this.tfCpf.setText(this.clienteA.getCpf());
        this.tfTelefone.setText(this.clienteA.getTelefone());
        this.tfEndereco.setText(this.clienteA.getEndereco());
        dpDataNascimento.setValue(this.clienteA.getDataNascimento());
    }
    

    @FXML
    public void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            clienteA.setNome(tfNome.getText());
            clienteA.setCpf(tfCpf.getText());
            clienteA.setTelefone(tfTelefone.getText());
            clienteA.setEndereco(tfEndereco.getText());
            clienteA.setDataNascimento(dpDataNascimento.getValue());

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
        if (this.tfNome.getText() == null || this.tfNome.getText().length() == 0) {
            errorMessage += "Nome inválido.\n";
        }
        
        if (this.tfCpf.getText() == null || this.tfCpf.getText().length() == 0) {
            errorMessage += "CPF inválido.\n";
        }
        
        if (this.tfTelefone.getText() == null || this.tfTelefone.getText().length() == 0) {
            errorMessage += "Telefone inválido.\n";
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
