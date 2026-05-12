/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.VeiculoDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Veiculo;
import br.edu.ifsc.fln.utils.AlertDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.ChoiceDialog;
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
public class FXMLAnchorPaneCadastroVeiculoController implements Initializable {

    @FXML
    private Button btAlterar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btInserir;

    @FXML
    private Label lbCliente;

    @FXML
    private Label lbVeiculoId;

    @FXML
    private Label lbVeiculoPlaca;

    @FXML
    private TableColumn<Veiculo, String> tableColumnCliente;

    @FXML
    private TableColumn<Veiculo, String> tableColumnVeiculoPlaca;

    @FXML
    private TableView<Veiculo> tableViewVeiculos;


    private List<Veiculo> listaVeiculos;
    private ObservableList<Veiculo> observableListVeiculos;

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        veiculoDAO.setConnection(connection);
        carregarTableViewVeiculo();

        tableViewVeiculos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewVeiculos(newValue));
    }

    public void carregarTableViewVeiculo() {
        tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tableColumnVeiculoPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));

        listaVeiculos = veiculoDAO.listar();

        observableListVeiculos = FXCollections.observableArrayList(listaVeiculos);
        tableViewVeiculos.setItems(observableListVeiculos);
    }

    public void selecionarItemTableViewVeiculos(Veiculo veiculo) {
        if (veiculo != null) {
            lbVeiculoId.setText(String.valueOf(veiculo.getId()));
            lbVeiculoPlaca.setText(veiculo.getPlaca());
            lbCliente.setText(veiculo.getCliente().getNome());
        } else {
            lbVeiculoId.setText("");
            lbVeiculoPlaca.setText("");
            lbCliente.setText("");
        }

    }

    @FXML
    public void handleBtInserir() throws IOException {
        Veiculo veiculo = new Veiculo();
        boolean btConfirmarClicked = showFXMLAnchorPaneCadastroVeiculoDialog(veiculo);
            if (btConfirmarClicked) {
                veiculoDAO.inserir(veiculo);
                carregarTableViewVeiculo();
            }
    }

    @FXML
    public void handleBtAlterar() throws IOException {
        Veiculo veiculo = tableViewVeiculos.getSelectionModel().getSelectedItem();
        if (veiculo != null) {
            boolean btConfirmarClicked = showFXMLAnchorPaneCadastroVeiculoDialog(veiculo);
            if (btConfirmarClicked) {
                veiculoDAO.alterar(veiculo);
                carregarTableViewVeiculo();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta operação requer a seleção \nde um Veiculo na tabela ao lado");
            alert.show();
        }
    }

    @FXML
    public void handleBtExcluir() throws IOException {
        Veiculo veiculo = tableViewVeiculos.getSelectionModel().getSelectedItem();
        if (veiculo != null) {
            if (AlertDialog.confirmarExclusao("Tem certeza que deseja excluir o veiculo " + veiculo.getPlaca())) {
                veiculoDAO.remover(veiculo);
                carregarTableViewVeiculo();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta operação requer a seleção \nde uma Veiculo na tabela ao lado");
            alert.show();
        }
    }

    private boolean showFXMLAnchorPaneCadastroVeiculoDialog(Veiculo veiculo) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroVeiculoController.class.getResource("/view/FXMLAnchorPaneCadastroVeiculoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        //criação de um estágio de diálogo (StageDialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Veiculo");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        //enviando o obejto veiculo para o controller
        FXMLAnchorPaneCadastroVeiculoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setVeiculo(veiculo);

        //apresenta o diálogo e aguarda a confirmação do usuário
        dialogStage.showAndWait();

        return controller.isBtConfirmarClicked();
    }

}