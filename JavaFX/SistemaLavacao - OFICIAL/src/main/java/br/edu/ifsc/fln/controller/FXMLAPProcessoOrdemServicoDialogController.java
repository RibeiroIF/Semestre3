/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.exception.DAOException;
import br.edu.ifsc.fln.exception.ExceptionLavacao;
import br.edu.ifsc.fln.model.dao.VeiculoDAO;
import br.edu.ifsc.fln.model.dao.ServicoDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.*;
//import br.edu.ifsc.fln.model.service.ParametrosService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author mpisching
 */
public class FXMLAPProcessoOrdemServicoDialogController implements Initializable {

    @FXML
    private ComboBox<Veiculo> comboBoxVeiculos;
    @FXML
    private DatePicker datePickerAgenda;
    @FXML
    private TableView<ItemOS> tableViewItens;
    @FXML
    private TableColumn<ItemOS, Servico> tableColumnServico;
    @FXML
    private TableColumn<ItemOS, Double> tableColumnValor;
    @FXML
    private TextField tfValor;
    @FXML
    private ComboBox<Servico> comboBoxServico;
    @FXML
    private TextField tfServico;
    @FXML
    private Button buttonAdicionar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private ContextMenu contextMenuTableView;
    @FXML
    private MenuItem contextMenuItemAdicionarItem;
    @FXML
    private MenuItem contextMenuItemRemoverItem;
    @FXML
    private ChoiceBox choiceBoxStatus;
    @FXML
    private TextField tfDesconto;

    private List<Veiculo> listaVeiculos;
    private List<Servico> listaServicos;
    private ObservableList<Veiculo> observableListVeiculos;
    private ObservableList<Servico> observableListServicos;
    private ObservableList<ItemOS> observableListItens;

    //atributos para manipulação de banco de dados
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();
    private final ServicoDAO servicoDAO = new ServicoDAO();

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private OrdemServico ordemServico;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        veiculoDAO.setConnection(connection);
        servicoDAO.setConnection(connection);
        carregarComboBoxVeiculos();
        carregarComboBoxServicos();
        carregarChoiceBoxStatus();
        setFocusLostHandle();
        tableColumnServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    private void carregarComboBoxVeiculos() {
        listaVeiculos = veiculoDAO.listar();
        observableListVeiculos = FXCollections.observableArrayList(listaVeiculos);
        comboBoxVeiculos.setItems(observableListVeiculos);
    }

    private void carregarComboBoxServicos() {
        /* carrega apenas os servicos  com estoque cuja SITUACAO está em ATIVO para operações */
        listaServicos = servicoDAO.listar();
        observableListServicos = FXCollections.observableArrayList(listaServicos);
        comboBoxServico.setItems(observableListServicos);
    }


    public void carregarChoiceBoxStatus() {
        choiceBoxStatus.setItems( FXCollections.observableArrayList(EStatus.values()));
        choiceBoxStatus.getSelectionModel().select(0);
    }

