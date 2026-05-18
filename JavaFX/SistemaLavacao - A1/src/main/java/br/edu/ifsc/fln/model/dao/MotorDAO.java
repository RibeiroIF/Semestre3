package br.edu.ifsc.fln.model.dao;

import br.edu.ifsc.fln.model.domain.ECategoria;
import br.edu.ifsc.fln.model.domain.ETipoCombustivel;
import br.edu.ifsc.fln.model.domain.Motor;
import br.edu.ifsc.fln.model.domain.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MotorDAO{

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean atualizar(Motor motor) {
        String sql = "UPDATE motor SET potencia=?, tipoCombustivel=? WHERE id_modelo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, motor.getPotencia());
            stmt.setString(2, motor.getTipoCombustivel().name());
            stmt.setInt(3, motor.getModelo().getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Modelo> listar() {
        String sql = "SELECT * FROM motor e INNER JOIN modelo p ON p.id = e.id_modelo";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo modelo = populateVO(resultado);
                retorno.add(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Modelo> listarPorPotencia(Motor motor) {
        String sql = "select m.potencia as motor_potencia FROM motor m " +
                "INNER JOIN modelo m1 on m.id = m1.id_motor WHERE id=?;";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, motor.getModelo().getId());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo modelo = populateVO(resultado);
                retorno.add(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Modelo> listarPorCombustivel() {
        String sql = "select m.tipoCombustivel as motor_tipoCombustivel FROM motor m " +
                "INNER JOIN modelo m1 on m.id = m1.id_motor;";
        List<Modelo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Modelo modelo = populateVO(resultado);
                retorno.add(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Modelo getMotor(Modelo modelo) {
        String sql = "SELECT * FROM motor e INNER JOIN modelo p ON p.id = e.id_modelo WHERE e.id_modelo = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, modelo.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                return populateVO(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MotorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Modelo populateVO(ResultSet rs) throws SQLException {
        Modelo modelo = new Modelo();

        modelo.setId(rs.getInt("id"));
        modelo.setDescricao(rs.getString("descricao"));
        modelo.setCategoria(ECategoria.valueOf(rs.getString("id_categoria")));
        modelo.getMotor().setPotencia(rs.getInt("potencia"));
        modelo.getMotor().setTipoCombustivel(Enum.valueOf(ETipoCombustivel.class,
                rs.getString("tipoCombustivel")));
        return modelo;
    }


}
