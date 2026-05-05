/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.ClienteA;

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
public class ClienteADAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(ClienteA clienteA) {
        String sql = "INSERT INTO cliente(nome, cpf, telefone, endereco, data_nascimento) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, clienteA.getNome());
            stmt.setString(2, clienteA.getCpf());
            stmt.setString(3, clienteA.getTelefone());
            stmt.setString(4, clienteA.getEndereco());
            stmt.setDate(5, java.sql.Date.valueOf(clienteA.getDataNascimento()));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteADAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(ClienteA clienteA) {
        String sql = "UPDATE cliente SET nome=?, cpf=?, telefone=?, endereco=?, data_nascimento=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, clienteA.getNome());
            stmt.setString(2, clienteA.getCpf());
            stmt.setString(3, clienteA.getTelefone());
            stmt.setString(4, clienteA.getEndereco());
            stmt.setDate(5, java.sql.Date.valueOf(clienteA.getDataNascimento()));
            stmt.setInt(6, clienteA.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteADAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(ClienteA clienteA) {
        String sql = "DELETE FROM cliente WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, clienteA.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteADAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<ClienteA> listar() {
        String sql = "SELECT * FROM cliente";
        List<ClienteA> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ClienteA clienteA = populateVO(resultado);
                retorno.add(clienteA);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteADAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public ClienteA buscar(ClienteA clienteA) {
        String sql = "SELECT * FROM cliente WHERE id=?";
        ClienteA retorno = new ClienteA();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, clienteA.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteADAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    private ClienteA populateVO(ResultSet rs) throws SQLException {
        ClienteA clienteA = new ClienteA();
        clienteA.setId(rs.getInt("id"));
        clienteA.setNome(rs.getString("nome"));
        clienteA.setCpf(rs.getString("cpf"));
        clienteA.setTelefone(rs.getString("telefone"));
        clienteA.setEndereco(rs.getString("endereco"));
        clienteA.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        return clienteA;
    }
}
