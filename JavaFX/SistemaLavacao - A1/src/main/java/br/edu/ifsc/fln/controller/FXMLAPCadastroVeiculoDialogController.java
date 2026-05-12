/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.ClienteDAO;
import br.edu.ifsc.fln.model.dao.MarcaDAO;
import br.edu.ifsc.fln.model.dao.ModeloDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Cliente;
import br.edu.ifsc.fln.model.domain.Marca;
import br.edu.ifsc.fln.model.domain.Modelo;
import br.edu.ifsc.fln.model.domain.Veiculo;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mpisc
 */
public class FXMLAPCadastroVeiculoDialogController implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btConfirmar;
    
    @FXML
    private TextField tfPlaca;

    @FXML
    private ComboBox<Marca> cbMarca;

    @FXML
    private ComboBox<Modelo> cbModelo;

    @FXML
    private ComboBox<Modelo> cbCliente;

    private List<Marca> listaMarcas;
    private ObservableList<Marca> observableListMarcas;

    private List<Modelo> listaModelos;
    private ObservableList<Modelo> observableListModelos;

    private List<Cliente> listaClientes;
    private ObservableList<Cliente> observableListClientes;

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private Stage dialogStage;
    private boolean btConfirmarClicked = false;
    private Veiculo veiculo;
    private final ModeloDAO modeloDAO = new ModeloDAO();
    private final MarcaDAO marcaDAO = new MarcaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modeloDAO.setConnection(connection);
        marcaDAO.setConnection(connection);
        clienteDAO.setConnection(connection);
        carregarComboBoxMarcas();
        carregarComboBoxModelos();
        carregarComboBoxClientes();
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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        if (veiculo.getId() != 0) {
            this.tfPlaca.setText(this.veiculo.getPlaca());
        }
        this.tfPlaca.requestFocus();
    }

    public void carregarComboBoxModelos() {
        listaModelos = modeloDAO.listar();
        observableListMarcas =
                FXCollections.observableArrayList(listaMarcas);
        cbModelo.setItems(observableListModelos);
    }

    public void carregarComboBoxMarcas() {
        listaMarcas = marcaDAO.listar();
        observableListMarcas =
                FXCollections.observableArrayList(listaMarcas);
        cbMarca.setItems(observableListMarcas);
    }

    public void carregarComboBoxClientes() {
        listaClientes = clienteDAO.listar();
        observableListClientes =
                FXCollections.observableArrayList(listaClientes);
        cbMarca.setItems(observableListMarcas);
    }

    @FXML
    public void handleBtConfirmar() {
        if (validarEntradaDeDados()) {
            veiculo.setPlaca(tfPlaca.getText());
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
        if (this.tfPlaca.getText() == null || this.tfPlaca.getText().length() == 0) {
            errorMessage += "Placa inválido.\n";
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