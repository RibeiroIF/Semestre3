package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.ItemOS;
import br.edu.ifsc.fln.model.domain.Servico;
import br.edu.ifsc.fln.model.domain.OrdemServico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ItemOSDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(ItemOS itemOS) {
        String sql = "INSERT INTO item_os(valor_servico, observacoes, id_servico, id_ordemservico) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, itemOS.getValorServico());
            stmt.setString(2, itemOS.getObservacoes());
            stmt.setInt(3, itemOS.getServico().getId());
            stmt.setInt(4, itemOS.getOrdemServico().getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemOSDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(ItemOS itemOS) {
        return true;
    }

    public boolean remover(ItemOS itemOS) {
        String sql = "DELETE FROM item_os WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemOS.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemOSDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<ItemOS> listar() {
        String sql = "SELECT * FROM item_os";
        List<ItemOS> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemOS itemOS = new ItemOS();
                Servico servico = new Servico();
                OrdemServico ordemServico = new OrdemServico();
                itemOS.setId(resultado.getInt("id"));
                itemOS.setObservacoes(resultado.getString("observacoes"));
                itemOS.setValorServico(resultado.getDouble("valor_servico"));

                servico.setId(resultado.getInt("id_servico"));
                ordemServico.setId(resultado.getInt("id_ordemservico"));

                //Obtendo os dados completos do Servico associado ao Item de OrdemServico
                ServicoDAO servicoDAO = new ServicoDAO();
                servicoDAO.setConnection(connection);
                servico = servicoDAO.buscar(servico);

                OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
                ordemServicoDAO.setConnection(connection);
                ordemServico = ordemServicoDAO.buscar(ordemServico);

                itemOS.setServico(servico);
                itemOS.setOrdemServico(ordemServico);

                retorno.add(itemOS);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemOSDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<ItemOS> listarPorOrdemServico(OrdemServico ordemServico) {
        String sql = "SELECT * FROM item_os WHERE id_ordemservico=?";
        List<ItemOS> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, ordemServico.getId());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemOS itemOS = new ItemOS();
                Servico servico = new Servico();
                OrdemServico v = new OrdemServico();
                itemOS.setId(resultado.getInt("id"));
                itemOS.setValorServico(resultado.getDouble("valor_servico"));
                itemOS.setObservacoes(resultado.getString("observacoes"));
                servico.setId(resultado.getInt("id_servico"));
                v.setId(resultado.getInt("id_ordemservico"));

                //Obtendo os dados completos do Servico associado ao Item de OrdemServico
                ServicoDAO servicoDAO = new ServicoDAO();
                servicoDAO.setConnection(connection);
                servico = servicoDAO.buscar(servico);

                itemOS.setServico(servico);
                itemOS.setOrdemServico(v);

                retorno.add(itemOS);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemOSDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public ItemOS buscar(ItemOS itemOS) {
        String sql = "SELECT * FROM item_os WHERE id=?";
        ItemOS retorno = new ItemOS();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemOS.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Servico servico = new Servico();
                OrdemServico ordemServico = new OrdemServico();
                itemOS.setId(resultado.getInt("id"));
                itemOS.setValorServico(resultado.getDouble("valor_servico"));
                itemOS.setObservacoes(resultado.getString("observacoes"));

                servico.setId(resultado.getInt("id_servico"));
                ordemServico.setId(resultado.getInt("id_ordemservico"));

                //Obtendo os dados completos do Cliente associado à OrdemServico
                ServicoDAO servicoDAO = new ServicoDAO();
                servicoDAO.setConnection(connection);
                servico = servicoDAO.buscar(servico);

                OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
                ordemServicoDAO.setConnection(connection);
                ordemServico = ordemServicoDAO.buscar(ordemServico);

                itemOS.setServico(servico);
                itemOS.setOrdemServico(ordemServico);

                retorno = itemOS;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdemServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}