package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.OrdemServicoDAO;
import br.edu.ifsc.fln.model.domain.ItemOS;
import br.edu.ifsc.fln.model.domain.OrdemServico;
import br.edu.ifsc.fln.utils.AlertDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author mpisching
 */
public class FXMLAPProcessoOrdemServicoController implements Initializable {

    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonInserir;

    @FXML
    private Button buttonRemover;

    @FXML
    private Label lbOrdemServicoVeiculo;

    @FXML
    private Label lbOrdemServicoAgenda;

    @FXML
    private Label lbOrdemServicoDesconto;

    @FXML
    private Label lbOrdemServicoId;

    @FXML
    private Label lbOrdemServicoStatus;

    @FXML
    private Label lbOrdemServicoTotal;

    @FXML
    private TableView<OrdemServico> tableViewOrdemServico;

    @FXML
    private TableColumn<OrdemServico, Integer> tableColumnOrdemServicoId;

    @FXML
    private TableColumn<OrdemServico, LocalDate> tableColumnOrdemServicoAgenda;

    @FXML
    private TableColumn<OrdemServico, OrdemServico> tableColumnOrdemServicoVeiculo;

    private List<OrdemServico> listaOrdemServicos;
    private ObservableList<OrdemServico> observableListOrdemServicos;

    private final OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewOrdemServico();

        tableViewOrdemServico.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableView(newValue));
    }

    public void carregarTableViewOrdemServico() {
        DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        tableColumnOrdemServicoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        //tableColumnOrdemServicoData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableColumnOrdemServicoAgenda.setCellFactory(column -> {
            return new TableCell<OrdemServico, LocalDate>() {
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(myDateFormatter.format(item));
                    }
                }
            };
        });

        tableColumnOrdemServicoAgenda.setCellValueFactory(new PropertyValueFactory<>("agenda"));
        tableColumnOrdemServicoVeiculo.setCellValueFactory(new PropertyValueFactory<>("veiculo"));

        listaOrdemServicos = ordemServicoDAO.listar();

        observableListOrdemServicos = FXCollections.observableArrayList(listaOrdemServicos);
        tableViewOrdemServico.setItems(observableListOrdemServicos);
    }

    public void selecionarItemTableView(OrdemServico ordem) {
        if (ordem != null) {
            lbOrdemServicoId.setText(Integer.toString(ordem.getId()));
            lbOrdemServicoAgenda.setText(String.valueOf(
                    ordem.getAgenda().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            lbOrdemServicoTotal.setText(String.format("%.2f", ordem.getTotal()));
            lbOrdemServicoDesconto.setText((String.format("%.2f", ordem.getDesconto())) + "%");
            lbOrdemServicoStatus.setText(ordem.getStatus().name());
            lbOrdemServicoVeiculo.setText(ordem.getVeiculo().getPlaca());
        } else {
            lbOrdemServicoId.setText("");
            lbOrdemServicoAgenda.setText("");
            lbOrdemServicoTotal.setText("");
            lbOrdemServicoDesconto.setText("");
            lbOrdemServicoStatus.setText("");
            lbOrdemServicoVeiculo.setText("");
        }
    }

    @FXML
    private void handleButtonInserir(ActionEvent event) throws IOException, SQLException {
        OrdemServico ordem = new OrdemServico();
        List<ItemOS> itens = new ArrayList<>();
        ordem.setItens(itens);
        boolean buttonConfirmarClicked = showFXMLAPProcessoOrdemServicoDialog(ordem);
        if (buttonConfirmarClicked) {
            //O código comentado a seguir (bloco try..catch) evidencia uma má prática de programação, haja vista que o boa parte da lógica de negócio está implementada no controller
            //PROBLEMA: caso haja necessidade de levar esta aplicação para outro nível (uma aplicação web, por exemplo), todo esse código deverá ser repetido no controller, o que
            //de fato pode se tornar inconsistente caso uma nova lógica seja necessária, implicando na necessidade de rever todos os controllers das aplicações, mas, o que garante
            // que todas equipes farão isso?
            //SOLUÇÃO: levar a lógica de negócio para o OrdemServicoDAO, afinal, estamos tratando de uma ordem. É ela que deve resolver o problema
//            try {
//                connection.setAutoCommit(false);
//                ordemDAO.setConnection(connection);
//                ordemDAO.inserir(ordem);
//                itemDeOrdemServicoDAO.setConnection(connection);
//                produtoDAO.setConnection(connection);
//                estoqueDAO.setConnection(connection);
//                for (ItemOS itemDeOrdemServico: ordem.getItensDeOrdemServico()) {
//                    Produto produto = itemDeOrdemServico.getProduto();
//                    itemDeOrdemServico.setOrdemServico(ordemDAO.buscarUltimaOrdemServico());
//                    itemDeOrdemServicoDAO.inserir(itemDeOrdemServico);
//                    produto.getEstoque().setQuantidade(
//                            produto.getEstoque().getQuantidade() - itemDeOrdemServico.getQuantidade());
//                    estoqueDAO.atualizar(produto.getEstoque());
//                }
//                connection.commit();
//                carregarTableView();
//            } catch (SQLException exc) {
//                try {
//                    connection.rollback();
//                } catch (SQLException exc1) {
//                    Logger.getLogger(FXMLAPProcessoOrdemServicoController.class.getName()).log(Level.SEVERE, null, exc1);
//                }
//                Logger.getLogger(FXMLAPProcessoOrdemServicoController.class.getName()).log(Level.SEVERE, null, exc);
//            }
//        }
            try {
                ordemServicoDAO.inserir(ordem);
            } catch (Exception e) {
                AlertDialog.exceptionMessage(e);
            }
            carregarTableViewOrdemServico();
        }
    }

    @FXML
    private void handleButtonAlterar(ActionEvent event)  {
        OrdemServico ordem = tableViewOrdemServico.getSelectionModel().getSelectedItem();
        if (ordem != null) {
            boolean buttonConfirmarClicked = false;
            try {
                buttonConfirmarClicked = showFXMLAPProcessoOrdemServicoDialog(ordem);
            } catch (IOException e) {
                AlertDialog.exceptionMessage(e);
            }
            if (buttonConfirmarClicked) {
                try {
                    ordemServicoDAO.alterar(ordem);
                    carregarTableViewOrdemServico();
                } catch (Exception e) {
                    AlertDialog.exceptionMessage(e);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um ordem na Tabela.");
            alert.show();
        }
    }

    @FXML
    private void handleButtonRemover(ActionEvent event) {
        OrdemServico ordem = tableViewOrdemServico.getSelectionModel().getSelectedItem();
        if (ordem != null) {
            if (AlertDialog.confirmarExclusao("Tem certeza que deseja excluir a ordem " + ordem.getId())) {
                try {
                    ordemServicoDAO.remover(ordem.getId());
                    carregarTableViewOrdemServico();
                } catch (Exception e) {
                    AlertDialog.exceptionMessage(e);
                }

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Por favor, escolha uma ordem na tabela!");
            alert.show();
        }
    }

    public boolean showFXMLAPProcessoOrdemServicoDialog(OrdemServico ordem) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAPProcessoOrdemServicoDialogController.class.getResource(
                "/view/FXMLAPProcessoOrdemServicoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        //criando um estágio de diálogo  (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de ordems");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(scene);
        dialogStage.sizeToScene();
        dialogStage.setResizable(false);

        //Setando o ordem ao controller
        FXMLAPProcessoOrdemServicoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setOrdemServico(ordem);

        //Mostra o diálogo e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();
    }

}