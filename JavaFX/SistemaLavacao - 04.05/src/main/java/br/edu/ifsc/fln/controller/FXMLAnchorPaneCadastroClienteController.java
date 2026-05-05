/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.ClienteDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.ClienteA;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mpisc
 */
public class FXMLAnchorPaneCadastroClienteController implements Initializable {

    
    @FXML
    private Button btAlterar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btInserir;

    @FXML
    private Label lbClienteId;

    @FXML
    private Label lbClienteCPF;

    @FXML
    private Label lbClienteDataNascimento;

    @FXML
    private Label lbClienteNome;

    @FXML
    private Label lbClienteTelefone;

    @FXML
    private Label lbClienteEndereco;
    
    @FXML
    private TableColumn<ClienteA, String> tableColumnClienteCPF;

    @FXML
    private TableColumn<ClienteA, String> tableColumnClienteNome;

    @FXML
    private TableView<ClienteA> tableViewClientes;

    
    private List<ClienteA> listaClienteAS;
    private ObservableList<ClienteA> observableListClienteAS;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteDAO.setConnection(connection);
        carregarTableViewCliente();
        
        tableViewClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue));
    }     
    
    public void carregarTableViewCliente() {
        tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnClienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        listaClienteAS = clienteDAO.listar();
        
        observableListClienteAS = FXCollections.observableArrayList(listaClienteAS);
        tableViewClientes.setItems(observableListClienteAS);
    }
    
    public void selecionarItemTableViewClientes(ClienteA clienteA) {
        if (clienteA != null) {
            lbClienteId.setText(String.valueOf(clienteA.getId()));
            lbClienteNome.setText(clienteA.getNome());
            lbClienteCPF.setText(clienteA.getCpf());
            lbClienteTelefone.setText(clienteA.getTelefone());
            lbClienteEndereco.setText(clienteA.getEndereco());
            lbClienteDataNascimento.setText(String.valueOf(
                    clienteA.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        } else {
            lbClienteId.setText(""); 
            lbClienteNome.setText("");
            lbClienteCPF.setText("");
            lbClienteTelefone.setText("");
            lbClienteEndereco.setText("");
            lbClienteDataNascimento.setText("");
        }
        
    }
    
    @FXML
    public void handleBtInserir() throws IOException {
        ClienteA clienteA = new ClienteA();
        boolean btConfirmarClicked = showFXMLAnchorPaneCadastroClienteDialog(clienteA);
        if (btConfirmarClicked) {
            clienteDAO.inserir(clienteA);
            carregarTableViewCliente();
        } 
    }
    
    @FXML 
    public void handleBtAlterar() throws IOException {
        ClienteA clienteA = tableViewClientes.getSelectionModel().getSelectedItem();
        if (clienteA != null) {
            boolean btConfirmarClicked = showFXMLAnchorPaneCadastroClienteDialog(clienteA);
            if (btConfirmarClicked) {
                clienteDAO.alterar(clienteA);
                carregarTableViewCliente();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta operação requer a seleção \nde um Cliente na tabela ao lado");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtExcluir() throws IOException {
        ClienteA clienteA = tableViewClientes.getSelectionModel().getSelectedItem();
        if (clienteA != null) {
            clienteDAO.remover(clienteA);
            carregarTableViewCliente();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta operação requer a seleção \nde uma Cliente na tabela ao lado");
            alert.show();
        }
    }

    private boolean showFXMLAnchorPaneCadastroClienteDialog(ClienteA clienteA) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroClienteController.class.getResource("/view/FXMLAnchorPaneCadastroClienteDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //criação de um estágio de diálogo (StageDialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Cliente");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //enviando o obejto cliente para o controller
        FXMLAnchorPaneCadastroClienteDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(clienteA);
        
        //apresenta o diálogo e aguarda a confirmação do usuário
        dialogStage.showAndWait();
        
        return controller.isBtConfirmarClicked();
    }
    
}