    private void setFocusLostHandle() {
        tfDesconto.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) { // focus lost
                if (tfDesconto.getText() != null && !tfDesconto.getText().isEmpty()) {
                    //System.out.println("teste focus lost");
                    try {
                        ordemServico.setDesconto(Double.parseDouble(tfDesconto.getText()));
                    } catch (ExceptionLavacao e) {
                        throw new RuntimeException(e);
                    }
                    tfValor.setText(String.valueOf(ordemServico.getTotal()));

                }
            }
        });
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
     * @return the ordemServico
     */
    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    /**
     * @param ordemServico the ordemServico to set
     */
    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
        if (ordemServico.getId() != 0) {
            comboBoxVeiculos.getSelectionModel().select(this.ordemServico.getVeiculo());
            datePickerAgenda.setValue(this.ordemServico.getAgenda());
            observableListItens = FXCollections.observableArrayList(
                    this.ordemServico.getItens());
            tableViewItens.setItems(observableListItens);
            tfValor.setText(String.format("%.2f", this.ordemServico.getTotal()));
            tfDesconto.setText(String.format("%.2f", this.ordemServico.getDesconto()));
            choiceBoxStatus.getSelectionModel().select(this.ordemServico.getStatus());

        }
    }

    @FXML
    public void handleButtonAdicionar() {
        Servico servico;
        ItemOS itemOS = new ItemOS();
        if (comboBoxServico.getSelectionModel().getSelectedItem() != null) {
            //o comboBox possui dados sintetizados de Servico para evitar carga desnecessária de informação
            servico = comboBoxServico.getSelectionModel().getSelectedItem();
            //a instrução a seguir busca detalhes do servico selecionado
            //servico = servicoDAO.buscar(servico);
            if (servico.getId() != 0) {
                itemOS.setServico(servico);
                itemOS.setValorServico(servico.getValor());
                ordemServico.getItens().add(itemOS);

                try {
                    ordemServico.calcularServico();
                } catch (ExceptionLavacao e) {
                    throw new RuntimeException(e);
                }

                itemOS.setOrdemServico(ordemServico);
                observableListItens = FXCollections.observableArrayList(ordemServico.getItens());
                tableViewItens.setItems(observableListItens);
                tfValor.setText(String.format("%.2f", ordemServico.getTotal()));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Problemas na escolha do servico");
                alert.setContentText("Não existe quantidade suficiente de servicos para ordemServico.");
                alert.show();
            }
        }
    }

    @FXML
    private void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
            ordemServico.setVeiculo(comboBoxVeiculos.getSelectionModel().getSelectedItem());
            ordemServico.setAgenda(datePickerAgenda.getValue());
            ordemServico.setStatus((EStatus)choiceBoxStatus.getSelectionModel().getSelectedItem());
            try {
                ordemServico.setDesconto(Double.parseDouble(tfDesconto.getText()));
            } catch (ExceptionLavacao e) {
                throw new RuntimeException(e);
            }
            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleButtonCancelar() {
        dialogStage.close();
    }

    @FXML
    void handleTableViewMouseClicked(MouseEvent event) {
        ItemOS itemOS
                = tableViewItens.getSelectionModel().getSelectedItem();
        if (itemOS == null) {
            contextMenuItemAdicionarItem.setDisable(true);
            contextMenuItemRemoverItem.setDisable(true);
        } else {
            contextMenuItemAdicionarItem.setDisable(false);
            contextMenuItemRemoverItem.setDisable(false);
        }

    }

    @FXML
    private void handleContextMenuItemAdicionarItem() {
        ItemOS itemOS
                = tableViewItens.getSelectionModel().getSelectedItem();
        int index = tableViewItens.getSelectionModel().getSelectedIndex();

        ordemServico.getItens().remove(index);
        observableListItens = FXCollections.observableArrayList(ordemServico.getItens());
        tableViewItens.setItems(observableListItens);

        tfValor.setText(String.format("%.2f", ordemServico.getTotal()));
    }

//    private String inputDialog(int value) {
//        TextInputDialog dialog = new TextInputDialog(Integer.toString(value));
//        dialog.setTitle("Entrada de dados.");
//        dialog.setHeaderText("Atualização da quantidade de servicos.");
//        dialog.setContentText("Quantidade: ");
//
//        // Traditional way to get the response value.
//        Optional<String> result = dialog.showAndWait();
//        return result.get();
//    }

    @FXML
    private void handleContextMenuItemRemoverItem() {
        ItemOS itemOS
                = tableViewItens.getSelectionModel().getSelectedItem();
        int index = tableViewItens.getSelectionModel().getSelectedIndex();
        ordemServico.getItens().remove(index);
        observableListItens = FXCollections.observableArrayList(ordemServico.getItens());
        tableViewItens.setItems(observableListItens);

        tfValor.setText(String.format("%.2f", ordemServico.getTotal()));
    }

    //validar entrada de dados do cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (comboBoxVeiculos.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Veiculo inválido!\n";
        }

        if (datePickerAgenda.getValue() == null) {
            errorMessage += "Data inválida!\n";
        }

        if (observableListItens == null) {
            errorMessage += "Itens de ordemServico inválidos!\n";
        }

        DecimalFormat df = new DecimalFormat("0.00");
        try {
            tfDesconto.setText(df.parse(tfDesconto.getText()).toString());
        } catch (ParseException ex) {
            errorMessage += "A taxa de desconto está incorreta! Use \",\" como ponto decimal.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
}