package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Marca;
import br.edu.ifsc.fln.model.domain.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ModeloDAO{

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Modelo modelo) {
        final String sql = "INSERT INTO modelo(descricao, id_marca) VALUES(?,?);";
        final String sqlEstoque = "INSERT INTO marca(id_modelo) (SELECT max(id) FROM modelo);";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            //registra o modelo
            stmt.setString(1, modelo.getDescricao());
            stmt.setInt(2, modelo.getMarca().getId());
            stmt.execute();
            //registra o marca do modelo imediatamente
            stmt = connection.prepareStatement(sqlEstoque);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Modelo modelo) {
        String sql = "UPDATE modelo SET descricao=?, id_marca=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, modelo.getDescricao());
            stmt.setInt(2, modelo.getMarca().getId());
            stmt.setInt(3, modelo.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Modelo modelo) {
        String sql = "DELETE FROM modelo WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, modelo.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Modelo> listar() {
        String sql =  "SELECT p.id as modelo_id, p.descricao as modelo_descricao, c.id as marca_id, c.nome as " +
                "marca_nome, FROM modelo p INNER JOIN marca c ON c.id = p.id_marca;";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo modelo = populateVO(resultado);
                retorno.add(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Modelo> listagem() {
        String sql = "select * from modelo p "
                +       "inner join marca c on c.id = p.id_marca;";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo modelo = populateVOFull(resultado);
                retorno.add(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Modelo> listarPorMarca(Marca marca) {
        String sql =  "SELECT p.id as modelo_id, p.descricao as modelo_descricao, "
                + "c.id as marca_id, c.nome as marca_nome "
                + "FROM modelo p INNER JOIN marca c ON c.id = p.id_marca WHERE c.id = ?;";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, marca.getId());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo modelo = populateVO(resultado);
                retorno.add(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Modelo buscar(Modelo modelo) {
        String sql =  "SELECT p.id as modelo_id, p.descricao as modelo_descricao, "
                + "c.id as marca_id, c.nome as marca_nome "
                + "FROM modelo p INNER JOIN marca c ON c.id = p.id_marca WHERE p.id = ?;";
        Modelo retorno = new Modelo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, modelo.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    private Modelo populateVO(ResultSet rs) throws SQLException {
        Modelo modelo = new Modelo();
        Marca marca = new Marca();
        modelo.setMarca(marca);

        modelo.setId(rs.getInt("modelo_id"));
        modelo.setDescricao(rs.getString("modelo_descricao"));
        marca.setId(rs.getInt("marca_id"));
        marca.setNome(rs.getString("marca_nome"));
        return modelo;
    }


    private Modelo populateVOFull(ResultSet rs) throws SQLException {
        Modelo modelo = new Modelo();
        Marca marca = new Marca();
        modelo.setMarca(marca);

        modelo.setId(rs.getInt(1));
        modelo.setDescricao(rs.getString(2));
        marca.setId(rs.getInt(3));
        marca.setNome(rs.getString(4));
//        int idFornecedor = rs.getInt("fornecedor_id");
//        FornecedorDAO fornecedorDAO = new FornecedorDAO();
//        Fornecedor fornecedor = fornecedorDAO.buscar(idFornecedor);
//        modelo.setFornecedor(fornecedor);
        return modelo;
    }

}
