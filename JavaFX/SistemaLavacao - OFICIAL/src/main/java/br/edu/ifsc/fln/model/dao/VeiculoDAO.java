/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mpisc
 */
public class VeiculoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo(placa, observacoes, id_modelo, id_cor, id_cliente) VALUES(?, ?, ?, ?, ?)";
        try {
            //armazena os dados da superclasse
            PreparedStatement stmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getObservacoes());
            stmt.setInt(3, veiculo.getModelo().getId());
            stmt.setLong(4, veiculo.getCor().getId());
            stmt.setInt(5, veiculo.getCliente().getId());
            stmt.execute();
            connection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);

            try {
                connection.rollback();
                System.out.println("rollback executado com sucesso!!!");
            } catch (SQLException e) {
                System.out.println("falha na operação roolback...");
                throw new RuntimeException(e);
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean alterar(Veiculo veiculo) {
        String sql = "UPDATE veiculo SET placa=?, observacoes=?, id_modelo=?, id_cor=?, id_cliente=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getObservacoes());
            stmt.setInt(3, veiculo.getModelo().getId());
            stmt.setLong(4, veiculo.getCor().getId());
            stmt.setInt(5, veiculo.getCliente().getId());
            stmt.setInt(6, veiculo.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Veiculo veiculo) {
        String sql = "DELETE FROM veiculo WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, veiculo.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Veiculo> listar() {
        String sql = "SELECT v.id as veiculo_id, v.placa as veiculo_placa, " +
                "v.observacoes as veiculo_observacoes, m.id as modelo_id, " +
                "m.descricao as modelo_descricao, m.categoria as modelo_categoria, " +
                "m1.nome as marca_nome, m2.potencia as motor_potencia, " +
                "m2.tipoCombustivel as motor_tipoCombustivel, c.id as cliente_id, " +
                "c.nome as cliente_nome, c1.id as cor_id, c1.nome as cor_nome FROM veiculo v " +
                "INNER JOIN modelo m ON m.id = v.id_modelo " +
                "INNER JOIN marca m1 ON m1.id = m.id_marca " +
                "INNER JOIN motor m2 ON m2.id_modelo = v.id_modelo " +
                "INNER JOIN cliente c ON c.id = v.id_cliente " +
                "INNER JOIN cor c1 ON c1.id = v.id_cor;";
        List<Veiculo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Veiculo veiculo = populateVO(resultado);
                retorno.add(veiculo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Veiculo buscar(Veiculo veiculo) {
        String sql = "SELECT * FROM veiculo v WHERE id=?;";
        Veiculo retorno = new Veiculo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, veiculo.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Veiculo buscar(int id) {
        String sql = "SELECT * FROM veiculo v WHERE id=?;";
        Veiculo retorno = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    private Veiculo populateVO(ResultSet rs) throws SQLException {
        Veiculo veiculo = new Veiculo();
        Modelo modelo = new Modelo();
        Marca marca = new Marca();
        modelo.setMarca(marca);
        veiculo.setModelo(modelo);

        veiculo.setId(rs.getInt("veiculo_id"));
        veiculo.setPlaca(rs.getString("veiculo_placa"));
        veiculo.getCor().setNome(rs.getString("cor_nome"));
        veiculo.getModelo().setDescricao(rs.getString("modelo_descricao"));
        veiculo.getModelo().getMarca().setNome(rs.getString("marca_nome"));
        veiculo.getModelo().setCategoria(ECategoria.valueOf(rs.getString("modelo_categoria")));
        veiculo.getModelo().getMotor().setPotencia(rs.getInt("motor_potencia"));
        veiculo.getModelo().getMotor().setTipoCombustivel(ETipoCombustivel.valueOf(rs.getString("motor_tipoCombustivel")));
        veiculo.setObservacoes(rs.getString("veiculo_observacoes"));

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.setConnection(connection);
        int idCliente = rs.getInt("cliente_id");
        Cliente cliente = clienteDAO.buscar(idCliente);
        veiculo.setCliente(cliente);
        veiculo.getCliente().setNome(rs.getString("cliente_nome"));
        return veiculo;
    }

    private Veiculo populateVOFull(ResultSet rs) throws SQLException {
        Veiculo veiculo = new Veiculo();

        veiculo.setId(rs.getInt(1));
        veiculo.setPlaca(rs.getString(2));
        veiculo.getModelo().setId(rs.getInt(7));
        veiculo.getModelo().setDescricao(rs.getString(8));
        Cliente cliente;
        if (rs.getString("cnpj") == null) {
            cliente = new PessoaFisica();
            ((PessoaFisica)cliente).setCpf(rs.getString(13));
        } else {
            cliente = new PessoaJuridica();
            ((PessoaJuridica)cliente).setCnpj(rs.getString(15));
            ((PessoaJuridica)cliente).setInscricaoEstadual(rs.getString(16));
        }
        cliente.setId(rs.getInt(7));
        cliente.setNome(rs.getString(8));
        cliente.setEmail(rs.getString(9));
        cliente.setCelular(rs.getString(10));
        veiculo.setCliente(cliente);
        return veiculo;
    }
}