/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.ModeloDAO;
import br.edu.ifsc.fln.model.dao.MotorDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.*;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mpisching
 */
public class FXMLAPCadastroModeloDialogController implements Initializable {
    
    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfMarca;

    @FXML
    private ChoiceBox cbCombustivel;

    @FXML
    private ChoiceBox cbCategoria;

    @FXML
    private TextField tfMotor;

    @FXML
    private Button btConfirmar;

    @FXML
    private Button btCancelar;

    private List<Modelo> listaCategorias;
    private ObservableList<Modelo> observableListCategorias;

    private List<Motor> listaCombustiveis;
    private ObservableList<Motor> observableListCombustiveis;

    //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();

    private final MotorDAO motorDAO = new MotorDAO();
    private final ModeloDAO modeloDAO = new ModeloDAO();

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Modelo modelo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modeloDAO.setConnection(connection);
        carregarChoiceBoxCategorias();

        motorDAO.setConnection(connection);
        carregarChoiceBoxCombustiveis();

        setFocusLostHandle();
    }

    private void setFocusLostHandle() {
        tfDescricao.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) { // focus lost
                if (tfDescricao.getText() == null || tfDescricao.getText().isEmpty()) {
                    //System.out.println("teste focus lost");
                    tfDescricao.requestFocus();
                }
            }
        });
    }

//This works fine too:    
//root.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//    focusState(newValue);
//});
//
//private void focusState(boolean value) {
//    if (value) {
//        System.out.println("Focus Gained");
//    }
//    else {
//        System.out.println("Focus Lost");
//    }
//}

    public void carregarChoiceBoxCategorias() {
        cbCategoria.setItems(FXCollections.observableArrayList(ECategoria.values()));
        cbCategoria.getSelectionModel().select(0);
//        listaCategorias = modeloDAO.listarCategorias();
//        observableListCategorias =
//                FXCollections.observableArrayList(listaCategorias);
//        cbCategoria.setItems(observableListCategorias);
    }

    public void carregarChoiceBoxCombustiveis() {
        cbCombustivel.setItems(FXCollections.observableArrayList(ETipoCombustivel.values()));
        cbCombustivel.getSelectionModel().select(0);
//        listaCombustiveis = motorDAO.listarPorCombustivel();
//        observableListCombustiveis =
//                FXCollections.observableArrayList(listaCombustiveis);
//        cbCombustivel.setItems(observableListCombustiveis);
    }

    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return the buttonConfirmarClicked
     */
    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    /**
     * @param buttonConfirmarClicked the buttonConfirmarClicked to set
     */
    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    /**
     * @return the modelo
     */
    public Modelo getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
        Marca marca = new Marca();
        this.modelo.setMarca(marca);
        tfDescricao.setText(modelo.getDescricao());
        tfMarca.setText(modelo.getMarca().getNome());
        cbCategoria.getSelectionModel().select(this.modelo.getCategoria());
        tfMotor.setText(String.valueOf(modelo.getMotor().getPotencia()));
        cbCombustivel.getSelectionModel().select(this.modelo.getMotor().getTipoCombustivel());
    }

    @FXML
    private void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            modelo.setDescricao(tfDescricao.getText());
            modelo.setCategoria((ECategoria) cbCategoria.getSelectionModel().getSelectedItem());
            modelo.getMarca().setNome(tfMarca.getText());
            modelo.getMotor().setPotencia(Integer.parseInt(tfMotor.getText()));
            modelo.getMotor().setTipoCombustivel((ETipoCombustivel) cbCombustivel.getSelectionModel().getSelectedItem());
            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleBtCancelar() {
        dialogStage.close();
    }

    //validar entrada de dados do cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (tfDescricao.getText() == null || tfDescricao.getText().isEmpty()) {
            errorMessage += "Descricao inválido!\n";
        }

        if (cbCategoria.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Selecione uma categoria!\n";
        }

        if (tfMarca.getText() == null) {
            errorMessage += "Selecione uma marca!\n";
        }

        if (tfMotor.getText() == null) {
            errorMessage += "Selecione um motor!\n";
        }

        if (cbCombustivel.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Selecione um tipo de combustível!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campo(s) inválido(s), por favor corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

}
