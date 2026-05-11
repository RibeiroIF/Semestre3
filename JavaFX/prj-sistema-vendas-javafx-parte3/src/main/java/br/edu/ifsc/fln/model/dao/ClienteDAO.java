/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.Cliente;
import br.edu.ifsc.fln.model.domain.PessoaJuridica;
import br.edu.ifsc.fln.model.domain.PessoaFisica;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mpisc
 */
public class ClienteDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente(nome, email, celular, dataCadastro) VALUES(?, ?, ?, ?)";
        String sqlFN = "INSERT INTO pessoaFisica(id_cliente, cpf) VALUES((SELECT max(id) FROM cliente), ?)";
        String sqlFI = "INSERT INTO pessoaJuridica(id_cliente, cnpj, inscricaoEstadual) VALUES((SELECT max(id) FROM " +
                "cliente), ?, ?)";
        try {
            //armazena os dados da superclasse
            PreparedStatement stmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCelular());
            stmt.setDate(4, Date.valueOf(LocalDate.now()));
            stmt.execute();
            //armazena os dados da subclasse
            if (cliente instanceof PessoaFisica) {
                stmt = connection.prepareStatement(sqlFN);
                stmt.setString(1, ((PessoaFisica)cliente).getCpf());
                //a linha a seguir está errada e foi proposital para fazer um teste de rollback
                //stmt.setString(2, ((PessoaFisica)cliente).getCpf());
                stmt.execute();
            } else {
                stmt = connection.prepareStatement(sqlFI);
                stmt.setString(1, ((PessoaJuridica)cliente).getCnpj());
                stmt.setString(2, ((PessoaJuridica)cliente).getInscricaoEstadual());
                stmt.execute();
            }
            connection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);

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

    public boolean alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome=?, email=?, celular=?, dataCadastro=? WHERE id=?";
        String sqlFN = "UPDATE pessoaFisica SET cpf=? WHERE id_cliente = ?";
        String sqlFI = "UPDATE pessoaJuridica SET cnpj=?, inscricaoEstadual=? WHERE id_cliente = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCelular());
            stmt.setDate(4, Date.valueOf(LocalDate.now()));
            stmt.setInt(5, cliente.getId());
            stmt.execute();
            if (cliente instanceof PessoaFisica) {
                stmt = connection.prepareStatement(sqlFN);
                stmt.setString(1, ((PessoaFisica)cliente).getCpf());
                stmt.setInt(2, cliente.getId());
                stmt.execute();
            } else {
                stmt = connection.prepareStatement(sqlFI);
                stmt.setString(1, ((PessoaJuridica)cliente).getCnpj());
                stmt.setString(2, ((PessoaJuridica)cliente).getInscricaoEstadual());
                stmt.setInt(3, cliente.getId());
                stmt.execute();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Cliente> listar() {
        String sql = "SELECT * FROM cliente f "
                        + "LEFT JOIN pessoaFisica n on n.id_cliente = f.id "
                        + "LEFT JOIN pessoaJuridica i on i.id_cliente = f.id;";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente cliente = populateVO(resultado);
                retorno.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Cliente buscar(Cliente cliente) {
        String sql = "SELECT * FROM cliente f "
                        + "LEFT JOIN pessoaFisica n on n.id_cliente = f.id "
                        + "LEFT JOIN pessoaJuridica i on i.id_cliente = f.id WHERE id=?";
        Cliente retorno = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Cliente buscar(int id) {
        String sql = "SELECT * FROM cliente f "
                        + "LEFT JOIN pessoaFisica n on n.id_cliente = f.id "
                        + "LEFT JOIN pessoaJuridica i on i.id_cliente = f.id WHERE id=?";
        Cliente retorno = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }    
    
    private Cliente populateVO(ResultSet rs) throws SQLException {
        Cliente cliente;
        if (rs.getString("cnpj") == null || rs.getString("inscricaoEstadual").length() <= 0) {
            //é um cliente pessoaFisica
            cliente = new PessoaFisica();
            ((PessoaFisica)cliente).setCpf(rs.getString("cpf"));
        } else {
            //é um cliente pessoaJuridica
            cliente = new PessoaJuridica();
            ((PessoaJuridica)cliente).setCnpj(rs.getString("cnpj"));
            ((PessoaJuridica)cliente).setInscricaoEstadual(rs.getString("inscricaoEstadual"));
        }
        cliente.setId(rs.getInt("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setEmail(rs.getString("email"));
        cliente.setCelular(rs.getString("celular"));
        cliente.getPontuacao().verificarPontos();
        return cliente;
    }
}
