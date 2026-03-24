/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.dao.CorDAO;
import br.edu.ifsc.fln.model.database.Database;
import br.edu.ifsc.fln.model.database.DatabaseFactory;
import br.edu.ifsc.fln.model.domain.Cor
;
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
public class FXMLAnchorPaneCadastroCor
Controller implements Initializable {

    
    @FXML
    private Button btnAlterar;

    @FXML
    private Button btExcluir;
    
    @FXML
    private Button btInserir;

    @FXML
    private Label lbCor
Descricao;

    @FXML
    private Label lbCor
Id;

    @FXML
    private TableColumn<Cor
, String> tableColumnCor
Descricao;

    @FXML
    private TableView<Cor
> tableViewCor
s;
    
    private List<Cor
> listaCor
s;
    private ObservableList<Cor
> observableListCor
s;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final CorDAO cor
DAO = new CorDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cor
DAO.setConnection(connection);
        carregarTableViewCor
();
        
        tableViewCor
s.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewCor
s(newValue));
    }     
    
    public void carregarTableViewCor
() {
        tableColumnCor
Descricao.setCellValueFactory(new PropertyValueFactory<>("nome
"));
        
        listaCor
s = cor
DAO.listar();
        
        observableListCor
s = FXCollections.observableArrayList(listaCor
s);
        tableViewCor
s.setItems(observableListCor
s);
    }
    
    public void selecionarItemTableViewCor
s(Cor
 cor
) {
        if (cor
 != null) {
            lbCor
Id.setText(String.valueOf(cor
.getId())); 
            lbCor
Descricao.setText(cor
.getDescricao());
        } else {
            lbCor
Id.setText(""); 
            lbCor
Descricao.setText("");
        }
        
    }
    
    @FXML
    public void handleBtInserir() throws IOException {
        Cor
 cor
 = new Cor
();
        boolean btConfirmarClicked = showFXMLAnchorPaneCadastroCor
Dialog(cor
);
        if (btConfirmarClicked) {
            cor
DAO.inserir(cor
);
            carregarTableViewCor
();
        } 
    }
    
    @FXML 
    public void handleBtAlterar() throws IOException {
        Cor
 cor
 = tableViewCor
s.getSelectionModel().getSelectedItem();
        if (cor
 != null) {
            boolean btConfirmarClicked = showFXMLAnchorPaneCadastroCor
Dialog(cor
);
            if (btConfirmarClicked) {
                cor
DAO.alterar(cor
);
                carregarTableViewCor
();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta operação requer a seleção \nde uma Cor
 na tabela ao lado");
            alert.show();
        }
    }
    
    @FXML
    public void handleBtExcluir() throws IOException {
        Cor
 cor
 = tableViewCor
s.getSelectionModel().getSelectedItem();
        if (cor
 != null) {
            cor
DAO.remover(cor
);
            carregarTableViewCor
();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Esta operação requer a seleção \nde uma Cor
 na tabela ao lado");
            alert.show();
        }
    }

    private boolean showFXMLAnchorPaneCadastroCor
Dialog(Cor
 cor
) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroCor
Controller.class.getResource("/view/FXMLAnchorPaneCadastroCor
Dialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        //criação de um estágio de diálogo (StageDialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Cor
");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        //enviando o obejto cor
 para o controller
        FXMLAnchorPaneCadastroCor
DialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCor
(cor
);
        
        //apresenta o diálogo e aguarda a confirmação do usuário
        dialogStage.showAndWait();
        
        return controller.isBtConfirmarClicked();
    }
    
}
