package br.edu.ifsc.fln.controller;

import br.edu.ifsc.fln.model.domain.Cor;
import br.edu.ifsc.fln.model.domain.ParametrosSistema;
import br.edu.ifsc.fln.model.service.ParametrosService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author mpisc
 */
public class FXMLAPControleDescontosController implements Initializable {

    @FXML
    private Button btSalvar;

    @FXML
    private Button btRestaurar;

    @FXML
    private Spinner<Double> spDescontoPequeno;

    @FXML
    private Spinner<Double> spDescontoMedio;

    @FXML
    private Spinner<Double> spDescontoGrande;

    @FXML
    private Spinner<Double> spDescontoMoto;

    @FXML
    private Spinner<Double> spDescontoPadrao;

    private ParametrosSistema parametros;

    private Stage dialogStage;
    private boolean btSalvarClicked = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        double descontoPequeno = ParametrosService.getPercentualPequeno();
        double descontoMedio = ParametrosService.getPercentualMedio();
        double descontoGrande = ParametrosService.getPercentualGrande();
        double descontoMoto = ParametrosService.getPercentualMoto();
        double descontoPadrao = ParametrosService.getPercentualPadrao();

        inicializarSpinner(spDescontoPequeno, 0, 100, descontoPequeno);
        inicializarSpinner(spDescontoMedio, 0, 100, descontoMedio);
        inicializarSpinner(spDescontoGrande, 0, 100, descontoGrande);
        inicializarSpinner(spDescontoMoto, 0, 100, descontoMoto);
        inicializarSpinner(spDescontoPadrao, 0, 100, descontoPadrao);
    }

    private void inicializarSpinner(Spinner<Double> spinner, double min, double max, double initial){
        SpinnerValueFactory<Double> factory = new SpinnerValueFactory.DoubleSpinnerValueFactory(min, max, initial);
        spinner.setValueFactory(factory);
    }

    public boolean isBtSalvarClicked() {
        return btSalvarClicked;
    }

    public void setBtSalvarClicked(boolean btSalvarClicked) {
        this.btSalvarClicked = btSalvarClicked;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public ParametrosSistema getParametrosSistema(){
        return parametros;
    }

    public void setParametrosSistema(ParametrosSistema parametros) {
        this.parametros = parametros;
        spDescontoPequeno.getValueFactory().setValue(ParametrosService.getPercentualPequeno());
        spDescontoMedio.getValueFactory().setValue(ParametrosService.getPercentualMedio());
        spDescontoGrande.getValueFactory().setValue(ParametrosService.getPercentualGrande());
        spDescontoMoto.getValueFactory().setValue(ParametrosService.getPercentualMoto());
        spDescontoPadrao.getValueFactory().setValue(ParametrosService.getPercentualPadrao());
    }

    @FXML
    public void handleBtSalvar() {
        ParametrosService.setPercentualPequeno(spDescontoPequeno.getValue());
        ParametrosService.setPercentualMedio(spDescontoMedio.getValue());
        ParametrosService.setPercentualGrande(spDescontoGrande.getValue());
        ParametrosService.setPercentualMoto(spDescontoMoto.getValue());
        ParametrosService.setPercentualPadrao(spDescontoPadrao.getValue());
        btSalvarClicked = true;
        //dialogStage.close();

    }

    @FXML
    public void handleBtRestaurar() throws IOException {
        ParametrosSistema parametros = new ParametrosSistema();
        this.setParametrosSistema(parametros);
    }


    private boolean showFXMLAnchorPaneCadastroCorDialog(Cor cor) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAPControleDescontosController.class.getResource("/view/FXMLAPCadastroCorDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        //criação de um estágio de diálogo (StageDialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Cor");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        //enviando o obejto cor para o controller
        FXMLAPCadastroCorDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCor(cor);

        //apresenta o diálogo e aguarda a confirmação do usuário
        dialogStage.showAndWait();

        return controller.isBtConfirmarClicked();
    }

}