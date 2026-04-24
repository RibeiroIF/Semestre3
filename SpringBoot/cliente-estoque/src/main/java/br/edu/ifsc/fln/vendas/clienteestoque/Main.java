package br.edu.ifsc.fln.vendas.clienteestoque;

import com.ifsc.cliente.model.Categoria;
import com.ifsc.cliente.service.CategoriaService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final CategoriaService service = new CategoriaService();

    // Componentes da tela
    private TextField txtId;
    private TextField txtDescricao;
    private TableView<Categoria> tabela;

    @Override
    public void start(Stage stage) {
        // 1. Linha Superior: Campos e Botões (HBox)
        Label lblId = new Label("ID:");
        txtId = new TextField();
        txtId.setPrefWidth(50);
        txtId.setDisable(true);

        Label lblDescricao = new Label("Descrição:");
        txtDescricao = new TextField();
        txtDescricao.setPrefWidth(200);

        Button btnSalvar = new Button("Salvar");
        Button btnExcluir = new Button("Excluir");
        Button btnLimpar = new Button("Limpar");

        HBox formLayout = new HBox(10, lblId, txtId, lblDescricao, txtDescricao, btnSalvar, btnExcluir, btnLimpar);
        formLayout.setAlignment(Pos.CENTER_LEFT);

        // 2. Área Central: Tabela
        tabela = new TableView<>();

        TableColumn<Categoria, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(50);

        TableColumn<Categoria, String> colDescricao = new TableColumn<>("Descrição");
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colDescricao.setPrefWidth(350);

        tabela.getColumns().addAll(colId, colDescricao);
        VBox.setVgrow(tabela, Priority.ALWAYS); // Faz a tabela esticar

        // 3. Ações dos Botões (Eventos)
        btnSalvar.setOnAction(e -> onSalvar());
        btnExcluir.setOnAction(e -> onExcluir());
        btnLimpar.setOnAction(e -> onLimpar());

        tabela.setOnMouseClicked(e -> {
            Categoria selecionada = tabela.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                txtId.setText(String.valueOf(selecionada.getId()));
                txtDescricao.setText(selecionada.getDescricao());
            }
        });

        // 4. Estrutura Final da Tela (VBox)
        VBox root = new VBox(15, formLayout, tabela);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Cliente Desktop - Categorias");
        stage.setScene(scene);
        stage.show();

        atualizarTabela(); // Ao abrir a tela, já busca da internet
    }

    // --- Métodos Operacionais ---

    private void onSalvar() {
        try {
            Categoria c = new Categoria();
            if (!txtId.getText().isEmpty()) {
                c.setId(Integer.parseInt(txtId.getText()));
            }
            c.setDescricao(txtDescricao.getText());

            service.salvar(c);
            onLimpar();
            atualizarTabela();
        } catch (Exception e) {
            mostrarErro("Erro ao salvar: " + e.getMessage());
        }
    }

    private void onExcluir() {
        try {
            if (!txtId.getText().isEmpty()) {
                service.excluir(Integer.parseInt(txtId.getText()));
                onLimpar();
                atualizarTabela();
            }
        } catch (Exception e) {
            mostrarErro("Erro ao excluir: " + e.getMessage());
        }
    }

    private void onLimpar() {
        txtId.clear();
        txtDescricao.clear();
    }

    private void atualizarTabela() {
        try {
            tabela.setItems(FXCollections.observableArrayList(service.listar()));
        } catch (Exception e) {
            mostrarErro("Erro ao buscar da API: " + e.getMessage());
        }
    }

    private void mostrarErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setContentText(msg);
        alert.show();
    }

    public static void main(String[] args) {
        launch();
    }
}